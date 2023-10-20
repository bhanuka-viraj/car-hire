package lk.ijse.carhire.controller.rent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.service.custom.CustomerService;
import lk.ijse.carhire.service.custom.RentService;

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


    private CarDto carDto;
    private CustomerDto custDto;

    CustomerService custService= ServiceFactory.getService(ServiceType.CUSTOMER);
    CarService carService= ServiceFactory.getService(ServiceType.CAR);
    RentService rentService=ServiceFactory.getService(ServiceType.RENT);
    @FXML
    void btnRentOnAction(ActionEvent event) {
        try{
            RentDto rentDto = new RentDto();
            String generatedId=generateRentId();
            Double totalRent=totalRent();

            System.out.println(generatedId);

            if(generatedId!=null){
                rentDto.setId(generatedId);
                rentDto.setFromDate(fromDatePick.getValue());
                rentDto.setToDate(toDatePick.getValue());
                rentDto.setTotal(CalTotal(carDto.getMinDeposit(),totalRent)-(totalRent*discount()));
                rentDto.setReturn(false);
                rentDto.setBalance(balancePayment());
                rentDto.setRefundableDeposit(carDto.getMinDeposit());
                rentDto.setAdvancedPayment(Double.parseDouble(txtAdvancePayment.getText()));
                rentDto.setPerDayRent(carDto.getPricePerDay());
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

                return;
            }




        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
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

            lblCustomer.setText(custDto.getFstname());
            lblDiscount.setText("- "+String.valueOf(discount()*100)+" %");
            lblDiscountAmt.setText("- "+String.valueOf(
                    discount()*totalRent()
            ));



        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Incorrect customer id").show();
            e.printStackTrace();
        }
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

    public Double totalRent(){
        LocalDate fromDate=fromDatePick.getValue();
        LocalDate toDate=toDatePick.getValue();

        try {
            long days= ChronoUnit.DAYS.between(fromDate,toDate);
            double total;

            total=days * carDto.getPricePerDay();

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
                // Generate the ID by concatenating car ID, customer NIC, and count
                id = String.valueOf(carDto.getId() + "_" + custDto.getNic() + "_" + count);

                // Check if the ID already exists in the database
                RentDto existingRent = rentService.getRentById(id);

                // If the ID exists and the corresponding rent is returned, increment count
                if (existingRent != null && existingRent.isReturn()==true) {
                    count++;
                } else if (existingRent != null && existingRent.isReturn()==false) {
                    // If the ID exists and the corresponding rent is not returned, return null
                    return null;
                } else {
                    // Break the loop if the ID doesn't exist
                    break;
                }
            }

            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
