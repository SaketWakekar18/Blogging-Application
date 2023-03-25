package app.springboot.blog.Repository;

import app.springboot.blog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

//    @Query(value = "DELETE FROM user_role where user=:user_id")
//    User deleteUserNativeNamedParam(@Param("user_id") int user_id);

}
