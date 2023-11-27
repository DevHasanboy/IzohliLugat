package com.example.izohlilugat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        tags = {@Tag(name = "get"),
                @Tag(name = "create"),
                @Tag(name = "update"),
                @Tag(name = "delete"),
                @Tag(name = "get-all"),
                @Tag(name = "get-all-page")
        }
)

@SpringBootApplication
public class IzohliLugatApplication {

    public static void main(String[] args) {
        SpringApplication.run(IzohliLugatApplication.class, args);
    }

}
