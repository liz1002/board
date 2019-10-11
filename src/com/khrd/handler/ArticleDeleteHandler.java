package com.khrd.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ArticleDAO;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ArticleDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int no = Integer.parseInt(req.getParameter("no"));
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleDAO dao = ArticleDAO.getInstance();
			dao.deleteArticleByNo(conn, no);
			dao.deleteContentByNo(conn, no);
			conn.commit();
			
			resp.sendRedirect(req.getContextPath() + "/article/list.do");
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			JDBCUtil.close(conn);
		}		
		return null;
	}

}
