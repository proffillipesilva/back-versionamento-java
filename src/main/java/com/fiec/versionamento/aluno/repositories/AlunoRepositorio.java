package com.fiec.versionamento.aluno.repositories;

import com.fiec.versionamento.aluno.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepositorio extends JpaRepository<Aluno, Integer> {
}
