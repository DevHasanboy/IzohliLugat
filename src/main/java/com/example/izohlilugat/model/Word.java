package com.example.izohlilugat.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String label;
    private String transcription;
    private Integer categoryId;
    private Integer audioId;
    private Integer numView;
    private Integer numLike;
    private Integer numShare;

    @OneToMany(mappedBy = "wordId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DayWord > dayWords;

    @OneToMany(mappedBy = "wordId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<WordInSentence> wordInSentences;

    @OneToMany(mappedBy = "wordId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Note> notes;

    @OneToMany(mappedBy = "wordId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)

    private List<WordType> wordTypes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
