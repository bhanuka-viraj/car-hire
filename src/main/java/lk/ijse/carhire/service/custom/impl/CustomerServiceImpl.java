package lk.ijse.carhire.service.custom.impl;

import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.DaoType;
import lk.ijse.carhire.dao.custom.CustomerDao;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.entity.car.Rent;
import lk.ijse.carhire.entity.customer.Customer;
import lk.ijse.carhire.service.custom.CustomerService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao dao = DaoFactory.getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws Exception {
        try {
            Customer existingCustomer=dao.get(dto.getNic());

            if(existingCustomer==null){
                Customer customer = new Customer();
                customer.setNic(dto.getNic());
                customer.setFstname(dto.getFstname());
                customer.setLstname(dto.getLstname());
                customer.setRents(rents());
                customer.setDob(dto.getDob());
                customer.setAddressPerm(dto.getAddressPerm());
                customer.setAddressPost(dto.getAddressPost());
                customer.setPostalCode(dto.getPostalCode());
                customer.setCity(dto.getCity());
                customer.setCountry(dto.getCountry());
                customer.setProvince(dto.getProvince());
                customer.setCnumber(dto.getCnumber());
                customer.setEmail(dto.getEmail());
                customer.setSalary(dto.getSalary());
                customer.setGender(dto.getGender());

                return dao.save(customer);
            }else {
                // Update an existing customer
                existingCustomer.setNic(dto.getNic());
                existingCustomer.setFstname(dto.getFstname());
                existingCustomer.setLstname(dto.getLstname());
                existingCustomer.setRents(rents()); // You may need to handle updates to the rent list
                existingCustomer.setDob(dto.getDob());
                existingCustomer.setAddressPerm(dto.getAddressPerm());
                existingCustomer.setAddressPost(dto.getAddressPost());
                existingCustomer.setPostalCode(dto.getPostalCode());
                existingCustomer.setCity(dto.getCity());
                existingCustomer.setCountry(dto.getCountry());
                existingCustomer.setProvince(dto.getProvince());
                existingCustomer.setCnumber(dto.getCnumber());
                existingCustomer.setEmail(dto.getEmail());
                existingCustomer.setSalary(dto.getSalary());
                existingCustomer.setGender(dto.getGender());

                return dao.update(existingCustomer);

            }

        } catch (Exception e) {
            throw e;
        }

    }

    public List<Rent>rents(){
        return null;
    }

    @Override
    public CustomerDto getCustomerByNic(String nic) throws Exception {
        try {

            Customer c = dao.get(nic);
            return new CustomerDto(c.getNic(), c.getFstname(), c.getLstname(), c.getDob(), c.getAddressPerm(), c.getAddressPost(),
                    c.getPostalCode(), c.getCity(), c.getCountry(), c.getProvince(), c.getCnumber(),
                    c.getEmail(), c.getSalary(), c.getGender());

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<CustomerDto> getAllCustomers() throws Exception {
        try {
            List<Customer> list = dao.getAll();
            List<CustomerDto> dtoList = new ArrayList<>();

            for (Customer c : list) {
                dtoList.add(new CustomerDto(c.getNic(), c.getFstname(), c.getLstname(), c.getDob(), c.getAddressPerm(), c.getAddressPost(),
                        c.getPostalCode(), c.getCity(), c.getCountry(), c.getProvince(), c.getCnumber(),
                        c.getEmail(), c.getSalary(), c.getGender()));
            }


            return dtoList;

        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    public boolean deleteCustomer(String nic) throws Exception {
        try {
            return dao.delete(nic);

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public LocalDate registrationDate(String nic) throws Exception {
        try{
            return dao.registrationDate(nic);
        }catch (Exception e){
            throw e;
        }
    }
}
