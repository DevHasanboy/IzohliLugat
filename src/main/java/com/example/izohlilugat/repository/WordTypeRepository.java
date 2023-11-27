package com.example.izohlilugat.repository;

import com.example.izohlilugat.model.WordType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WordTypeRepository extends JpaRepository<WordType,Integer> {


    Optional<WordType> findByIdAndDeletedAtIsNull(Integer id);


    @Query(
            nativeQuery = true,
            value = "select * from  wordtype "
    )
    List<WordType> findAllBy();


    Page<WordType> findAllByDeletedAtIsNull(Pageable pageable);
}
