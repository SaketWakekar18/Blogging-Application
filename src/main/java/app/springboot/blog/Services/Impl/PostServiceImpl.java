package app.springboot.blog.Services.Impl;

import app.springboot.blog.Entity.Category;
import app.springboot.blog.Entity.Post;
import app.springboot.blog.Entity.User;
import app.springboot.blog.Exceptions.ResourceNotFoundException;
import app.springboot.blog.Payloads.PostDTO;
import app.springboot.blog.Payloads.PostResponse;
import app.springboot.blog.Repository.CategoryRepository;
import app.springboot.blog.Repository.PostRepository;
import app.springboot.blog.Repository.UserRepository;
import app.springboot.blog.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, int user_id, int category_id) {
        User user = this.userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", user_id));
        Category category = this.categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", category_id));
        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setImageName("default.jpg");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedUser = this.postRepository.save(post);
        return this.modelMapper.map(savedUser, PostDTO.class);
    }

    @Override
    public PostDTO updatepost(PostDTO postDTO, int post_id) {
        Post post = this.postRepository.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", post_id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());
        Post updatedPost = this.postRepository.save(post);
        return this.modelMapper.map(updatedPost, PostDTO.class);

    }

    @Override
    public void deletePost(int post_id) {
        Post post = this.postRepository.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", post_id));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        List<PostDTO> postDTOS = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public PostResponse getAllPostsWithPagination(int pageNumber, int pageSize) {
        Page<Post> posts = this.postRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<PostDTO> postDTOS = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getAllPostsWithPaginationAndSorting(int pageNumber, int pageSize, String sortBy) {
        Page<Post> posts = this.postRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending()));
        List<PostDTO> postDTOS = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(int post_id) {
        Post post = this.postRepository.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", post_id));
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostByCategory(int category_id) {
        Category category = this.categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", category_id));
        List<Post> postByCategory = this.postRepository.findByCategory(category);
        List<PostDTO> postDTOS = postByCategory.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> getPostByUser(int user_id) {
        User user = this.userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", user_id));
        List<Post> postByUser = this.postRepository.findByUser(user);
        List<PostDTO> postDTOS = postByUser.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> searchPost = this.postRepository.findByTitleContaining(keyword);
        List<PostDTO> postDTOS = searchPost.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

}
