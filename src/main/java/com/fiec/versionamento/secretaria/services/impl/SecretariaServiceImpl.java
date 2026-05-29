package com.fiec.versionamento.secretaria.services.impl;


import com.fiec.versionamento.aluno.models.Aluno;
import com.fiec.versionamento.aluno.repositories.AlunoRepositorio;
import com.fiec.versionamento.config.LogExecution;
import com.fiec.versionamento.kafka.KafkaProducerService;
import com.fiec.versionamento.kafka.Topico;
import com.fiec.versionamento.secretaria.services.SecretariaService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SecretariaServiceImpl implements SecretariaService {


    private KafkaProducerService kafkaProducerService;
    private AlunoRepositorio alunoRepositorio;

    @Override
    @LogExecution
    public String matricula(Aluno aluno) {
        UUID id = UUID.randomUUID();
        aluno.setIdMatricula(id.toString());
        alunoRepositorio.save(aluno);

        kafkaProducerService.sendMessage("matriculas",
                 id + "," + aluno.getRm() + "," + aluno.getCurso() );
        return id.toString();
    }

    @KafkaListener(topics = "matriculas", groupId = "my-consumer-group")
    @LogExecution
    public void consumeEvent(String message) {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String id = message.split(",")[0];
        List<Aluno> alunos = alunoRepositorio.findAll();
        Aluno aluno = alunos.stream().filter(a -> id.equals(a.getIdMatricula())).findFirst()
                .orElse(null);
        aluno.setMatriculado(true);
        alunoRepositorio.save(aluno);
    }

}
