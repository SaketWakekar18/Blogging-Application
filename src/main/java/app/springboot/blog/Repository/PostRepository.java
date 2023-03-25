package app.springboot.blog.Repository;

import app.springboot.blog.Entity.Category;
import app.springboot.blog.Entity.Post;
import app.springboot.blog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    public List<Post> findByUser(User user);
    public List<Post> findByCategory(Category category);
    public List<Post> findByTitleContaining(String keyword);
}
