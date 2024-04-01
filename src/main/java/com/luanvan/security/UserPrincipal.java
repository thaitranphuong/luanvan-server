package com.luanvan.security;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails{
	
	private final Long userId;
	
	private final String email;
	
	@JsonIgnore
	private final String password;
	
	private final Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long userId, String email,
			Collection<? extends GrantedAuthority> authorities, String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.authorities = authorities;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
