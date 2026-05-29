package com.fiec.versionamento.aluno.services.impl;

import com.fiec.versionamento.aluno.models.Aluno;
import com.fiec.versionamento.aluno.repositories.AlunoRepositorio;
import com.fiec.versionamento.aluno.services.AlunoService;
import com.fiec.versionamento.config.LogExecution;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private AlunoRepositorio alunoRepositorio;

    @Override
    @LogExecution
    public Aluno criaAluno(Aluno aluno) {
        /* JOINT POINT */
        Aluno alunoCriado = alunoRepositorio.save(aluno);
        return alunoCriado;
        /* **** */
    }

    @Override
    @LogExecution
    public List<Aluno> pegaAlunosDoSistema() {
        System.out.println("Teste");
        List<Aluno> listaDeAlunos = alunoRepositorio.findAll();

        return listaDeAlunos;
    }
}
