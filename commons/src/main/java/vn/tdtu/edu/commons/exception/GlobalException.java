package vn.tdtu.edu.commons.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.tdtu.edu.commons.response.BaseResponse;


@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse> handleBaseException(BaseException e){
        BaseResponse response = BaseResponse.builder()
                .code(Integer.valueOf(e.getCode()))
                .message(e.getLocalizedMessage())
                .build();

        return ResponseEntity.ok(response);
    }
}
