package lk.ijse.carhire.service.custom.impl;

import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.DaoType;
import lk.ijse.carhire.dao.custom.CarDao;
import lk.ijse.carhire.dao.custom.CustomerDao;
import lk.ijse.carhire.dao.custom.RentDao;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.dto.CustomerDto;
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

        Rent rent=rentDao.getRent(id);

        if(rent!=null){
            Car car=carDao.get(rent.getCar().getId());

            CarDto carDto=new CarDto(car.getId(), car.getVehicleNumber(), car.getMake(), car.getColor(), car.getSeats(),
                    car.getYom(), car.getPricePerDay(), car.getMinDeposit(), car.getMaxKmPerDay(), car.getRemarks(), car.getCategory().getName());

            Customer customer=customerDao.get(rent.getCustomer().getNic());

            CustomerDto customerDto=new CustomerDto(customer.getNic(), customer.getFstname(), customer.getLstname(), customer.getDob(),
                    customer.getAddressPerm(), customer.getAddressPost(), customer.getPostalCode(), customer.getCity(),
                    customer.getCountry(), customer.getProvince(), customer.getCnumber(), customer.getEmail(),customer.getSalary(), customer.getGender());


            return new RentDto(rent.getId(), rent.getFromDate(),rent.getToDate(),rent.getTotal(),rent.isReturn(),
                    rent.getBalance(), rent.getRefundableDeposit(), rent.getAdvancedPayment(), rent.getPerDayRent(),carDto,customerDto);
        }

        return null;
    }

    @Override
    public List<RentDto> getAllRents() throws Exception {
        return null;
    }
}
