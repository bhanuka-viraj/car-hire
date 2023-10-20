package lk.ijse.carhire.service.custom;

import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.SuperService;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService extends SuperService {
    public boolean saveCustomer(CustomerDto customerDto) throws Exception;
    public CustomerDto getCustomerByNic(String nic) throws Exception;
    public List<CustomerDto> getAllCustomers() throws Exception;

    public boolean deleteCustomer(String nic)throws Exception;
    public LocalDate registrationDate(String nic) throws Exception;
}
