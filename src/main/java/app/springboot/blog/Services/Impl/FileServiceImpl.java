package app.springboot.blog.Services.Impl;

import app.springboot.blog.Services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
        String name = multipartFile.getOriginalFilename();

        String randomID = UUID.randomUUID().toString();
        String fileNewName = randomID.concat(name.substring(name.lastIndexOf(".")));

        String filePath = path + File.separator + fileNewName;

        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));

        return fileNewName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullpath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullpath);
        return inputStream;
    }
}
