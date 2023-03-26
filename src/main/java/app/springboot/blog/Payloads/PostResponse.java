package app.springboot.blog.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDTO> content;
    private int pageSize;
    private int pageNumber;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;
}
