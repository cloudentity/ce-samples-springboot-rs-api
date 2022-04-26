package com.cloudentity.springboot.samples;

import java.util.HashMap;
import java.util.Map;
//import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class SampleController {

  @GetMapping("/jwt/info")
  public Map<String, Object> jwtInfoSample(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Jwt j = (Jwt)auth.getCredentials();
    j.getClaims();
    return j.getClaims();
  }

  @PreAuthorize("hasAuthority('SCOPE_openid')")
  @RequestMapping("/sample/protected/openidscope")
  public Map<String, String> sampleScopeProtected() {
    Map<String, String> m = new HashMap<>();
    m.put("hasScope", "true");
    return m;
  }


  @PreAuthorize("hasAuthority('SCOPE_nonexistent')")
  @RequestMapping("/sample/protected/nonexistentscope")
  public Map<String, String> sampleNonExistentScope() {
    Map<String, String> m = new HashMap<>();
    m.put("hasScope", "true");
    return m;
  }

}
