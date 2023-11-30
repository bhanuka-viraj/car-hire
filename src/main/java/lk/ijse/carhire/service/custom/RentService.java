package lk.ijse.carhire.service.custom;

import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.service.SuperService;

import java.time.LocalDate;
import java.util.List;

public interface RentService extends SuperService {
    public boolean saveRent(RentDto rentDto)throws Exception;
    public boolean deleteRent(String id) throws Exception;
    public RentDto getRentById(String id) throws Exception;
    public List<RentDto> getAllRents()throws Exception;
    public boolean setIsRented(String id,boolean value);
    public boolean setIsReturn(String id,boolean value);
    LocalDate getLastUpdateDate(String id);
    LocalDate getCreatedDate(String id);

    boolean setOverdueAmt(double overdueAmt,String id);
}
