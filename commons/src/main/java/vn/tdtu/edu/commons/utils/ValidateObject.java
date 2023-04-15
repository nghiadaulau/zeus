package vn.tdtu.edu.commons.utils;

import jakarta.persistence.Cache;
import java.util.HashMap;
import java.util.Map;

public class ValidateObject {

    /* Example code to using
    public  static Map<String, String> validateUserDTO(UserDTO userDTO){
        Map<String, String > errors = new HashMap<>();

        errors.putAll(
                ValidateUtils.builder()
                        .fieldName("username")
                        .value(userDTO.getUsername())
                        .required(true)
                        .build().validate()
        );

        errors.putAll(
                ValidateUtils.builder()
                        .fieldName("password")
                        .value(userDTO.getPassword())
                        .required(true)
                        .min(6L)
                        .max(6L)
                        .build().validate()
        );

        errors.putAll(
                ValidateUtils.builder()
                        .fieldName("role")
                        .value(userDTO.getRole())
                        .required(true)
                        .build().validate()
        );
        return errors;
    }
    */
}
