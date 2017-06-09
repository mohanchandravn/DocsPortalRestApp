package com.docs.portal.cors.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.filter.OncePerRequestFilter;

public class DocsCORSFilter extends OncePerRequestFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocsCORSFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");

		// if (request.getHeader("Access-Control-Request-Method") != null &&
		// "OPTIONS".equals(request.getMethod())) {
		logger.trace("Sending Header....");
		// CORS "pre-flight" request
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		// response.addHeader("Access-Control-Allow-Headers", "Authorization");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type, Portal-Type, Authorization, Accept");
		// response.addHeader("Access-Control-Allow-Headers", "Portal-Type");
		response.addHeader("Access-Control-Max-Age", "1");
		// }
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		filterChain.doFilter(request, response);

	}

}
