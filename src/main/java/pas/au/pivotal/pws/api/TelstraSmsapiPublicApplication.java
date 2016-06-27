package pas.au.pivotal.pws.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@ComponentScan("pas.au.pivotal.pws.api")
@SpringBootApplication
public class TelstraSmsapiPublicApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelstraSmsapiPublicApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("telstra")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/telstra.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot Sample with Swagger for Telstra Public SMS API")
				.description("Spring Boot Sample with Swagger for Telstra Public SMS API")
				.version("1.0")
				.contact("Pas Apicella")
				.license("Apache License Version 2.0")
				.build();
	}
}
