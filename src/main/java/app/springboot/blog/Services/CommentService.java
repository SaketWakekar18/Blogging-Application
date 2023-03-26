package app.springboot.blog.Services;

import app.springboot.blog.Payloads.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, int postid, int userid);

    void deleteComment(int commentid);

}
