package app.springboot.blog.Controllers;

import app.springboot.blog.Payloads.*;
import app.springboot.blog.Services.FileService;
import app.springboot.blog.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Value("${project.image}")
    private String path;
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @PostMapping("user/{user_id}/category/{category_id}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable int user_id, @PathVariable int category_id){
        PostDTO createdPostDTO = this.postService.createPost(postDTO,user_id,category_id);
        return new ResponseEntity<PostDTO>(createdPostDTO, HttpStatus.CREATED);
    }
    @GetMapping("user/{user_id}/posts")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable int user_id){
        List<PostDTO> postDTOS = this.postService.getPostByUser(user_id);
        return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }
    @GetMapping("category/{category_id}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable int category_id){
        List<PostDTO> postDTOS = this.postService.getPostByCategory(category_id);
        return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }
    @GetMapping("/posts/pagination")
    public ResponseEntity<PostResponse> getAllPostsWithPagination(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
                                                     @RequestParam(value = "pageSize",defaultValue = "1",required = false)int pageSize)
    {
        return ResponseEntity.ok(this.postService.getAllPostsWithPagination(pageNumber,pageSize));
    }
    @GetMapping("/posts/sorting/user")
    public ResponseEntity<PostResponse> getAllPostsByUserWithPaginationAndSorting(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)int pageNumber,
                                                                        @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)int pageSize,
                                                                        @RequestParam (value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy)
    {
        return ResponseEntity.ok(this.postService.getAllPostsWithPaginationAndSorting(pageNumber,pageSize,sortBy));
    }
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return ResponseEntity.ok(this.postService.getAllPosts());
    }
    @GetMapping("/posts/{post_id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int post_id){
        PostDTO postDTO = this.postService.getPostById(post_id);
        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<APIResponse> deletePost(@PathVariable int post_id){
        this.postService.deletePost(post_id);
        return new ResponseEntity<APIResponse>(new APIResponse("Post deleted succesfully!",true),HttpStatus.OK);
    }

    @PutMapping("/posts/{post_id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable int post_id){
        PostDTO postDTOs = this.postService.updatepost(postDTO,post_id);
        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping("/posts/search")
    public ResponseEntity<List<PostDTO>> searchPost(@RequestParam(value = "keyword") String keyword){
        return ResponseEntity.ok(this.postService.searchPosts(keyword));
    }

    @PostMapping("/image/upload")
    public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile file,
                                                    @RequestParam("postid")int postid) throws IOException {
        String fileName = this.fileService.uploadImage(path, file);
        PostDTO postDTO = this.postService.getPostById(postid);
        postDTO.setImageName(fileName);
        PostDTO updatedPost = this.postService.updatepost(postDTO, postid);

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/image/download")
    public void downloadImage(@RequestParam("image") String imageName, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = this.fileService.getResource(path,imageName);
        httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream,httpServletResponse.getOutputStream());
    }
}
