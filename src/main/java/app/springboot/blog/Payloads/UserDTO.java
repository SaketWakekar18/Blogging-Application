package app.springboot.blog.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Integer user_id;
    @NotEmpty
    @Size(min = 5, message = "Name must be of atleast 5 characters")
    private String name;
    @Email(message = "Email address not valid")
    private String email;
    @NotEmpty
    @Size(min = 8, max = 18, message = "Password must be minimum of 8 characters and maximum of 16 characters")
    private String password;
    @NotEmpty(message = "About field cannot be empty")
    private String about;
    private Set<RoleDTO> roles = new HashSet<>();

}
