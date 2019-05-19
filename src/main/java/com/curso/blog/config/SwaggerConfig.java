package com.curso.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@Component
@EnableSwagger2
@ConfigurationProperties(prefix = "app.swagger")
public class SwaggerConfig {

    private String name;
    private String version;
    private String description;

    private String maintainerName;
    private String maintainerUrl;
    private String maintainerEmail;

    @Bean
    public Docket blogDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Blog Project")
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.curso.blog"
                )).paths(PathSelectors.regex("/.*"))
                .build()

                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(name)
                .description(description)
                .version(version)
                .contact(new Contact(
                        maintainerName,
                        maintainerUrl,
                        maintainerEmail
                )).build();
    }

}
