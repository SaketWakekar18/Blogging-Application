package app.springboot.blog.Services;

import app.springboot.blog.Payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer category_id);

    void deleteCategory(Integer category_id);

    CategoryDTO getCategoryById(Integer category_id);

    List<CategoryDTO> getAllCategory();
}
