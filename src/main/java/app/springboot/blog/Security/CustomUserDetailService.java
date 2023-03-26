package app.springboot.blog.Security;

import app.springboot.blog.Entity.User;
import app.springboot.blog.Exceptions.ResourceNotFoundException;
import app.springboot.blog.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database
        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Username", "Email:" + username, 0));
        return user;
    }
}
