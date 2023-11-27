package com.example.izohlilugat.swaggerConfig;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {


    @Bean
    public GroupedOpenApi publicDayWordApi(){
        return GroupedOpenApi.builder()
                .group("DayWord")
                .pathsToExclude("/dayword/**")
                .build();
    }
    @Bean
    public GroupedOpenApi publicCategoryApi(){
        return GroupedOpenApi.builder()
                .group("Category")
                .pathsToExclude("/categ/**")
                .build();
    }
    @Bean
    public GroupedOpenApi publicNoteApi(){
        return GroupedOpenApi.builder()
                .group("Note")
                .pathsToExclude("/note/**")
                .build();
    }
    @Bean
    public GroupedOpenApi publicSentenceApi(){
        return GroupedOpenApi.builder()
                .group("Sentence")
                .pathsToExclude("/senten/**")
                .build();
    }
    @Bean
    public GroupedOpenApi publicTypeApi(){
        return GroupedOpenApi.builder()
                .group("Type")
                .pathsToExclude("/type/**")
                .build();
    }
    @Bean
    public GroupedOpenApi publicWordApi(){
        return GroupedOpenApi.builder()
                .group("Word")
                .pathsToExclude("/word/**")
                .build();
    }
    @Bean
    public GroupedOpenApi publicWordInSentenceApi(){
        return GroupedOpenApi.builder()
                .group("WordInSentence")
                .pathsToExclude("/word-in-sent/**")
                .build();
    }
    @Bean
    public GroupedOpenApi publicWordTypeApi(){
        return GroupedOpenApi.builder()
                .group("WordType")
                .pathsToExclude("/wordType/**")
                .build();
    }


}
