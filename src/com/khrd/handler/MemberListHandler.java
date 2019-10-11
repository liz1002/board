package com.khrd.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.MemberDAO;
import com.khrd.dto.Member;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class MemberListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Connection conn = ConnectionProvider.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		List<Member> list = dao.selectList(conn);
		req.setAttribute("list", list);
		JDBCUtil.close(conn);
		return "/WEB-INF/view/memberList.jsp";
	}
}
