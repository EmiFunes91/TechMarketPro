package com.ecom.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Obtener el código de error
        Object status = request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("errorCode", statusCode);
        }
        return "error"; // Redirige a error.html en la carpeta templates
    }

    // Método opcional para obtener la ruta de error, si es necesario en la clase
    public String getErrorPath() {
        return "/error";
    }
}

