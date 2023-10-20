package lk.ijse.carhire.service.custom;

import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.service.SuperService;

import java.util.List;

public interface RentService extends SuperService {
    public boolean saveRent(RentDto rentDto)throws Exception;
    public boolean deleteRent(Long id) throws Exception;
    public RentDto getRentById(String id) throws Exception;
    public List<RentDto> getAllRents()throws Exception;
}
