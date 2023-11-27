package com.example.izohlilugat.repository;

import com.example.izohlilugat.model.DayWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DayWordRepository extends JpaRepository<DayWord,Integer> {


    Optional<DayWord> findByIdAndDeletedAtIsNull(Integer id);


    List<DayWord> findAll();


    Page<DayWord> findAllByDeletedAtIsNull(Pageable pageable);
}
