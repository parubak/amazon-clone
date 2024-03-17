package com.example.amazonclone.dto;

import com.example.amazonclone.Image;
import com.example.amazonclone.models.ProductImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ProductImageDto extends Image implements DtoEntity<ProductImage> {
    @Getter @Setter
    private Long productId;

    public ProductImageDto(ProductImage entity) {
        super(entity.getImage());
        this.productId = entity.getProduct().getId();
    }

    public ProductImageDto(MultipartFile file, Long productId) throws IOException {
        super(file);
        this.productId = productId;
    }

    @Override
    public ProductImage buildEntity() {

        ProductImage productImage = new ProductImage();
        productImage.setImage(image);

        return productImage;
    }
}
