package com.khrd.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.MemberDAO;
import com.khrd.dto.Member;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class MemberLoginHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/loginForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			String id = req.getParameter("memberId");
			String pwd = req.getParameter("password");
			
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				MemberDAO dao = MemberDAO.getInstance();
				Member m = dao.selectIdAndPw(conn, id, pwd);
				if(m == null) {
					req.setAttribute("notMatch", true);
					return "/WEB-INF/view/loginForm.jsp";
				}
				//로그인 처리
				HttpSession session = req.getSession();
				session.setAttribute("Auth", m.getMemberId());
				return "index.jsp"; //홈 이동
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}

}
