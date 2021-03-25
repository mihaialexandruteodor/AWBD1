package com.awbd.lab7.repositories;

import com.awbd.lab7.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatagoryRepository extends CrudRepository<Category, Long> {
}
