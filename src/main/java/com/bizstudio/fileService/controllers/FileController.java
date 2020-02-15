/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.controllers;

import com.bizstudio.fileService.dtos.FileDeleteResponse;
import com.bizstudio.fileService.dtos.QueryParam;
import com.bizstudio.fileService.dtos.QueryResult;
import com.bizstudio.fileService.dtos.UploadFileResponse;
import com.bizstudio.fileService.exceptions.RecordNotFoundException;
import com.bizstudio.fileService.services.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import static java.util.stream.Collectors.toList;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author obinna.asuzu
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v2/files")
@Api(value = "Files API", description = "Files Rest API", tags = {"Files API"})
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @ApiOperation(value = "Upload file")
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = UploadFileResponse.class, message = "")
    })
    @PostMapping("/{username}/upload")
    public UploadFileResponse uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable String username) {

        UploadFileResponse fileResponse = fileStorageService.storeFile(file, username, "/images");

        return fileResponse;
    }

    @ApiOperation(value = "Upload multiple files")
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = UploadFileResponse.class, responseContainer = "List", message = "")
    })
    @PostMapping("/{username}/uploadMultiple")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @PathVariable String username) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, username))
                .collect(toList());
    }

    @ApiOperation(value = "get file by name")
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Resource.class, message = "")
    })
    @GetMapping("/download/{username}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String username,
            @PathVariable String fileName,
            HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(username + "/images/" + fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
    @ApiOperation(value = "dlete file by name")
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Map.class, message = "")
    })
    @DeleteMapping("/{username}")
    public ResponseEntity<FileDeleteResponse> deleteFile(
            @PathVariable String username,
            @RequestParam String filename) {
        Boolean deleted = Boolean.FALSE;
        try {
            deleted = fileStorageService.deleteFile(filename, username, "images");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.ok(new FileDeleteResponse(deleted,filename));
    }

    @PostMapping("/find")
    public ResponseEntity getByArtisans(@RequestBody QueryParam queryParams, HttpServletRequest request) throws RecordNotFoundException, Exception {
        int page = queryParams.getPageNumber() == null ? 0 : queryParams.getPageNumber();
        int size = queryParams.getPageSize() == null ? 0 : queryParams.getPageSize();
        String username = queryParams.getFilter().get("id");
        Page<UploadFileResponse> result = fileStorageService.getFilesByUsername(username, "/images", page, size);
        QueryResult<UploadFileResponse> queryResult = new QueryResult<UploadFileResponse>(result);
        return ResponseEntity.ok().body(queryResult);
    }

}















