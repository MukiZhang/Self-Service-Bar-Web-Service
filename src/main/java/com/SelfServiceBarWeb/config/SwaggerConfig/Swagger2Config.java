package com.SelfServiceBarWeb.config.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Muki on 2018/11/4
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.SelfServiceBarWeb.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("无人吧Web端 API文档")
                //创建人
                .contact(new Contact("MQZ", "https://github.com/MukiZhang/Self-Service-Bar-Web-Service", "821404356@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("后台接口")
                .build();
    }
}
