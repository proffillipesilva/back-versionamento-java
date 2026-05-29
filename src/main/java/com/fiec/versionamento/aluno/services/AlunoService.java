package com.fiec.versionamento.aluno.services;

import com.fiec.versionamento.aluno.models.Aluno;

import java.util.List;

public interface AlunoService {
    Aluno criaAluno(Aluno aluno);
    List<Aluno> pegaAlunosDoSistema();
}
