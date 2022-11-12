package com.shopping.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	private final UserDetailsService userDetailsService;
	private final AuthSuccessHandler authSuccessHandler;
	private final AuthFailureHandler authFailurHandler;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/" , "/member/**").permitAll() // 해당 경로 접근 허용
				.antMatchers("/admin/**").hasRole("admin") // // 인증 요구
				.anyRequest().authenticated();

		http.formLogin()
				.loginPage("/member/signUp") // 인증 처리 메서드
				// .defaultSuccessUrl("/")
				.usernameParameter("memberId")
				.passwordParameter("memberPw")
				.loginProcessingUrl("/login/action")
				.successHandler(authSuccessHandler)
				.failureHandler(authFailurHandler); // 인증 처리 페이지
		
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "remeber-me");
		
		http.sessionManagement()
				.maximumSessions(1)
				.maxSessionsPreventsLogin(false)
				.expiredUrl("/");
			
		http.rememberMe()
				.key("testToken")
				.alwaysRemember(false)
				.tokenValiditySeconds(2000)
				.rememberMeParameter("remember-me")
				.userDetailsService(userDetailsService);
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// permitAll()은 SecurityFilter 안에 들어와 심사를 받지만 ignoring은 아예 거치지 않고 허가해준다.
	@Bean
	public WebSecurityCustomizer websecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**" , "/js/**", "/font/**", "/img/**");
	}
	
}
