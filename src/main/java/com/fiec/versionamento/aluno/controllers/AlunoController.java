package com.fiec.versionamento.aluno.controllers;

import com.fiec.versionamento.aluno.models.Aluno;
import com.fiec.versionamento.aluno.repositories.AlunoRepositorio;
import com.fiec.versionamento.aluno.services.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos")
@AllArgsConstructor
public class AlunoController {


    private AlunoService alunoService;

    @PostMapping
    ResponseEntity<Aluno> criaAlunoNovo(
            @RequestBody Aluno aluno
    ) {
        Aluno alunoCriado = alunoService.criaAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
    }

    @GetMapping
    ResponseEntity<List<Aluno>> pegaListaDeAlunos(){
        List<Aluno> listaDeAlunos = alunoService.pegaAlunosDoSistema();
        return ResponseEntity.status(HttpStatus.OK).body(listaDeAlunos);
    }
}
