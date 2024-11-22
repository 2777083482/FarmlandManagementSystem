package com.fms.exceptionHandler;

import com.fms.Exception.AutoFillException;
import com.fms.Exception.UserIdCheckException;
import com.fms.constant.CommonConstant;
import com.fms.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AutoFillException.class})
    public Result<ProblemDetail> AutoFillExceptionHand(Exception e) {
        ProblemDetail problemDetail = createProblemDetail(e);
        problemDetail.setTitle(CommonConstant.AUTOFILL_ERROR);
        return Result.error(problemDetail);
    }
    @ExceptionHandler({UserIdCheckException.class})
    public Result<ProblemDetail> UserIdCheckExceptionHand(Exception e) {
        ProblemDetail problemDetail = createProblemDetail(e);
        problemDetail.setTitle(CommonConstant.USERID_ONT_MATCHED);
        return Result.error(problemDetail);
    }

    @ExceptionHandler({SQLException.class})
    public Result<ProblemDetail> SQLIntegrityConstraintViolationExceptionHand(Exception e) {
        ProblemDetail problemDetail = createProblemDetail(e);
        problemDetail.setTitle(CommonConstant.SQL_ERROR);
        return Result.error(problemDetail);
    }

    public ProblemDetail createProblemDetail(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,e.toString());
    }



}
