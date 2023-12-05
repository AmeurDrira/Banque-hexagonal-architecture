package com.fr.exalt.userinterface.exception;

import java.net.URI;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class ExceptionHandler implements ProblemHandling {

    @Override
    public ProblemBuilder prepare(
            @NonNull final Throwable throwable,
            @NonNull final StatusType status,
            @NonNull final URI type) {
        return ProblemHandling.super
                .prepare(throwable, status, type);

    }

    @Override
    public ResponseEntity<Problem> create(
            @NonNull final Throwable throwable,
            final Problem originalProblem,
            @NonNull final NativeWebRequest request,
            @NonNull final HttpHeaders headers) {
        ProblemBuilder problemBuilder =
                prepare(
                        throwable,
                        Objects.requireNonNull(originalProblem.getStatus()),
                        Problem.DEFAULT_TYPE);
        ThrowableProblem problem = problemBuilder.build();
        return ProblemHandling.super.create(throwable, problem, request, headers);
    }
}
