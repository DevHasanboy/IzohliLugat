package com.example.izohlilugat.repository;

import com.example.izohlilugat.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {


    Optional<Note> findByIdAndDeletedAtIsNull(Integer id);


    List<Note> findAll();


    Page<Note> findAllByDeletedAtIsNull(Pageable pageable);
}
