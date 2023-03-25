package app.springboot.blog.Services;

import app.springboot.blog.Payloads.PostDTO;
import app.springboot.blog.Payloads.PostResponse;

import java.util.List;

public interface PostService {
    public PostDTO createPost(PostDTO postDTO,int user_id,int category_id);
    public PostDTO updatepost(PostDTO postDTO,int post_id);
    public void deletePost(int post_id);
    public List<PostDTO> getAllPosts();
    public PostResponse getAllPostsWithPagination(int pageNumber, int pageSize);
    public PostResponse getAllPostsWithPaginationAndSorting(int pageNumber, int pageSize,String sortBy);
    public PostDTO getPostById(int post_id);
    public List<PostDTO> getPostByCategory(int category_id);
    public List<PostDTO> getPostByUser(int user_id);
    public List<PostDTO> searchPosts(String keyword);
}
