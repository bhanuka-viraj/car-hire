package lk.ijse.carhire.dao.custom;

import lk.ijse.carhire.dao.SuperDao;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.entity.car.Rent;

import java.time.LocalDate;
import java.util.List;

public interface RentDao extends SuperDao {
    public boolean save(Rent rent);
    public boolean update(Rent rent);
    public boolean delete(String id) ;
    public Rent getRent(String id) ;
    public List<Rent> getRents();

    boolean setIsReturn(String id, boolean value);
    LocalDate getLastUpdatedDate (String id);
    LocalDate getCreatedDate (String id);

    boolean setOverdueAmt(double overdueAmt,String id);
}
