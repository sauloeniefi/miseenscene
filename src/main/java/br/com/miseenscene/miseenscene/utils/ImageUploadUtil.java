package br.com.miseenscene.miseenscene.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageUploadUtil {

    public static void saveFile(String uploadDir, String imageName, MultipartFile image) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = image.getInputStream()) {
            Path imagePath = uploadPath.resolve(imageName);
            Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioex) {
            throw new IOException("Não foi possível salvar a imagem: " + imageName, ioex);
        }
    }
}
