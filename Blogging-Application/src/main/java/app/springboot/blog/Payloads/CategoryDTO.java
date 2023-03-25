package app.springboot.blog.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Integer category_id;
    @NotBlank
    @Size(min = 4,message = "Title cannot be less than 4 letters")
    private String categoryTitle;
    @NotBlank
    @Size(min = 5, max = 50,message = "Description should be of minimum 5 and maximum 50 letters")
    private String categoryDescription;

}
