package app.springboot.blog.Payloads;

import lombok.Data;

@Data
public class JWTAuthRequest {
    private String username;
    private String password;
}
