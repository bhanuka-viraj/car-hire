package lk.ijse.carhire.service.custom;

import lk.ijse.carhire.dto.CategoryDto;
import lk.ijse.carhire.service.SuperService;

import java.util.List;

public interface CategoryService extends SuperService {
    public boolean saveCategory(CategoryDto dto) throws Exception;
    public CategoryDto getCategoryByName(String name) throws Exception;
    public List<CategoryDto> getAllCategories() throws Exception;

    public boolean deleteCategory(Integer id)throws Exception;
}
