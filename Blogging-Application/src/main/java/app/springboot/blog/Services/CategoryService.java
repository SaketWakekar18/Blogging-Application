package app.springboot.blog.Services;

import app.springboot.blog.Payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public CategoryDTO createCategory(CategoryDTO categoryDTO);
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer category_id);
    public void deleteCategory(Integer category_id);
    public CategoryDTO getCategoryById(Integer category_id);
    public List<CategoryDTO> getAllCategory();
}
