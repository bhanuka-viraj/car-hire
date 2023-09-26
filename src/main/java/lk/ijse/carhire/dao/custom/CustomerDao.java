package lk.ijse.carhire.dao.custom;

import lk.ijse.carhire.dao.SuperDao;
import lk.ijse.carhire.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerDao extends SuperDao {
    public boolean save(Customer customer)throws Exception;
    public List<Customer> getAll() throws Exception;

    public Customer get(String nic) throws Exception;

    public boolean delete( Integer nic)throws Exception;
}
