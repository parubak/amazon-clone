package com.example.amazonclone;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class Image {
    protected byte[] data;

    public Image(MultipartFile file) throws IOException {
        data = ImageUtil.compressImage(file.getBytes());
    }
}
