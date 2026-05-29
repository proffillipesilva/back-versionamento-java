package com.fiec.versionamento.secretaria.services;

import com.fiec.versionamento.aluno.models.Aluno;

public interface SecretariaService {
    String matricula(Aluno aluno);
    void consumeEvent(String message);
}
