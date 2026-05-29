package com.fiec.versionamento.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)          // Diz que a anotação só pode ser usada em métodos
@Retention(RetentionPolicy.RUNTIME)  // Garante que a anotação fique disponível em tempo de execução
public @interface LogExecution {
}