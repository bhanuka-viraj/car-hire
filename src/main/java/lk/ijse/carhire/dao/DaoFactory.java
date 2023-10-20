package lk.ijse.carhire.dao;

import lk.ijse.carhire.dao.custom.impl.CarDaoImpl;
import lk.ijse.carhire.dao.custom.impl.CategoryDaoImpl;
import lk.ijse.carhire.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.carhire.dao.custom.impl.RentDaoImpl;

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
            default:
                return null;
        }
    }
}
