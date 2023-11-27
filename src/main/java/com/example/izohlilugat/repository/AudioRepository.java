package com.example.izohlilugat.repository;

import com.example.izohlilugat.model.Audio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AudioRepository extends JpaRepository<Audio,Integer> {

    Optional<Audio>findByIdAndDeletedAtIsNull(Integer id);


    List<Audio> findAll();

    Page<Audio> findAllByDeletedAtIsNull(Pageable pageable);


}
