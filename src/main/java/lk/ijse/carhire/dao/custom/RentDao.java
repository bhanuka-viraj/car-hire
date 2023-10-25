package lk.ijse.carhire.dao.custom;

import lk.ijse.carhire.dao.SuperDao;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.entity.car.Rent;

import java.util.List;

public interface RentDao extends SuperDao {
    public boolean save(Rent rent);
    public boolean delete(Long id) ;
    public Rent getRent(String id) ;
    public List<Rent> getRents();
}
