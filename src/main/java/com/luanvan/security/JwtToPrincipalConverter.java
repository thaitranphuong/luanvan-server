package com.luanvan.security;


import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtToPrincipalConverter {
	
	public UserPrincipal convert(DecodedJWT jwt) {
		UserPrincipal userPrincipal = new UserPrincipal(Long.valueOf(jwt.getSubject()), jwt.getClaim("e").asString(), 
																						extractAuthoritiesFromClaim(jwt),
																						null);
		return userPrincipal;
	}
	
	private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
		var claim = jwt.getClaim("a");
		if (claim.isNull() || claim.isMissing()) return List.of();
		return claim.asList(SimpleGrantedAuthority.class);
	}
}
