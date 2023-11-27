package com.example.izohlilugat.repository;

import com.example.izohlilugat.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


    Optional<Category> findByIdAndDeletedAtIsNull(Integer id);


    List<Category> findAll();

    Page<Category> findAllByDeletedAtIsNull(Pageable pageable);
}
