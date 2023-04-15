package vn.tdtu.edu.commons.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data @Getter @Setter
@Builder
public class BaseResponse {
    private Integer code;
    private Object data;
    private String message;
}

