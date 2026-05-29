package com.fiec.versionamento.secretaria.controllers;

import com.fiec.versionamento.aluno.models.Aluno;
import com.fiec.versionamento.secretaria.services.SecretariaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secretaria")
@AllArgsConstructor
public class SecretariaController {

    private SecretariaService secretariaService;

    @PostMapping
    ResponseEntity<String> matricula(@RequestBody Aluno aluno){
        String resposta = secretariaService.matricula(aluno);


        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
