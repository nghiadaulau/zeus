package vn.tdtu.edu.commons.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {
    private String code;
    private String message;
}
