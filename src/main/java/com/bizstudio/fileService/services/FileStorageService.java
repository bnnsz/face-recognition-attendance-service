/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.services;

import com.bizstudio.fileService.controllers.FileController;
import com.bizstudio.fileService.dtos.UploadFileResponse;
import com.bizstudio.fileService.exceptions.FileStorageException;
import com.bizstudio.fileService.exceptions.MyFileNotFoundException;
import com.bizstudio.fileService.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    

    @Autowired
    private HttpServletRequest request;

    String linkRoot;
    String contextPath;

    @Autowired
    public FileStorageService(Environment env) {
        this.fileStorageLocation = Paths.get(env.getProperty("application.resources.path"))
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public UploadFileResponse storeFile(MultipartFile file, String username, String folderPath) {
        try {
            Files.createDirectories(this.fileStorageLocation.resolve(username + folderPath));
        } catch (Exception ex) {
        }
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation
                    .resolve(username + folderPath)
                    .resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            UploadFileResponse fileResponse = new UploadFileResponse(targetLocation.toFile());
            return fileResponse.updateLink(getLink() + "/" + username);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation
                    .resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public Page<UploadFileResponse> getFilesByUsername(String username, String folderPath, int page, int size) throws ServiceException {
        List<UploadFileResponse> result = new ArrayList<>();
        
        final String userfolder[] = {"system"};
        if(!userfolder[0].equalsIgnoreCase(username)){
            userfolder[0] = username;
        }
        
        Path folder = this.fileStorageLocation
                .resolve(userfolder[0] + folderPath).normalize();

        try (Stream<Path> walk = Files.walk(folder)) {
            result = walk
                    .filter(path -> path.toFile().isFile())
                    .map(path -> {
                        UploadFileResponse file = new UploadFileResponse(path.toFile());
                        return file.updateLink(getLink() + "/" + userfolder[0]);
                    }).collect(toList());
            return new PageImpl<UploadFileResponse>(result, PageRequest.of(page, size), result.size());
        } catch (IOException e) {
        }
        return new PageImpl<UploadFileResponse>(result, PageRequest.of(page, size), result.size());
    }

    public boolean deleteFile(String fileName, String username, String folderPath) throws IOException {
        Path path = this.fileStorageLocation
                .resolve(username)
                .resolve(folderPath)
                .resolve(fileName);
        System.out.println("delete file: " + path.toString());
        return Files.deleteIfExists(path);
    }
    
    public boolean deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        System.out.println("delete file: " + path.toString());
        return Files.deleteIfExists(path);
    }

    public String getLink() {
//        if (linkRoot == null) {
//            try {
//                String contextPath = request.getContextPath();
//                URL url = new URL(request.getRequestURL().toString());
//                String protocol = url.getProtocol();
//                int serverPort = url.getPort();
//                String host = url.getHost();
//                String port = serverPort == 80 || serverPort == 443 ? "" : ":" + serverPort;
//                String context = (contextPath == null || contextPath.isEmpty() ? "" : "/" + contextPath) + "/api/v2/files/download";
//                linkRoot = protocol + "://" + host + port + context;
//            } catch (MalformedURLException ex) {
//                java.util.logging.Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return "http://167.172.18.200/api/v2/files/download";
    }
}













