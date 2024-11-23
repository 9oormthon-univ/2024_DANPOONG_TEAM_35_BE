package app.jasople.Config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

        @Bean
        public OpenAPI api() {
            SecurityScheme apiKey = new SecurityScheme()
                    .type(SecurityScheme.Type.APIKEY)
                    .in(SecurityScheme.In.HEADER)
                    .name("Authorization");

            SecurityRequirement securityRequirement = new SecurityRequirement()
                    .addList("Bearer Token");

            return new OpenAPI()
                    .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                    .addSecurityItem(securityRequirement)
                    .addServersItem(new Server().url("/").description("https 설정"))
                    .info(new Info().title("자소플 API 명세서")
                            .description("자소플 API 명세서입니다.")
                            .version("v0.0.1"));
        }
}
