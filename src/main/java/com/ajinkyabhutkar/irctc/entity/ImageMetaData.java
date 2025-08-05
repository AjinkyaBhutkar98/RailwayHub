package com.ajinkyabhutkar.irctc.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ImageMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;

    private String fileId;

    private String contentType;

    private LocalDateTime createTime;

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    private Long fileSize;

    public ImageMetaData() {
    }


    public ImageMetaData(int id, String fileName, String fileId, String contentType, LocalDateTime createTime, Long fileSize) {
        this.id = id;
        this.fileName = fileName;
        this.fileId = fileId;
        this.contentType = contentType;
        this.createTime = createTime;
        this.fileSize = fileSize;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
