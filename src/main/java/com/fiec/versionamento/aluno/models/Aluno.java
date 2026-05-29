package com.fiec.versionamento.aluno.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id
    private Integer id;
    private String nome;
    private String curso;
    private int rm;
    private String idMatricula;
    private boolean matriculado = false;
}
