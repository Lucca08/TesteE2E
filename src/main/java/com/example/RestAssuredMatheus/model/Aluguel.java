package com.example.RestAssuredMatheus.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aluguel {

    private Long id;
    private LocalDateTime dataRetirada = LocalDateTime.now();
    private LocalDateTime dataDevolucao = LocalDateTime.now().plusDays(2);
    private List<Livro> livros;
    private Locatario locatario;
}
