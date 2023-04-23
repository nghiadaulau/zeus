package vn.tdtu.edu.admin.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AdminErrorController implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.BAD_REQUEST.value()){
                // handle HTTP 400 Bad Request error
                return "404";
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                // handle HTTP 401 Unauthorized error
                return "404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                // handle HTTP 403 Forbidden error
                return "404";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // handle HTTP 404 Not Found error
                return "404";
            } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                // handle HTTP 405 Not Allowed error
                return "404";
            } else if (statusCode == HttpStatus.NOT_ACCEPTABLE.value()) {
                // handle HTTP 406 Not Acceptable error
                return "404";
            } else if (statusCode == HttpStatus.CONFLICT.value()) {
                // handle HTTP 409 Conflict error
                return "404";
            } else if (statusCode == HttpStatus.GONE.value()) {
                // handle HTTP 410 Gone error
                return "404";
            } else if (statusCode == HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()) {
                // handle HTTP 415  Unsupported Media Type error
                return "404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // handle HTTP 500  Internal Server error
                return "404";
            } else if (statusCode == HttpStatus.NOT_IMPLEMENTED.value()) {
                // handle HTTP 501 Not Implemented error
                return "404";
            } else if (statusCode == HttpStatus.BAD_GATEWAY.value()) {
                // handle HTTP 502 Bad Gateway error
                return "404";
            } else if (statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                // handle HTTP 503 Service Unavailable error
                return "404";
            } else if (statusCode == HttpStatus.GATEWAY_TIMEOUT.value()) {
                // handle HTTP 504 Gateway Timeout error
                return "404";
            }
        }
        return "404";
    }
}