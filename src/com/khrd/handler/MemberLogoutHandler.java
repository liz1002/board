package com.khrd.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khrd.controller.CommandHandler;

public class MemberLogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		session.invalidate();
		//session.removeAttribute("key"); 여러 개일 때
		return "index.jsp"; //홈 이동
	}

}
