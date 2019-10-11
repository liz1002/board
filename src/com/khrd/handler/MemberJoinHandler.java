package com.khrd.handler;

import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.MemberDAO;
import com.khrd.dto.Member;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class MemberJoinHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/joinForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			req.setCharacterEncoding("utf-8");
			String memberId = req.getParameter("memberId");
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				MemberDAO dao = MemberDAO.getInstance();
				Member member = new Member(memberId, name, password, new Date());
				int result = dao.insert(conn, member);
				req.setAttribute("result", result);
				return "/WEB-INF/view/joinResult.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn);
			}			
		}
		return null;
	}

}
