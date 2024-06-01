package hackaton.ip_backend.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Value("${version}")
	private String version;

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
				.title("IP Service API Documentation")
				.version(version);

		SecurityScheme securityScheme = new SecurityScheme()
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.bearerFormat(HttpHeaders.AUTHORIZATION)
				.in(SecurityScheme.In.HEADER)
				.name(HttpHeaders.AUTHORIZATION);

		SecurityRequirement requirement = new SecurityRequirement();
		requirement.addList(HttpHeaders.AUTHORIZATION);

		return new OpenAPI()
				.components(new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION, securityScheme))
				.addSecurityItem(requirement)
				.info(info);
	}

}
