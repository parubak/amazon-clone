package com.example.amazonclone.dto;

import com.example.amazonclone.models.ProductReview;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
public class ProductReviewDto implements DtoEntity<ProductReview, Long> {

    @Nullable
    private Long id;

    private String username;

    private Double mark;

    @Nullable
    private String reviewText;

    private Long productId;

    public ProductReviewDto(ProductReview entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.mark = entity.getMark();
        this.reviewText = entity.getReviewText();
        this.productId = entity.getProduct().getId();
    }

    public ProductReviewDto(String username, Double mark, Long productId) {
        this.username = username;
        this.mark = mark;
        this.productId = productId;
    }

    public ProductReviewDto(String username, Double mark, @Nullable String reviewText, Long productId) {
        this(username, mark, productId);
        this.reviewText = reviewText;
    }

    @Override
    public ProductReview buildEntity() {
        ProductReview productReview = new ProductReview();

        if(id != null)
            productReview.setId(id);
        productReview.setUsername(username);
        productReview.setMark(mark);
        if(reviewText != null)
            productReview.setReviewText(reviewText);

        return productReview;
    }

    @Override
    public ProductReview buildEntity(Long id) {
        ProductReview productReview = buildEntity();
        productReview.setId(id);
        return productReview;
    }
}
