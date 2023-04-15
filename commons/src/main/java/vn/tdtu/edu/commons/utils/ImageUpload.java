package vn.tdtu.edu.commons.utils;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

@Service
public class ImageUpload {

    private final String UPLOAD_FOLDER = "/zeus/commons/src/main/resources/static/images/";

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public boolean uploadImage(MultipartFile imageProduct, String fileName){
        boolean isUpload = false;
        try{
            saveFile(UPLOAD_FOLDER, fileName, imageProduct);

            isUpload = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return isUpload;
    }

}

