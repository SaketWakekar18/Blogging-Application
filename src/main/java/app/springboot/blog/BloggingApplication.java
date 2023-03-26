package app.springboot.blog;

import app.springboot.blog.Entity.Role;
import app.springboot.blog.Payloads.AppConstants;
import app.springboot.blog.Repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BloggingApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Role adminRole = new Role();
            adminRole.setRoleid(AppConstants.ADMIN_USER);
            adminRole.setRolename("ROLE_ADMIN");

            Role userRole = new Role();
            userRole.setRoleid(AppConstants.NORMAL_USER);
            userRole.setRolename("ROLE_NORMAL");

            List<Role> roles = List.of(adminRole, userRole);

            List<Role> result = this.roleRepository.saveAll(roles);

            result.forEach(r -> {
                r.getRolename();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
