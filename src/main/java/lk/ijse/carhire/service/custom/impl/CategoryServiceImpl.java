package lk.ijse.carhire.service.custom.impl;

import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.DaoType;
import lk.ijse.carhire.dao.custom.CategoryDao;
import lk.ijse.carhire.dto.CategoryDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.entity.car.CarCategory;
import lk.ijse.carhire.entity.customer.Customer;
import lk.ijse.carhire.service.custom.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao= DaoFactory.getDao(DaoType.CATEGORY);
    @Override
    public boolean saveCategory(CategoryDto dto) throws Exception {
        CarCategory carCategory=new CarCategory();
        carCategory.setName(dto.getCategoryName());
        carCategory.setAvailableCars(0);

        return dao.save(carCategory);

    }

    @Override
    public CategoryDto getCategoryByName(String name) throws Exception {
        try {
            CarCategory carCategory = dao.get(name);

            return new CategoryDto(carCategory.getId(), carCategory.getName(), carCategory.getAvailableCars());


        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public List<CategoryDto> getAllCategories() throws Exception {
        try {
            List<CarCategory> list = dao.getAll();
            List<CategoryDto> dtoList = new ArrayList<>();

            for (CarCategory c : list) {
                dtoList.add(new CategoryDto(c.getId(), c.getName(), c.getAvailableCars()));
            }


            return dtoList;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteCategory(Integer id) throws Exception {
        return false;
    }
}
