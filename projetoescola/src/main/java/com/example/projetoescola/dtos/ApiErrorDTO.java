package com.example.projetoescola.dtos;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrorDTO {
    @Getter
    private List<String> errors;
    public ApiErrorDTO(String mensagem){
    this.errors = Arrays.asList(mensagem);
    }
    }
    