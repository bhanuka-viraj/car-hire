package lk.ijse.carhire.dao.custom;

import lk.ijse.carhire.dao.SuperDao;
import lk.ijse.carhire.entity.customer.Customer;

import java.time.LocalDate;
import java.util.List;

public interface CustomerDao extends SuperDao {
    public boolean save(Customer customer);
    public boolean update(Customer customer);
    public List<Customer> getAll();

    public Customer get(String nic);

    public boolean delete( String nic);

    public LocalDate registrationDate(String nic);
}
