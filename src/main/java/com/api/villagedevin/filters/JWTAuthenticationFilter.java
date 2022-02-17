package com.api.villagedevin.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.villagedevin.model.persistence.UserSpringSecurity;
import com.api.villagedevin.model.transport.CredentialsDTO;
import com.api.villagedevin.utils.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	JWTUtil jwtUtil;
	AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ServletInputStream stream = request.getInputStream();
			CredentialsDTO credentials = new ObjectMapper().readValue(stream, CredentialsDTO.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					credentials.getEmail(), credentials.getPassword(), new ArrayList<>());
			Authentication authenticate = authenticationManager.authenticate(authenticationToken);
			return authenticate;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String email = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();
		String generateToken = jwtUtil.generateToken(email);

		response.addHeader("Authorization", "Bearer " + generateToken);
	}

	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().append(json());
		}

		private String json() {
			long date = new Date().getTime();
			JsonObject j = new JsonObject();

			j.addProperty("timestamp", date);
			j.addProperty("status", "401");
			j.addProperty("error", "Not authorized");
			j.addProperty("message", "Login or password invalid");
			j.addProperty("path", "/login");
			return j.toString();
		}

	}

}
