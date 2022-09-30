package br.com.miseenscene.miseenscene.service.impl;

import br.com.miseenscene.miseenscene.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // File name
        String fileName = file.getOriginalFilename();
        String randomUUID = UUID.randomUUID().toString();
        String newFileName = randomUUID.concat(fileName.substring(fileName.lastIndexOf(".")));

        //Fullpath
        //String filePath = path + File.separator + newFileName;


        //create folder if not created
        File newFile = new File(path);
        if (!newFile.exists()) {
            newFile.mkdir();
        }

        //file copy
        InputStream inputStream = file.getInputStream();
        Path filePath = Path.of(path + File.separator + newFileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        return newFileName;
    }
}
