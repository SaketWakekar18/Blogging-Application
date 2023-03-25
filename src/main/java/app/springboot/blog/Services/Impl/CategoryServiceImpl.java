package app.springboot.blog.Services.Impl;

import app.springboot.blog.Entity.Category;
import app.springboot.blog.Exceptions.ResourceNotFoundException;
import app.springboot.blog.Payloads.CategoryDTO;
import app.springboot.blog.Repository.CategoryRepository;
import app.springboot.blog.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO,Category.class);
        Category createCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(createCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer category_id) {
        Category category = this.categoryRepository.findById(category_id).orElseThrow(()->new ResourceNotFoundException("Category","ID",category_id));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer category_id) {
        Category category = this.categoryRepository.findById(category_id).orElseThrow(()->new ResourceNotFoundException("Category","ID",category_id));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getCategoryById(Integer category_id) {
        Category category = this.categoryRepository.findById(category_id).orElseThrow(()->new ResourceNotFoundException("Category","ID",category_id));
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> category = this.categoryRepository.findAll();
        List<CategoryDTO> allCategory = category.stream().map(e->this.modelMapper.map(e,CategoryDTO.class)).collect(Collectors.toList());
        return allCategory;
    }
}
