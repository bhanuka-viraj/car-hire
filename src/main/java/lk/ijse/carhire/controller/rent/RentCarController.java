package lk.ijse.carhire.controller.rent;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.service.custom.CustomerService;
import lk.ijse.carhire.service.custom.RentService;

import javax.swing.plaf.synth.Region;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentCarController {
    @FXML
    private DatePicker fromDatePick;

    @FXML
    private TextField txtCustNic;

    @FXML
    private Label lblRefundableDeposit;
    @FXML
    private Label lblTotalPayment;
    @FXML
    private Label lblBalancePayment;

    @FXML
    private Label lblTotalRent;

    @FXML
    private Label lblMake;

    @FXML
    private DatePicker toDatePick;

    @FXML
    private Label lblCustomer;

    @FXML
    private TextField txtAdvancePayment;

    @FXML
    private TextField txtCarId;

    @FXML
    private Label lblDiscount;
    @FXML
    private Label lblDiscountAmt;
    @FXML
    private Label lblAdvance1;

    @FXML
    private Label lblAdvance;
    @FXML
    private Button btnRent;
    @FXML
    private Button btnReturn;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Label lblTotalOrBalance;
    @FXML
    private Label lblReturnOrCreateDate;
    @FXML
    private Label lblPayAmount;
    @FXML
    private Label lblReturnOrCreate;
    @FXML
    private Label lblReturnAnn;


    private CarDto carDto;
    private CustomerDto custDto;
    private RentDto rentDto;

    CustomerService custService= ServiceFactory.getService(ServiceType.CUSTOMER);
    CarService carService= ServiceFactory.getService(ServiceType.CAR);
    RentService rentService=ServiceFactory.getService(ServiceType.RENT);

    public void initialize(){
        showReturnButton(false);
        showDeleteButton(false);
        showUpdateButton(false);

        Platform.runLater(()->txtCarId.requestFocus());
    }


    public void setCarDto(CarDto carDto){
        this.carDto=carDto;

        setCar(carDto);
    }
    public void setCustomerDto(CustomerDto customerDto){
        this.custDto=customerDto;
    }
    public void setRentDto(RentDto rentDto){
        this.rentDto=rentDto;

        CarDto carDto1=rentDto.getCarDto();
        setCarDto(carDto1);
        setCustomerDto(rentDto.getCustomerDto());
        setCustLabels(custDto);
        showReturnInfo();
    }

    public RentDto getRentDto(){
        return this.rentDto;
    }

    public void setDetails(){
        fromDatePick.setValue(rentDto.getFromDate());
        toDatePick.setValue(rentDto.getToDate());
        txtCustNic.setText(rentDto.getCustomerDto().getNic());
        txtAdvancePayment.setText(String.valueOf(rentDto.getAdvancedPayment()));
        lblAdvance1.setText("- "+String.valueOf(rentDto.getAdvancedPayment()));
        lblAdvance.setText(String.valueOf(rentDto.getAdvancedPayment()));
        lblTotalPayment.setText(String.valueOf(rentDto.getTotal()));
        lblTotalRent.setText(String.valueOf(totalRent()));
        lblBalancePayment.setText(String.valueOf(rentDto.getBalance()));


    }

    private void setCar(CarDto carDto) {
        txtCarId.setText(carDto.getId());
        lblMake.setText(carDto.getMake());
        lblRefundableDeposit.setText(String.valueOf(carDto.getMinDeposit()));

    }

    @FXML
    void btnRentOnAction(ActionEvent event) {
        try{
            rentDto = new RentDto();
            String generatedId=generateRentId();
//            System.out.println(generatedId);

            rentDto.setFromDate(fromDatePick.getValue());
            rentDto.setToDate(toDatePick.getValue());
            Double totalRent=totalRent();

            if(generatedId!=null){
                rentDto.setId(generatedId);
                rentDto.setTotal(CalTotal(carDto.getMinDeposit(),totalRent)-(totalRent*discount()));
                rentDto.setReturn(false);
                rentDto.setBalance(balancePayment());
                rentDto.setRefundableDeposit(carDto.getMinDeposit());
                rentDto.setAdvancedPayment(Double.parseDouble(txtAdvancePayment.getText()));
                rentDto.setPerDayRent(carDto.getPricePerDay());
                rentDto.setOverdueAmt(0);
                rentDto.setCarDto(carDto);
                rentDto.setCustomerDto(custDto);

                boolean isSaved=rentService.saveRent(rentDto);

                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Rent saved successfully !").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Rent cannot be saved !").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING,
                        custDto.getFstname()+" has just rented this car, please return to rent again").show();

            }


        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try{
            RentDto rentToUpdate=rentDto;
            Double totalRent=totalRent();

            rentToUpdate.setFromDate(fromDatePick.getValue());
            rentToUpdate.setToDate(toDatePick.getValue());
            rentToUpdate.setTotal(CalTotal(carDto.getMinDeposit(),totalRent)-(totalRent*discount()));
            rentToUpdate.setReturn(false);
            rentToUpdate.setBalance(balancePayment());
            rentToUpdate.setRefundableDeposit(carDto.getMinDeposit());
            rentToUpdate.setAdvancedPayment(Double.parseDouble(txtAdvancePayment.getText()));
            rentToUpdate.setPerDayRent(carDto.getPricePerDay());
            rentToUpdate.setOverdueAmt(rentDto.getOverdueAmt());
            rentToUpdate.setCarDto(carDto);
            rentToUpdate.setCustomerDto(custDto);

            boolean isUpdated=rentService.saveRent(rentToUpdate);
            System.out.println("Rentdto car id:   "+rentDto.getCarDto().getId()+"      cardto car id: "+carDto.getId());
            if(isUpdated){
                if (!rentDto.getCarDto().getId().equals(carDto.getId())){
                    rentService.setIsRented(rentDto.getCarDto().getId(),false);
                }
                new Alert(Alert.AlertType.CONFIRMATION,"Rent saved successfully !").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Rent cannot be saved !").show();
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnReturnOnAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/carhire/view/rent/return_car.fxml"));
            Parent root = loader.load();
            ReturnCarController returnCarController =loader.getController();

//            System.out.println("*************************"+rentDto.getCustomerDto().getFstname()+"******");
            //            System.out.println("over due days: "+daysFromReturnDate()+"overdue amt: "+overdue()+"********************");
            returnCarController.setOverdueAmt(overdue());
            returnCarController.setOverdueDays(daysFromReturnDate());
            returnCarController.setRentDto(rentDto);



            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Rent | Return");
            stage.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        try{
            boolean isDeleted=rentService.deleteRent(rentDto.getId());

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Rent deleted successfully").show();
                Stage stage= (Stage) btnDelete.getScene().getWindow();
                stage.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void txtNicOnAction(ActionEvent event) throws Exception {
        try{
            custDto=custService.getCustomerByNic(txtCustNic.getText());

            setCustLabels(custDto);



        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Incorrect customer id").show();
            e.printStackTrace();
        }
    }

    public void setCustLabels(CustomerDto custDto){
        lblCustomer.setText(custDto.getFstname());
        lblDiscount.setText("- "+String.valueOf(discount()*100)+" %");
        lblDiscountAmt.setText("- "+String.valueOf(
                discount()*totalRent()
        ));
    }
    @FXML
    void txtCarIdOnAction(ActionEvent event) {
        try{
            carDto=carService.getCarById(txtCarId.getText());

            lblMake.setText(carDto.getMake());
            lblRefundableDeposit.setText(String.valueOf(carDto.getMinDeposit()));

        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Incorrect car id").show();
            e.printStackTrace();
        }
    }

    @FXML
    void toDatePickerOnAction(ActionEvent event) {
        try{
            if(txtCarId!=null){
                lblTotalRent.setText(String.valueOf(totalRent()));
            }else {
                new Alert(Alert.AlertType.WARNING,"Enter car id first !").show();
            }


        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    void txtAdvanceOnAction(ActionEvent event) {
        String advance=txtAdvancePayment.getText();

        lblAdvance.setText(advance);
        lblAdvance1.setText("- "+advance);

        lblTotalPayment.setText(String.valueOf(
                CalTotal(Double.parseDouble(txtAdvancePayment.getText()),carDto.getMinDeposit())
        ));

        lblBalancePayment.setText(String.valueOf(balancePayment()));
    }

    public Double totalRent() {
        LocalDate fromDate;
        LocalDate toDate;

        if (rentDto == null) {
            fromDate = fromDatePick.getValue();
            toDate = toDatePick.getValue();
        } else {
            fromDate = rentDto.getFromDate();
            toDate = rentDto.getToDate();
        }

        try {
            long days = ChronoUnit.DAYS.between(fromDate, toDate);
            double total = days * carDto.getPricePerDay();

            return total;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Double CalTotal(Double... args){
        Double total=0.0;

        for (Double value:args) {
            total+=value;
        }
        return total;
    }

    public Double balancePayment(){
        return totalRent()-(totalRent()*discount())-Double.parseDouble(txtAdvancePayment.getText());
    }


    public double discount(){
        try {
            LocalDate registrationDate=custService.registrationDate(custDto.getNic());

            if(registrationDate.isBefore(LocalDate.now())){
                return 0.1;
            }else{
                return 0.2;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    private String generateRentId() {
        try {
            int count = 0;
            String id;

            while (true) {
                id = String.valueOf(carDto.getId() + "_" + custDto.getNic() + "_" + count);

                RentDto existingRent = rentService.getRentById(id);

                if (existingRent != null && existingRent.isReturn()==true) {
                    count++;
                } else if (existingRent != null && existingRent.isReturn()==false) {
                    return null;
                } else {
                    break;
                }
            }

            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void disableFileds() {
        fromDatePick.setEditable(false);
        toDatePick.setEditable(false);
        txtCustNic.setEditable(false);
        txtAdvancePayment.setEditable(false);
        txtCarId.setEditable(false);
    }
    public void showRentButton(boolean value){
        if (value){
            btnRent.setVisible(true);
        }else {
            btnRent.setVisible(false);
        }
    }
    public void showReturnButton(boolean value){
        if (value){
            btnReturn.setVisible(true);
        }else {
            btnReturn.setVisible(false);
        }
    }

    public void showDeleteButton(boolean value){
        if (value){
            btnDelete.setVisible(true);
        }else {
            btnDelete.setVisible(false);
        }
    }
    public void showUpdateButton(boolean value){
        if (value){
            btnUpdate.setVisible(true);
        }else {
            btnUpdate.setVisible(false);
        }
    }


    public void disableCarId(boolean value) {
        if(value){
            txtCarId.setEditable(false);
        }else {
            txtCarId.setEditable(true);
        }
    }

    public void showReturnInfo(){
        if(rentDto.isReturn()){
            double tot=0;
            lblReturnOrCreate.setText("Returned on");
            lblReturnOrCreateDate.setText(String.valueOf(rentService.getLastUpdateDate(rentDto.getId())));

            if (daysFromReturnDate()>0){
                tot=totalRent()-(totalRent()*discount()+overdue());
            }else {
                tot=totalRent()-(totalRent()*discount());
            }
            lblTotalOrBalance.setText("Total Paid");
            lblPayAmount.setText("Rs. "+String.valueOf(tot));
            lblReturnAnn.setText("**Returned**");
        }else {
            lblReturnOrCreate.setText("Rented on");
            lblReturnOrCreateDate.setText(String.valueOf(rentService.getCreatedDate(rentDto.getId())));

            if (daysFromReturnDate()>0){
                lblTotalOrBalance.setText("Overdue");
                lblPayAmount.setText(String.valueOf(overdue())+"( "+daysFromReturnDate()+" days )");

            }else {
                lblTotalOrBalance.setText("Blc to pay");
                lblPayAmount.setText(String.valueOf(rentDto.getBalance()));
            }
        }
    }

    public long daysFromReturnDate(){
        LocalDate today=LocalDate.now();

        return ChronoUnit.DAYS.between(rentDto.getToDate(), today);
    }

    public double overdue(){
        return rentDto.getPerDayRent()*daysFromReturnDate();
    }

}
