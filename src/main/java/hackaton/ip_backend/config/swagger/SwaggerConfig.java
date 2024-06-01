package hackaton.ip_backend.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Value("${version}")
	private String version;

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
				.title("IP Service API Documentation")
				.version(version);

		return new OpenAPI()
				.components(new Components())
				.info(info);
	}

}
