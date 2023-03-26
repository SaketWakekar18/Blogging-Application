package app.springboot.blog.Payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResponse {
    private String token;
}
