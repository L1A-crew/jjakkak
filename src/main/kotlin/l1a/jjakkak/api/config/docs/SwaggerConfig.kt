package l1a.jjakkak.api.config.docs

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.parameters.RequestBody
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import l1a.jjakkak.api.ApiUrl
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun userApi(): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("User API")
            .pathsToMatch("${ApiUrl.USER}/**")
            .addOpenApiCustomizer(openApiCustomizer())
            .build()

    @Bean
    fun appointmentApi(): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("Appointment API")
            .pathsToMatch("${ApiUrl.APPOINTMENT}/**")
            .addOpenApiCustomizer(openApiCustomizer())
            .build()
   
    @Bean
    fun addressApi(): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("Address API")
            .pathsToMatch("${ApiUrl.ADDRESS}/**")
            .addOpenApiCustomizer(openApiCustomizer())
            .build()

    @Bean
    fun customOpenAPI(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title("째깍 api")
                    .version("v1")
                    .description("시간은 째깍째깍")
            )
            .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
            .addSecurityItem(SecurityRequirement().addList("basicAuth"))
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearerAuth", SecurityScheme()
                            .name("bearerAuth")
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )

    @Bean
    fun openApiCustomizer(): OpenApiCustomizer = OpenApiCustomizer { openApi ->
        openApi.paths.values.forEach { pathItem ->
            pathItem.readOperations().forEach { operation ->
                customizeOperation(operation)
            }
        }
    }

    private fun customizeOperation(operation: Operation) {
        // 기본 태그 대체
        operation.tags = operation.tags?.map { it.replace("-controller-impl", "") }
    }
}