package com.example.izohlilugat.repository;


import com.example.izohlilugat.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type,Integer> {

   
    Optional<Type> findByIdAndDeletedAtIsNull(Integer typeId);

   
    List<Type> findAll();

    
    Page<Type> findAllByDeletedAtIsNull(Pageable pageable);

}
