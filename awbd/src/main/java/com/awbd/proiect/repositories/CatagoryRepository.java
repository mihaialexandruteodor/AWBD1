package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatagoryRepository extends CrudRepository<Category, Long> {
}
