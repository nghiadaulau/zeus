package vn.tdtu.edu.commons.utils;

import lombok.Builder;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
public class ValidateUtils {

    private Object value;
    private boolean required;
    private Integer maxLength;
    private String fieldName;
    private String regex;
    private boolean onlyNumber;
    private boolean isInteger;
    private Long max;
    private Long min;
    private final String ONLY_NUMBER = "[0-9]+";

    public Map validate(){
        Map<String, String> errors = new HashMap<>();
        //Check field is require
        if (required && ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)){
            errors.put(fieldName, fieldName + " is required");
        }

        //Check max length of field
        if (!ObjectUtils.isEmpty(maxLength)
                && !ObjectUtils.isEmpty(value)
                && value.toString().length() > maxLength.intValue()
                && ObjectUtils.isEmpty(fieldName)){
            errors.put(fieldName, fieldName + " must has 0 " + maxLength + "characters");
        }

        if (onlyNumber && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)){
            Pattern patternOnlyNumber = Pattern.compile(ONLY_NUMBER);
            Matcher matcher = patternOnlyNumber.matcher(value.toString());

            if(!matcher.matches()){
                errors.put(fieldName, fieldName + "Must contain only number");
            }
        }

        //Check integer require
        if(isInteger && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)){
            try {
                Integer.parseInt(value.toString());
            }catch (Exception e){
                errors.put(fieldName, fieldName + " must is integer number");
            }
        }

        //Check max and min of field value
        if (!ObjectUtils.isEmpty(max)
                && !ObjectUtils.isEmpty(value)
                && !ObjectUtils.isEmpty(min)
                && !ObjectUtils.isEmpty(fieldName)){
            try {
                String valueDouble = value.toString();
                if (valueDouble.length()< min || valueDouble.length() > max){
                    errors.put(fieldName, fieldName + " must range from " + min + " to " + max);
                }
            }catch (Exception e){
                errors.put(fieldName, fieldName + "is invalid data type");
            }
        }

        return errors;
    }
}
