package trabalhofinal.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}

@Bean

    public OpenAPI customOpenAPI() {

     return new OpenAPI()

          .info(new Info()

          .title("Aplicação Faculdade/Aluno")

          .version("1.0")

          .description("Uma aplicação exemplo de Faculdade/Aluno")

          .termsOfService("http://swagger.io/terms/")

          .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }