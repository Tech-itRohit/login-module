package com.log.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.log.jwt.JwtAuthentificationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
    private JwtAuthentificationFilter jwtAuthenticationFilter;
	@Autowired
	public AuthenticationSuccessHandler customSuccessHandler;
	@Autowired
	private UserDetailsService getUserDetailsServices()
	{
		return new UserDetailsServiceImpl();
	}
	@Bean
	BCryptPasswordEncoder getPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	DaoAuthenticationProvider getDaoAuthProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsServices());
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.authenticationProvider(getDaoAuthProvider());
		super.configure(auth);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
       
        http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/token").permitAll()
                .antMatchers("/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/index.html", "/v2/api-docs", "/v3/api-docs", "/webjars/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin(login -> login.loginPage("/signin").loginProcessingUrl("login")
                .successHandler(customSuccessHandler)).csrf(csrf -> csrf.disable());
        http .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			
	}
	 
	
}
