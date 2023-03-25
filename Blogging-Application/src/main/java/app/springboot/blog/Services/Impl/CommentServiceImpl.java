package app.springboot.blog.Services.Impl;

import app.springboot.blog.Entity.Comment;
import app.springboot.blog.Entity.Post;
import app.springboot.blog.Entity.User;
import app.springboot.blog.Exceptions.ResourceNotFoundException;
import app.springboot.blog.Payloads.CommentDTO;
import app.springboot.blog.Repository.CommentRepository;
import app.springboot.blog.Repository.PostRepository;
import app.springboot.blog.Repository.UserRepository;
import app.springboot.blog.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDTO createComment(CommentDTO commentDTO, int postid,int userid) {
        Post post = this.postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post","ID",postid));
        User user = this.userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("User","ID",userid));
        Comment comment = this.modelMapper.map(commentDTO,Comment.class);
        comment.setPost(post);
        comment.setUsers(user);
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment,CommentDTO.class);
    }

    @Override
    public void deleteComment(int commentid) {
        Comment comment = this.commentRepository.findById(commentid).orElseThrow(()->new ResourceNotFoundException("Comment","ID",commentid));
        this.commentRepository.delete(comment);
    }
}
