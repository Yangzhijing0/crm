package com.woniuxy.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2//启动swagger2的注解
public class Swagger2Config {
    /**
     * @return
     */
    @Bean
    public Docket createRequestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.basePackage("com.woniuxy.controller"))
                .build();
    }
    /**
     * 编写ApiInfo的方法
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户中心API")//设置标题
                .description("用户中心相关API接口")//设置描述信息
                .termsOfServiceUrl("http://localhost:9002")//设置服务的端口
                .contact(new Contact("Giles","http://giles.org","373205347@qq.com"))//设置作者信息
                .version("1.0")//版本
                .license("蜗牛学院")//版权
                .build();
    }
}
