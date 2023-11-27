package com.example.izohlilugat.repository;

import com.example.izohlilugat.model.Sentence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SentenceRepository extends JpaRepository<Sentence,Integer> {


    Optional<Sentence> findByIdAndDeletedAtIsNull(Integer id);


    List<Sentence> findAll();


    Page<Sentence> findAllByDeletedAtIsNull(Pageable pageable);
}
