package com.chy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chy.model.User;
import com.chy.service.UserService;

@Component
public class LoginFilter implements Filter {

	@Autowired
	private UserService service;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (request.getRequestURI().endsWith("login.xhtml")) {
			chain.doFilter(request, response);

		} else {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (null == user) {
				if (getUserByCookie(request) == null) {
					response.sendRedirect(request.getContextPath()
							+ "/xhtml/login/login.xhtml");
					return;
				} else {
					chain.doFilter(request, response);
				}
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private User getUserByCookie(HttpServletRequest request) {
		User user = null;
		Cookie cookies[] = request.getCookies();
		String account = null;
		String password = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("com.story.user.account".equals(cookies[i].getName())) {
					System.out.println("getAccount===");
					account = cookies[i].getValue();

				} else if ("com.story.user.password".equals(cookies[i]
						.getName())) {
					System.out.println("getPassWord===");
					password = cookies[i].getValue();
				}
				if (account != null && password != null) {
					user = service.login(account, password);
					if (user != null) {
						request.getSession().setAttribute("user", user);
					}
					break;
				}
			}
		}
		return user;
	}

}
