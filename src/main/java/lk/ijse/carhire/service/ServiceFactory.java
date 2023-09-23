package lk.ijse.carhire.service;

import lk.ijse.carhire.service.custom.impl.CustomerServiceImpl;

public class ServiceFactory {
    public static <T extends SuperService>T getService(ServiceType type){
        switch (type){
            case CUSTOMER :
               return (T) new CustomerServiceImpl();

            default:
                return null;
        }
    }
}
