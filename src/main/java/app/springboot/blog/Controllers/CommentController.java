package app.springboot.blog.Controllers;

import app.springboot.blog.Payloads.APIResponse;
import app.springboot.blog.Payloads.CommentDTO;
import app.springboot.blog.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/create/post/{postid}/user/{userid}")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
                                                    @PathVariable int postid,
                                                    @PathVariable int userid
    ){
        CommentDTO comment = this.commentService.createComment(commentDTO,postid,userid);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }
    @DeleteMapping("/delete/{commentid}")
    public ResponseEntity<APIResponse> deleteComment(@PathVariable int commentid){
        this.commentService.deleteComment(commentid);
        return new ResponseEntity<>(new APIResponse("Comment deleted successfully",true),HttpStatus.OK);
    }
}
