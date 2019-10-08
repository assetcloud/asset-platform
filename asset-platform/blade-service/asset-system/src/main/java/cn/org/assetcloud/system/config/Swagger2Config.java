package cn.org.assetcloud.system.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springblade.core.swagger.SwaggerAutoConfiguration;
import org.springblade.core.swagger.SwaggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger2 配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config extends SwaggerAutoConfiguration {

	private static final String DEFAULT_EXCLUDE_PATH = "/error";
	private static final String BASE_PATH = "/**";

	public Swagger2Config() {
	}

	@Override
	@ConditionalOnMissingBean
	public SwaggerProperties swaggerProperties() {
		SwaggerProperties swaggerProperties = new SwaggerProperties();
		swaggerProperties.setBasePackage("cn.org.assetcloud");
		return swaggerProperties;
	}

	@Override
	public Docket api(SwaggerProperties swaggerProperties) {
		swaggerProperties.setBasePackage("");
		if (swaggerProperties.getBasePath().size() == 0) {
			swaggerProperties.getBasePath().add("/**");
		}

		List<Predicate<String>> basePath = new ArrayList();
		swaggerProperties.getBasePath().forEach((path) -> {
			basePath.add(PathSelectors.ant(path));
		});
		if (swaggerProperties.getExcludePath().size() == 0) {
			swaggerProperties.getExcludePath().add("/error");
		}

		List<Predicate<String>> excludePath = new ArrayList();
		swaggerProperties.getExcludePath().forEach((path) -> {
			excludePath.add(PathSelectors.ant(path));
		});
		System.out.println(swaggerProperties.getBasePackage());
		return (new Docket(DocumentationType.SWAGGER_2)).host(swaggerProperties.getHost()).apiInfo(this.apiInfo(swaggerProperties)).select().apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage())).paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath))).build().securitySchemes(Collections.singletonList(this.securitySchema())).securityContexts(Collections.singletonList(this.securityContext())).pathMapping("/");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(this.defaultAuth()).forPaths(PathSelectors.regex(this.swaggerProperties().getAuthorization().getAuthRegex())).build();
	}

	private List<SecurityReference> defaultAuth() {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList();
		this.swaggerProperties().getAuthorization().getAuthorizationScopeList().forEach((authorizationScope) -> {
			authorizationScopeList.add(new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription()));
		});
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[authorizationScopeList.size()];
		return Collections.singletonList(SecurityReference.builder().reference(this.swaggerProperties().getAuthorization().getName()).scopes((AuthorizationScope[])authorizationScopeList.toArray(authorizationScopes)).build());
	}

	private OAuth securitySchema() {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList();
		this.swaggerProperties().getAuthorization().getAuthorizationScopeList().forEach((authorizationScope) -> {
			authorizationScopeList.add(new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription()));
		});
		ArrayList<GrantType> grantTypes = new ArrayList();
		this.swaggerProperties().getAuthorization().getTokenUrlList().forEach((tokenUrl) -> {
			grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(tokenUrl));
		});
		return new OAuth(this.swaggerProperties().getAuthorization().getName(), authorizationScopeList, grantTypes);
	}

	private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
		return (new ApiInfoBuilder()).title(swaggerProperties.getTitle()).description(swaggerProperties.getDescription()).license(swaggerProperties.getLicense()).licenseUrl(swaggerProperties.getLicenseUrl()).termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl()).contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail())).version(swaggerProperties.getVersion()).build();
	}
}

