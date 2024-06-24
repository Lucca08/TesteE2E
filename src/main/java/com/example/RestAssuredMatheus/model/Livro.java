package com.example.RestAssuredMatheus.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Livro {
    private Long id;

    private String nome;
    private String isbn;
    private LocalDate dataPublicacao;
    private boolean alugado = false;

    private List<Long> autoresId;
}
