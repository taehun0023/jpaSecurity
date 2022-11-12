package com.shopping.www.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse respone,
			AuthenticationException exception) throws IOException, ServletException {

		String errorMessage = "비밀번호 확인 해 주세요.";

		if (exception instanceof BadCredentialsException) {
			errorMessage = "Invalid Username or Password";
		} else if (exception instanceof InsufficientAuthenticationException) {
			errorMessage = "Invalid Secret Key";
		}
		
		setDefaultFailureUrl("/member/login?error=true&exception=" + errorMessage);
		
		super.onAuthenticationFailure(request, respone, exception);
	};
}