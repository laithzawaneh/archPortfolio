package com.archPortfolio.archPortfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		// for display custom login page
		// return "plain-login";//htmlpage
		return "dashboard/fancy-login";// htmlpage
	}

	// add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "dashboard/access-denied";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		// You can perform additional logout-related tasks if needed
		// For example, redirecting to a login page or displaying a logout confirmation
		// message
		return "redirect:/showMyLoginPage";
	}
}
