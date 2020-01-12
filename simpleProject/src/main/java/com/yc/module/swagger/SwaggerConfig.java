package com.yc.module.swagger;

import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Docket 对象，让我们可以灵活的配置 Swagger 的各项属性 Created by yuche on 2019/11/23.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        // return new ApiInfo(this.title, this.description, this.version, this.termsOfServiceUrl, this.contact,
        // this.license, this.licenseUrl, this.vendorExtensions);

        return new ApiInfoBuilder()
        // 页面标题
            .title("Spring Boot 项目集成 Swagger 实例文档")
            // 创建人
            // .contact(new Contact("mall-screen", "https://XXXX.com.cn", ""))
            // 版本号
            .version("API V1.0").termsOfServiceUrl("Terms of service")
            // 描述
            .description("我的博客网站：https://itweknow.cn，欢迎大家访问。").build();
    }
}
