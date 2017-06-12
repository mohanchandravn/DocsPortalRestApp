package com.docs.portal.cors.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.core.Ordered;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DocsCORSFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocsCORSFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Portal-Type, Authorization, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if(!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(req, res);
        }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		response.addHeader("Access-Control-Allow-Origin", "*");
//
//		// if (request.getHeader("Access-Control-Request-Method") != null &&
//		// "OPTIONS".equals(request.getMethod())) {
//		logger.trace("Sending Header....");
//		// CORS "pre-flight" request
//		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//		// response.addHeader("Access-Control-Allow-Headers", "Authorization");
//		response.addHeader("Access-Control-Allow-Headers", "Content-Type, Portal-Type, Authorization, Accept");
//		// response.addHeader("Access-Control-Allow-Headers", "Portal-Type");
//		response.addHeader("Access-Control-Max-Age", "1");
//		// }
//		if (request.getMethod().equals("OPTIONS")) {
//			response.setStatus(HttpServletResponse.SC_OK);
//			return;
//		}
//
//		filterChain.doFilter(request, response);
//
//	}

}
