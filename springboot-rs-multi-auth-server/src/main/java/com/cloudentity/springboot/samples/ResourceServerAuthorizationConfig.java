package com.cloudentity.springboot.samples;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.stereotype.Component;

//https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide
// use spring security instead of spring oauth 2.0

@Component
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ResourceServerAuthorizationConfig extends WebSecurityConfigurerAdapter {

  /**
   * This will enable incoming accessToken to be validated by a key provided by remote authorization server
   * and inject JWT into the authentication context of methods. With this scope can be enforced at method
   * level using constructs like `@PreAuthorize("hasAuthority('SCOPE-frontend')")`
   *
   * @param http
   * @throws Exception
   */
  public void configure(HttpSecurity http) throws Exception {

    JwtIssuerAuthenticationManagerResolver authenticationManagerResolver = new JwtIssuerAuthenticationManagerResolver
        ("https://rtest.authz.cloudentity.io/rtest/ce-dev-playground-integrations",
            "https://rtest.authz.cloudentity.io/rtest/ce-samples-oidc-client-apps");

    http.csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers("/actuator/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .oauth2ResourceServer(oauth2 -> oauth2.authenticationManagerResolver(authenticationManagerResolver));
  }
}
