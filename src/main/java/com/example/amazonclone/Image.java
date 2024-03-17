package com.example.amazonclone;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
public class Image {
    public byte[] image;

    public Image(MultipartFile file) throws IOException {
        image = ImageUtil.compressImage(file.getBytes());
    }

    public Image(byte[] data) {
        this.image = data;
    }
}
