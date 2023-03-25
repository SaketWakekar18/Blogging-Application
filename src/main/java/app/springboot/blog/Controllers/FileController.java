package app.springboot.blog.Controllers;

import app.springboot.blog.Payloads.FileResponse;
import app.springboot.blog.Services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${project.image}")
    private String path;
    @Autowired
    private FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadImage(@RequestParam("image") MultipartFile file){
        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path,file);
        } catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(null,"File cannot be uploaded due to server error !"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(new FileResponse(fileName,"File uploaded Successfully !"), HttpStatus.OK);
    }

    @GetMapping(value = "/download",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadFile(@RequestParam("filename") String filename, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = this.fileService.getResource(path,filename);
        httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream,httpServletResponse.getOutputStream());
    }
}
