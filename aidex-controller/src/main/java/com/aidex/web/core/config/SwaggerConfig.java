package com.aidex.web.core.config;

import java.util.ArrayList;
import java.util.List;

import com.aidex.common.config.AiDexConfig;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import static org.springframework.http.HttpMethod.*;


/**
 * Swagger2的接口配置
 *
 * @author ruoyi
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig
{
    /** 系统基础配置 */
    @Autowired(required = false)
    private AiDexConfig aidexConfig;

    /** 是否开启swagger */
    @Value("${swagger.enabled}")
    private boolean enabled;

    /** 设置请求的统一前缀 */
    @Value("${swagger.pathMapping}")
    private String pathMapping;


    /*引入Knife4j提供的扩展类*/
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }



    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.OAS_30)
                // 是否启用Swagger
                .enable(enabled)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
//                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
//                 .apis(RequestHandlerSelectors.basePackage("com.aidex.web.controller.system"))
                // 扫描所有
//                 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
//                .globalResponses(RequestMethod.POST,responseMessageList())
                .globalResponses(GET, responseMessageList())
                .globalResponses(DELETE, responseMessageList())
                .globalResponses(POST, responseMessageList())
                .globalResponses(PUT, responseMessageList())
                /* 设置安全模式，swagger可以设置访问token */
//                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping(pathMapping)
                //赋予插件体系
                .extensions(openApiExtensionResolver.buildExtensions(""))
                ;
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes()
    {
        List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts()
    {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth()
    {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo()
    {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("Claire相关接口")
                // 描述
                .description("Claire相关接口")
                // 作者信息
                .contact(new Contact("", null, null))
                // 版本
                .version("1.0")
                .build();
    }


    private List<Response> responseMessageList() {
        List<Response> responseMessageList = new ArrayList<>();

        responseMessageList.add(new ResponseBuilder().code("200").description("成功").build());
        responseMessageList.add(new ResponseBuilder().code("401").description("无权限访问，请登录").build());
        responseMessageList.add(new ResponseBuilder().code("500").description("内部错误，有错误信息返回").build());


        return responseMessageList;
    }
}
