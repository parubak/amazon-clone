package com.example.amazonclone.repos;

import com.example.amazonclone.models.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {


    @Query("select pi from ProductItem as pi where  pi.product.subgroup.id IN (:category) order by pi.product.rating desc")
    Page<ProductItem> findAllBySortProductRating(@Param("category")Long[] category, Pageable pageable);
    @Query("select pi from ProductItem as pi order by pi.product.rating desc")
    Page<ProductItem> findAllBySortProductRating(PageRequest pageRequest);
    @Query("select pi from ProductItem as pi order by pi.id desc")
    Page<ProductItem> findAllBySortId(PageRequest pageRequest);

    @Query("select pi from ProductItem as pi where pi.price between :pFrom and :pTo")
    Page<ProductItem> findAllProductItemPagFiltrs(@Param("pFrom") int pFrom, @Param("pTo") int pTo, PageRequest pageRequest);
    @Query("select pi from ProductItem as pi where pi.price < :pFrom")
    Page<ProductItem> findAllProductItemPagFiltrsPFrom(@Param("pFrom") int pFrom, PageRequest pageRequest);
    @Query("select pi from ProductItem as pi where pi.price > :pTo")
    Page<ProductItem> findAllProductItemPagFiltrsPTo(@Param("pTo") int pTo, PageRequest pageRequest);

    @Query("select pi from ProductItem as pi where pi.product.subgroup.id IN (:category)")
    Page<ProductItem> findAllProductItemGetCategoris(@Param("category") Long[] category, PageRequest pageRequest);


}
