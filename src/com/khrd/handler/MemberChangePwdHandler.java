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

public class MemberChangePwdHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/changePwdForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			HttpSession session = req.getSession();
			String id = session.getAttribute("Auth").toString();
			String pwd = req.getParameter("password");
			String newPwd = req.getParameter("newPassword");
			
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				MemberDAO dao = MemberDAO.getInstance();

				Member m = dao.selectId(conn, id);
				if(m.getPassword().equals(pwd)) {
					int result = dao.ChangePwd(conn, id, newPwd);
					req.setAttribute("result", result);
					return "/WEB-INF/view/changePwdResult.jsp";	
				}
				req.setAttribute("notMatch", true);
				return "/WEB-INF/view/changePwdForm.jsp";
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}

}
