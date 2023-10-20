package lk.ijse.carhire.service.custom.impl;

import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.DaoType;
import lk.ijse.carhire.dao.custom.CarDao;
import lk.ijse.carhire.dao.custom.CategoryDao;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.entity.car.Car;
import lk.ijse.carhire.entity.car.CarCategory;
import lk.ijse.carhire.service.custom.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {
    private CarDao dao= DaoFactory.getDao(DaoType.CAR);
    private CategoryDao catDao=DaoFactory.getDao(DaoType.CATEGORY);

    @Override
    public boolean saveCar(CarDto dto) throws Exception {
        CarCategory category=catDao.get(dto.getCategory());
        Car car = new Car();
        car.setId(dto.getId());
        car.setVehicleNumber(dto.getVehicleNumber());
        car.setMake(dto.getMake());
        car.setColor(dto.getColor());
        car.setSeats(dto.getSeats());
        car.setYom(dto.getYom());
        car.setPricePerDay(dto.getPricePerDay());
        car.setMinDeposit(dto.getMinDeposit());
        car.setMaxKmPerDay(dto.getMaxKmPerDay());
        car.setRemarks(dto.getRemarks());
        car.setCategory(category);

        return dao.save(car);
    }

    @Override
    public CarDto getCarById(String id) throws Exception {
        try{
            Car c=dao.get(id);
            CarCategory category=c.getCategory();

            return  new CarDto(c.getId(),c.getVehicleNumber(),c.getMake(),c.getColor(),
                    c.getSeats(),c.getYom(),c.getPricePerDay(),c.getMinDeposit(),c.getMaxKmPerDay(),
                    c.getRemarks(), category.getName());

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<CarDto> getAllCars() throws Exception {
        return null;
    }

    @Override
    public boolean deleteCar(String id) throws Exception {
        return false;
    }
}
