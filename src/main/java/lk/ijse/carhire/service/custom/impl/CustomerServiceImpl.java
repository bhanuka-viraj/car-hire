package lk.ijse.carhire.service.custom.impl;

import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.DaoType;
import lk.ijse.carhire.dao.custom.CustomerDao;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.entity.Customer;
import lk.ijse.carhire.service.custom.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao dao=DaoFactory.getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws Exception {
        return dao.save(new Customer(dto.getNic(),
                dto.getFstname(),
                dto.getLstname(),
                dto.getDob(),
                dto.getAddressPerm(),
                dto.getAddressPost(),
                dto.getPostalCode(),
                dto.getCity(),
                dto.getCountry(),
                dto.getProvince(),
                dto.getCnumber(),
                dto.getEmail(),
                dto.getSalary() ,
                dto.getGender()));

    }

    @Override
    public CustomerDto getCustomerByNic(String nic) throws Exception {
       Customer c= dao.get(nic);

       return new CustomerDto(c.getNic(),c.getFstname(),c.getLstname(),c.getDob(),c.getAddressPerm(),c.getAddressPost(),
               c.getPostalCode(), c.getCity(), c.getCountry(), c.getProvince(), c.getCnumber(),
               c.getEmail(),c.getSalary(),c.getGender());
    }

    @Override
    public List<CustomerDto> getAllCustomers() throws Exception {
        List<Customer> list=dao.getAll();
        List<CustomerDto>dtoList=new ArrayList<>();

        for (Customer c:list) {
            dtoList.add(new CustomerDto(c.getNic(),c.getFstname(),c.getLstname(),c.getDob(),c.getAddressPerm(),c.getAddressPost(),
                    c.getPostalCode(), c.getCity(), c.getCountry(), c.getProvince(), c.getCnumber(),
                    c.getEmail(),c.getSalary(),c.getGender()));
        }


        return dtoList;
    }

    @Override
    public boolean deleteCustomer(Integer nic) throws Exception {
        return dao.delete(nic);
    }
}
