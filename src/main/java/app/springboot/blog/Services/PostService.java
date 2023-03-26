package app.springboot.blog.Services;

import app.springboot.blog.Payloads.PostDTO;
import app.springboot.blog.Payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, int user_id, int category_id);

    PostDTO updatepost(PostDTO postDTO, int post_id);

    void deletePost(int post_id);

    List<PostDTO> getAllPosts();

    PostResponse getAllPostsWithPagination(int pageNumber, int pageSize);

    PostResponse getAllPostsWithPaginationAndSorting(int pageNumber, int pageSize, String sortBy);

    PostDTO getPostById(int post_id);

    List<PostDTO> getPostByCategory(int category_id);

    List<PostDTO> getPostByUser(int user_id);

    List<PostDTO> searchPosts(String keyword);
}
