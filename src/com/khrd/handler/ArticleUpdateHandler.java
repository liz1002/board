package com.khrd.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ArticleDAO;
import com.khrd.dto.Article;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ArticleUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		if(req.getMethod().equalsIgnoreCase("get")) {
			int no = Integer.parseInt(req.getParameter("no"));
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				
				ArticleDAO dao = ArticleDAO.getInstance();
				Article article = dao.selectByNo(conn, no);
				req.setAttribute("article", article);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
			return "/WEB-INF/view/articleUpdateForm.jsp";
		} else if(req.getMethod().equalsIgnoreCase("post")) {
			int no = Integer.parseInt(req.getParameter("no"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");

			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				
				ArticleDAO dao = ArticleDAO.getInstance();
				dao.updateArticle(conn, no, title);
				dao.updateContent(conn, no, content);
				conn.commit();

				resp.sendRedirect(req.getContextPath() + "/article/list.do");
				return null;
			}catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			}finally {
				JDBCUtil.close(conn);
			}		
		}
		return null;
	}
}
