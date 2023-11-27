package com.example.izohlilugat.repository;

import com.example.izohlilugat.model.WordInSentence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordInSentenceRepository extends JpaRepository<WordInSentence,Integer> {


    Optional<WordInSentence> findByIdAndDeletedAtIsNull(Integer integer);


    List<WordInSentence> findAll();


    Page<WordInSentence> findAllByDeletedAtIsNull(Pageable pageable);
}
