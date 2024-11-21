package org.itiszakk.cashflow.exception;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ExceptionResolver extends DataFetcherExceptionResolverAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    protected GraphQLError resolveToSingleError(@NonNull Throwable ex, @NonNull DataFetchingEnvironment env) {

        LOGGER.error("Resolving exception", ex);

        if (ex instanceof NotFoundException) {
            return buildError(ErrorType.NOT_FOUND, ex, env);
        }

        return buildError(ErrorType.INTERNAL_ERROR, ex, env);
    }

    private GraphQLError buildError(ErrorType type, Throwable ex, DataFetchingEnvironment env) {
        return GraphQLError.newError()
                .errorType(type)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}