package org.jetsoft.web.jssystemapp.core;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
class MainErrorController implements ErrorController {

    @RequestMapping("/error")
    String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value() || statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "error-403";
            }
        }

        return "error";
    }

    @ModelAttribute("exceptions")
    @Profile("!dev")
    private Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {

        ErrorAttributes errorAttributes = new DefaultErrorAttributes();

        return errorAttributes.getErrorAttributes(
                request,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE)
        );
    }

    @ModelAttribute("exceptions")
    @Profile("!prod")
    private Map<String, Object> getErrorAttributesWithStackTrace(WebRequest request, boolean includeStackTrace) {

        ErrorAttributes errorAttributes = new DefaultErrorAttributes();

        return errorAttributes.getErrorAttributes(
                request,
                ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.EXCEPTION,
                        ErrorAttributeOptions.Include.STACK_TRACE,
                        ErrorAttributeOptions.Include.BINDING_ERRORS
                )
        );
    }
}
