package app.springboot.blog.Controllers;

import app.springboot.blog.Payloads.APIResponse;
import app.springboot.blog.Payloads.CategoryDTO;
import app.springboot.blog.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO createcategoryDTO = this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<CategoryDTO>(createcategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable int category_id){
        CategoryDTO updatecategoryDTO = this.categoryService.updateCategory(categoryDTO,category_id);
        return ResponseEntity.ok(updatecategoryDTO);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable int category_id){
        this.categoryService.deleteCategory(category_id);
        return new ResponseEntity<APIResponse>(new APIResponse("Category deleted successfully !!",true),HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int category_id){
        CategoryDTO categoryDTO = this.categoryService.getCategoryById(category_id);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }

}
