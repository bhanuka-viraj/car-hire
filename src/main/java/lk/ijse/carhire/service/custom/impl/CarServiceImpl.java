package lk.ijse.carhire.service.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.DaoType;
import lk.ijse.carhire.dao.custom.CarDao;
import lk.ijse.carhire.dao.custom.CategoryDao;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.entity.car.Car;
import lk.ijse.carhire.entity.car.CarCategory;
import lk.ijse.carhire.service.custom.CarService;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {
    private CarDao dao= DaoFactory.getDao(DaoType.CAR);
    private CategoryDao catDao=DaoFactory.getDao(DaoType.CATEGORY);

    @Override
    public boolean saveCar(CarDto dto){
        try{
            Car existingCar=dao.get(dto.getId());
            CarCategory category = catDao.get(dto.getCategory());

            if(existingCar==null){
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
                car.setRented(dto.isRented());
                car.setRemarks(dto.getRemarks());
                car.setCategory(category);

                return dao.save(car);
            }else {
                existingCar.setId(dto.getId());
                existingCar.setVehicleNumber(dto.getVehicleNumber());
                existingCar.setMake(dto.getMake());
                existingCar.setColor(dto.getColor());
                existingCar.setSeats(dto.getSeats());
                existingCar.setYom(dto.getYom());
                existingCar.setPricePerDay(dto.getPricePerDay());
                existingCar.setMinDeposit(dto.getMinDeposit());
                existingCar.setMaxKmPerDay(dto.getMaxKmPerDay());
                existingCar.setRented(dto.isRented());
                existingCar.setRemarks(dto.getRemarks());
                existingCar.setCategory(category);

                return dao.update(existingCar);

            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CarDto getCarById(String id) throws Exception {
        try{
            Car c=dao.get(id);


            if (c!=null ){
                CarCategory category=c.getCategory();
                if (category!=null){
                    return  new CarDto(c.getId(),c.getVehicleNumber(),c.getMake(),c.getColor(),
                            c.getSeats(),c.getYom(),c.getPricePerDay(),c.getMinDeposit(),c.getMaxKmPerDay(),c.isRented(),
                            c.getRemarks(), category.getName());
                }else {
                    return null;
                }

            }else {
                return null;
            }


        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<CarDto> getAllCars() throws Exception {
        try {
            List<Car>list=dao.getAll();
            List<CarDto>dtoList=new ArrayList<>();

            for (Car c:list) {
                dtoList.add(new CarDto(c.getId(),c.getVehicleNumber(), c.getMake(), c.getColor(),c.getSeats(),c.getYom(),
                        c.getPricePerDay(),c.getMinDeposit(),c.getMaxKmPerDay(),c.isRented(), c.getRemarks(), c.getCategory().getName()));
            }

            return dtoList;

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public boolean deleteCar(String id) throws Exception {
        return dao.delete(id);
    }

}
