package lk.ijse.carhire.dao.custom;

import lk.ijse.carhire.dao.SuperDao;
import lk.ijse.carhire.entity.car.CarCategory;

import java.util.List;


public interface CategoryDao extends SuperDao {
    public boolean save(CarCategory category);
    public List<CarCategory> getAll();

    public CarCategory get(String name);

    public boolean delete( Long id);
}
