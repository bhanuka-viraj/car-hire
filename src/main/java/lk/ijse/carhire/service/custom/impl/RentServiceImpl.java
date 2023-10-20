package lk.ijse.carhire.service.custom.impl;

import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.DaoType;
import lk.ijse.carhire.dao.custom.CarDao;
import lk.ijse.carhire.dao.custom.CustomerDao;
import lk.ijse.carhire.dao.custom.RentDao;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.entity.car.Car;
import lk.ijse.carhire.entity.car.Rent;
import lk.ijse.carhire.entity.customer.Customer;
import lk.ijse.carhire.service.custom.RentService;

import java.util.List;

public class RentServiceImpl implements RentService {
    RentDao rentDao = DaoFactory.getDao(DaoType.RENT);
    CustomerDao customerDao=DaoFactory.getDao(DaoType.CUSTOMER);
    CarDao carDao=DaoFactory.getDao(DaoType.CAR);
    @Override
    public boolean saveRent(RentDto rentDto) throws Exception {
        Rent rent = new Rent();
        Car car=carDao.get(rentDto.getCarDto().getId());
        Customer customer=customerDao.get(rentDto.getCustomerDto().getNic());

        rent.setId(rentDto.getId());
        rent.setFromDate(rentDto.getFromDate());
        rent.setToDate(rentDto.getToDate());
        rent.setTotal(rentDto.getTotal());
        rent.setReturn(rentDto.isReturn());
        rent.setBalance(rentDto.getBalance());
        rent.setRefundableDeposit(rentDto.getRefundableDeposit());
        rent.setAdvancedPayment(rentDto.getAdvancedPayment());
        rent.setPerDayRent(rentDto.getPerDayRent());
        rent.setCar(car);
        rent.setCustomer(customer);


        return rentDao.save(rent);
    }

    @Override
    public boolean deleteRent(Long id) throws Exception {
        return rentDao.delete(id);
    }

    @Override
    public RentDto getRentById(String id) throws Exception {

        return null;
    }

    @Override
    public List<RentDto> getAllRents() throws Exception {
        return null;
    }
}
