package lk.ijse.carhire.dao;

import lk.ijse.carhire.dao.custom.impl.*;

public class DaoFactory {
    public static <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case CUSTOMER :
                return (T) new CustomerDaoImpl();

            case CATEGORY:
                return (T) new CategoryDaoImpl();

            case CAR:
                return (T) new CarDaoImpl();
            case RENT:
                return (T) new RentDaoImpl();
            case USER:
                return (T) new UserDaoImpl();
            default:
                return null;
        }
    }
}
