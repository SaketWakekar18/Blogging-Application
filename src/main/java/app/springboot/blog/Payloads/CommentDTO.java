package app.springboot.blog.Payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private int commentid;
    private String content;

}
