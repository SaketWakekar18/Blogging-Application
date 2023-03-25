package app.springboot.blog.Payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileResponse {
    private String fileName;
    private String message;

    public FileResponse(String fileName, String message) {
        this.fileName = fileName;
        this.message = message;
    }
}
