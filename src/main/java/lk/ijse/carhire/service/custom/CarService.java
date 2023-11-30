package lk.ijse.carhire.service.custom;

import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.dto.CategoryDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.SuperService;

import java.time.LocalDate;
import java.util.List;

public interface CarService extends SuperService {
    public boolean saveCar(CarDto dto) throws Exception;
    public CarDto getCarById(String id) throws Exception;
    public List<CarDto> getAllCars() throws Exception;

    public boolean deleteCar(String id)throws Exception;


}
