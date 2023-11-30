package lk.ijse.carhire.service;

import lk.ijse.carhire.service.custom.impl.*;

public class ServiceFactory {
    public static <T extends SuperService>T getService(ServiceType type){
        switch (type){
            case CUSTOMER :
               return (T) new CustomerServiceImpl();

            case CATEGORY:
                return (T) new CategoryServiceImpl();

            case CAR:
                return (T) new CarServiceImpl();
            case RENT:
                return (T) new RentServiceImpl();

            case USER:
                return (T) new UserServiceImpl();

            default:
                return null;
        }
    }
}
