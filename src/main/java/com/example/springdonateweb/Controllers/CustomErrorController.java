package com.example.springdonateweb.Controllers;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    @ResponseBody
    public Map<String, Object> handleError(HttpServletRequest request) {
        // Retrieve the status code of the error
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");

        // Build a custom response body
        Map<String, Object> response = new HashMap<>();
        response.put("status", statusCode);
        response.put("message", errorMessage != null ? errorMessage : "Something went wrong!");

        // Customize the message for specific status codes
        if (statusCode != null) {
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                response.put("message", "Page not found!");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                response.put("message", "Internal server error. Please try again later.");
            }
        }

        return response;
    }





}
