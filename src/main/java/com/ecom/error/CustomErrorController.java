package com.ecom.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Aquí puedes redirigir a una página de error personalizada
        return "error"; // Redirige a error.html en la carpeta templates
    }
}
