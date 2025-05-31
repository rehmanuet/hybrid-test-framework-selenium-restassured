package api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {
    private String timestamp;
    private int status;
    private String error;
    private List<FieldError> errors;
    private String message;
    private String path;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FieldError {
        private List<String> codes;
        private List<Argument> arguments;
        private String defaultMessage;
        private String objectName;
        private String field;
        private Object rejectedValue;
        private String code;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Argument {
        private List<String> codes;
        private Object arguments;
        private String defaultMessage;
        private String code;
    }
}