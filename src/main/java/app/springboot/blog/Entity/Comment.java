package app.springboot.blog.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentid;
    private String content;
    @ManyToOne
    @JoinColumn(name = "postid")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User users;
}
