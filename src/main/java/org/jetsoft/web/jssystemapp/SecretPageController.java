package org.jetsoft.web.jssystemapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secured-example-endpoint")
class SecretPageController {

    @GetMapping
    String showSecretPage() {

        return "secret-page";
    }

}
