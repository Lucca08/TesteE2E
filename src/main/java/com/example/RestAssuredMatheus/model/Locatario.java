package com.example.RestAssuredMatheus.model;

import java.time.LocalDate;

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
public class Locatario {

    private Long id;
    private String nome;
    private String genero; 
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
}
