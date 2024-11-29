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

        if (ex instanceof CashflowException cashflowException) {
            return buildCashflowError(cashflowException, env);
        }

        return buildInternalError(ex, env);
    }

    private GraphQLError buildCashflowError(CashflowException ex, DataFetchingEnvironment env) {
        return GraphQLError.newError()
                .errorType(ex.getType())
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }

    private GraphQLError buildInternalError(Throwable ex, DataFetchingEnvironment env) {
        return GraphQLError.newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}