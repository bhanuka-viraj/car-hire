package lk.ijse.carhire.controller.rent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.service.custom.RentService;

import java.io.IOException;

public class ReturnCarController {
    @FXML
    private Label lblCustName;

    @FXML
    private Label lblNic;

    @FXML
    private Label lblAdvanced;

    @FXML
    private Label lblDeposit;

    @FXML
    private Label lblCar;

    @FXML
    private Label lblBalancePayment;
    @FXML
    private Label lblOverdue;

    private long overdueDays;
    private double overdueAmt;

    private RentDto rentDto;
    private RentService rentService= ServiceFactory.getService(ServiceType.RENT);

    public void setRentDto(RentDto rentDto) {
        this.rentDto = rentDto;
        setDetails();
    }
    public void setOverdueDays(long overdueDays){
        this.overdueDays=overdueDays;
    }
    public void setOverdueAmt(double overdueAmt){
        this.overdueAmt=overdueAmt;
    }

    private void setDetails() {
        try {
            if (rentDto != null) {
                CustomerDto customerDto = rentDto.getCustomerDto();

//                System.out.println("*************************"+rentDto.getCustomerDto().getFstname()+"******");

                lblCar.setText(rentDto.getCarDto().getVehicleNumber());
                lblAdvanced.setText(String.valueOf(rentDto.getAdvancedPayment()));
                lblCustName.setText(customerDto.getFstname() + " " + customerDto.getLstname());
                lblNic.setText(customerDto.getNic());
                lblDeposit.setText(String.valueOf(rentDto.getRefundableDeposit()));

                System.out.println("over due days: "+overdueDays+"overdue amt: "+overdueAmt+"********************");
                if (overdueDays>0){
                    double totalBalance=rentDto.getBalance()+overdueAmt;
                    lblOverdue.setText(String.valueOf(overdueAmt));
                    lblBalancePayment.setText(String.valueOf(totalBalance));
                }else {
                    lblOverdue.setText("0");
                    lblBalancePayment.setText(String.valueOf(rentDto.getBalance()));
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"rentdto is null").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {

        try {
                boolean setRented=rentService.setIsRented(rentDto.getCarDto().getId(),false);
                boolean setReturned=rentService.setIsReturn(rentDto.getId(),true);
                if(overdueDays>0){
                    rentService.setOverdueAmt(overdueAmt,rentDto.getId());
                }

                if(setReturned && setRented){
                    new Alert(Alert.AlertType.CONFIRMATION,"Rent returned successfully !").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Error occurred while returning !").show();
                }



        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

}
