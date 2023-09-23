package lk.ijse.carhire.dao;

import lk.ijse.carhire.dao.custom.impl.CustomerDaoImpl;

public class DaoFactory {
    public static <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case CUSTOMER :
                return (T) new CustomerDaoImpl();
            default:
                return null;
        }
    }
}
