package com.example.RestAssuredMatheus.model;

import java.time.Year;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    private Long id;
    private String nome;
    private String genero;
    private Year anoNascimento;
    
    private String cpf;
}
