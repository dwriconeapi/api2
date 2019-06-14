package org.ricone.security;

import org.ricone.config.cache.CacheService;
import org.ricone.security.jwt.XPressAuthenticationEntryPoint;
import org.ricone.security.jwt.XPressAuthorizationFilter;
import org.ricone.security.oneroster.OneRosterAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/*
	https://www.baeldung.com/spring-security-granted-authority-vs-role
	https://www.baeldung.com/spring-security-method-security
	https://www.baeldung.com/spring-security-expressions
	https://docs.spring.io/spring-security/site/docs/current/reference/html/authorization.html#el-access
	https://www.baeldung.com/spring-security-create-new-custom-security-expression
*/


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	//https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html#multiple-httpsecurity

	@Configuration @Order(1)
	@PropertySource("classpath:security.properties")
	public static class XPressWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		private final CacheService cacheService;
		private final Environment environment;

		public XPressWebSecurityConfigurationAdapter(CacheService cacheService, Environment environment) {
			this.cacheService = cacheService;
			this.environment = environment;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
			http.antMatcher("/requests/**")
				.authorizeRequests().anyRequest().authenticated()
				.and().addFilter(new XPressAuthorizationFilter(authenticationManagerBean(), cacheService, environment))
					.exceptionHandling().authenticationEntryPoint(new XPressAuthenticationEntryPoint())
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
	}

	@Configuration @Order(2)
	@PropertySource("classpath:security.properties")
	public static class OneRosterWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		private final CacheService cacheService;
		private final Environment environment;

		public OneRosterWebSecurityConfigurationAdapter(CacheService cacheService, Environment environment) {
			this.cacheService = cacheService;
			this.environment = environment;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/ims/oneroster/v1p1/**")
				.authorizeRequests().anyRequest().authenticated()
				.and().addFilter(new OneRosterAuthorizationFilter(authenticationManagerBean(), cacheService, environment))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
	}
}