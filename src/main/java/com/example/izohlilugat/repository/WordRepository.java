package com.example.izohlilugat.repository;

import com.example.izohlilugat.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word,Integer> {

    Optional<Word> findByIdAndDeletedAtIsNull(Integer id);


    List<Word> findAll();


    Page<Word> findAllByDeletedAtIsNull(Pageable pageable);
}
