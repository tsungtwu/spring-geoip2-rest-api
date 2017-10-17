package org.iii.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {       
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.regex("/geoip.*"))    
          .build()
          .apiInfo(apiInfo());                                           
    }
    
    
    private ApiInfo apiInfo() {
        ApiInfo apiInfo =  new ApiInfoBuilder()
        		.title("GeoIP2 REST API")
        		.description("## Spring MVC REST GeoIP2 API with Swagger Documentation. \n\n  "
        				+ "### Databases \n\n IP geolocation databases from MaxMind's GeoIP2 databases \n\n - Geo2Lite2-City.mmdb "
        				+ "\n\n[MaxMind Documentation](https://dev.maxmind.com/) ")
        		.contact(new Contact("Tsung Wu", "", "jtrmn.wu@gmail.com"))
        		.version("0.1")
        		.build();
        return apiInfo;
    }
}
