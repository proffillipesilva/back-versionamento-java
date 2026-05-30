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
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;

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

    @GetMapping("/bucket")
    ResponseEntity<Void> listaBucket() {

        String bucketName = "fiec-versionamento-luana-46127";

        // 1. Initialize the S3 Client
        // It automatically picks up the environment variables we configured earlier!
        S3Client s3 = S3Client.builder()
                .region(Region.US_EAST_2) // Change to your region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        try {
            System.out.println("Listing objects in bucket: " + bucketName);

            System.out.println(s3.listBuckets());

            // 2. Create the request
            ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .build();

            // 3. Execute the request (Handles pagination if you have > 1000 files)
            s3.listObjectsV2Paginator(listObjectsRequest).stream()
                    .flatMap(response -> response.contents().stream())
                    .forEach(object -> {
                        System.out.println(" - File: " + object.key() + " (Size: " + object.size() + " bytes)");
                    });

        } catch (Exception e) {
            System.err.println("Error listing bucket files: " + e.getMessage());
        } finally {
            // 4. Close the client when done
            s3.close();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
