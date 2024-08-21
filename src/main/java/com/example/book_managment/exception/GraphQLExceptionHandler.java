package com.example.book_managment.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof AuthenticationException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .build();
        }
        if (ex instanceof DataIntegrityViolationException) {
        // Ejemplo de manejo para violaciones de integridad de datos
        return GraphqlErrorBuilder.newError(env)
                .message("Username already exists.")
                .build();
        }
        return null; // Otros errores serán manejados por el manejador de excepciones por defecto
    }
}
