package com.awbd.lab5.repositories;

import com.awbd.lab5.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatagoryRepository extends CrudRepository<Category, Long> {
}
