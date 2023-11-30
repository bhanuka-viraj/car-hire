package lk.ijse.carhire.service.custom.impl;

import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.DaoType;
import lk.ijse.carhire.dao.custom.UserDao;
import lk.ijse.carhire.dto.UserDto;
import lk.ijse.carhire.entity.User;
import lk.ijse.carhire.service.custom.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=DaoFactory.getDao(DaoType.USER);
    @Override
    public boolean save(UserDto userDto) {
        User user=new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setContactNo(userDto.getContactNo());
        user.setUsername(userDto.getUsername());

        return userDao.save(user);
    }

    @Override
    public UserDto getUser(String username) {
        User user=userDao.getUser(username);

        return new UserDto(user.getFirstName(),user.getLastName(),user.getContactNo(),user.getUsername(),user.getPassword());
    }
}
