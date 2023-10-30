package com.log.config;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.log.entity.User;




public class CustomUserDetails implements UserDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	

	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() 
//	{
//		SimpleGrantedAuthority simpleGrantedAuthority =  new SimpleGrantedAuthority(user.getRole());
//		return Arrays.asList(simpleGrantedAuthority);
//	}
	  @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getRole());
	        return Collections.singletonList(authority);
	    }
	  public String getRole() {
	        return user.getRole();
	    }

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
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

