package app.springboot.blog.Payloads;

import app.springboot.blog.Entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private int postid;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDTO category;
    private UserDTO user;
    private Set<CommentDTO> comments = new HashSet<>();

}
