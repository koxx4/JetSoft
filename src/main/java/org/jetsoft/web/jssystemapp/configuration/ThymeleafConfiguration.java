package org.jetsoft.web.jssystemapp.configuration;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ThymeleafConfiguration {

    @Bean
    LayoutDialect layoutDialect() {

        return new LayoutDialect();
    }
}
