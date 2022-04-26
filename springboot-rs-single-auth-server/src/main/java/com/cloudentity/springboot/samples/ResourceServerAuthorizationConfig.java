package com.cloudentity.springboot.samples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

//https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide
// use spring security instead of spring oauth 2.0

@Component
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ResourceServerAuthorizationConfig extends WebSecurityConfigurerAdapter {
/*
  @Value("${resourceServerId}")
  public String resourceServerId;*/

  /**
   * Spring OAuth expects "aud" claim in JWT token. That claim's value should match to the resourceId value you specify your
   * Spring app (if not specified it defaults to "oauth2-resource").
   *
   * @param resources
   * @throws Exception
   *//*
  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    super.configure(resources);
    resources.resourceId(resourceServerId);
  }*/

  /**
   * This will enable incoming accessToken to be validated by a key provided by remote authorization server
   * and inject JWT into the authentication context of methods. With this scope can be enforced at method
   * level using constructs like `@PreAuthorize("hasAuthority('SCOPE_jda-frontend')")`
   *
   * @param http
   * @throws Exception
   */
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers("/actuator/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .oauth2ResourceServer()
        .jwt();
  }
}
