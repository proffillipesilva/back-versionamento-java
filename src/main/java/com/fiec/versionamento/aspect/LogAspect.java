package com.fiec.versionamento.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    // Captura qualquer método que esteja anotado com @LogExecution
    @Around("@annotation(com.fiec.versionamento.config.LogExecution)")
    public Object logMetodo(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1. Extrai os dados do método (Classe, Nome e Parâmetros)
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String nomeClasse = joinPoint.getTarget().getClass().getSimpleName();
        String nomeFuncao = signature.getMethod().getName();
        String parametros = Arrays.toString(joinPoint.getArgs());

        // 2. Log de Entrada
        System.out.println("[LOG ENTRADA] Classe: " + nomeClasse +
                " | Função: " + nomeFuncao + "()" +
                " | Parâmetros: " + parametros);

        // 3. Executa a função original e captura o retorno
        Object resultado = joinPoint.proceed();

        // 4. Log de Saída (Retorno)
        // Se o método for 'void', o resultado será null
        System.out.println("[LOG SAÍDA] Função: " + nomeFuncao + "()" +
                " | Retorno: " + (resultado != null ? resultado : "void"));

        // Retorna o resultado para que o fluxo do sistema continue normalmente
        return resultado;
    }
}