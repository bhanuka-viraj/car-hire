package lk.ijse.carhire.dao.custom;

import lk.ijse.carhire.dao.SuperDao;
import lk.ijse.carhire.entity.car.Car;
import lk.ijse.carhire.entity.car.CarCategory;

import java.util.List;

public interface CarDao extends SuperDao {
    public boolean save(Car car);
    public List<Car> getAll();

    public Car get(String id);

    public boolean delete( Car id);
}
