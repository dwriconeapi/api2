package org.ricone.api.oneroster.component.springfox;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1001)
public class LimitParamReader implements ParameterBuilderPlugin {
	private TypeResolver resolver;

	public LimitParamReader(TypeResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public void apply(ParameterContext parameterContext) {
		ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
		Optional<SwaggerParam.Limit> requestParam = methodParameter.findAnnotation(SwaggerParam.Limit.class);
		if(requestParam.isPresent()) {
			parameterContext.parameterBuilder()
					.parameterType("query")
					.name("limit")
					.description("The number of results to return")
					.required(false)
					.order(1001)
					.type(resolver.resolve(String.class));
		}
	}

	@Override
	public boolean supports(DocumentationType documentationType) {
		return true;
	}
}