package com.awbd.lab5.repositories;

import com.awbd.lab5.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query("select p from Product p where p.seller.id = ?1")
    List<Product> findBySeller(Long sellerId);

    @Query("select p from Product p where p.seller.firstName = :firstName and p.seller.lastName = :lastName")
    List<Product> findBySellerName(@Param("firstName") String sellerFirstName, @Param("lastName") String sellerLastName);

}