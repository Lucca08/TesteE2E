package com.example.RestAssuredMatheus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livro {
    private Long id;

    private String nome;
    private String isbn;
    private LocalDate dataPublicacao;
    private boolean alugado = false;

    private List<Long> autoresId;
}
