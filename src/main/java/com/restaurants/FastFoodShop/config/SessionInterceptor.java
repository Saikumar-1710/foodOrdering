package com.restaurants.FastFoodShop.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.restaurants.FastFoodShop.Entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Component
public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		//url -> uri 
		//uri.
		
		String uri = request.getRequestURI();
		
		if(uri.equals("/login") || uri.equals("/") || uri.startsWith("/css") || uri.startsWith("/js") || uri.startsWith("/images")) {
			return true;
		}
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect("/login");
			return false;
		}
		
		User user  = (User)session.getAttribute("loggedUser");
		
		if(user == null) {
			response.sendRedirect("/login");
			return false;
		}
		
		return true;
	}
}
