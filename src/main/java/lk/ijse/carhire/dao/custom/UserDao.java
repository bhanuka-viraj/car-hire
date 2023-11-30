package lk.ijse.carhire.dao.custom;

import lk.ijse.carhire.dao.SuperDao;
import lk.ijse.carhire.entity.User;

public interface UserDao extends SuperDao {
    public boolean save (User  user);

    User getUser(String username);
}
