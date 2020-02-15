/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.dtos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author obinna.asuzu
 */
public class UploadFileResponse {

    private String name;
    private String link;
    private String type;
    private String mime;
    private long size;
    private String path;

    public UploadFileResponse(String name, String path, String fileDownloadUri, String fileType, String mime, long size) {
        this.name = name;
        this.path = path;
        this.link = fileDownloadUri;
        this.type = fileType;
        this.mime = mime;
        this.size = size;
    }

    public UploadFileResponse(File file) {
        this.name = file.getName();
        this.path = file.getAbsolutePath();
        this.type = FilenameUtils.getExtension(name);
        this.size = file.length();
        try {
            this.mime = Files.probeContentType(file.toPath());
        } catch (IOException ex) {
            Logger.getLogger(UploadFileResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.link = "/" + file.getName();
    }

    public UploadFileResponse updateLink(String link) {
        this.link = link + this.link;
        return this;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param fileName the name to set
     */
    public void setName(String fileName) {
        this.name = fileName;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param fileDownloadUri the link to set
     */
    public void setLink(String fileDownloadUri) {
        this.link = fileDownloadUri;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the mime
     */
    public String getMime() {
        return mime;
    }

    /**
     * @param mime the mime to set
     */
    public void setMime(String mime) {
        this.mime = mime;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }
}
