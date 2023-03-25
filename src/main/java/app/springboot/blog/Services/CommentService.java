package app.springboot.blog.Services;

import app.springboot.blog.Payloads.CommentDTO;

public interface CommentService {
    public CommentDTO createComment(CommentDTO commentDTO,int postid,int userid);
    public void deleteComment(int commentid);

}
