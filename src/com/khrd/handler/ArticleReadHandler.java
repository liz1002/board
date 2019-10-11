package com.khrd.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ArticleDAO;
import com.khrd.dto.Article;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ArticleReadHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String sNo = req.getParameter("no");
		int no = Integer.parseInt(sNo);
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			ArticleDAO dao = ArticleDAO.getInstance();
			Article article = dao.selectByNo(conn, no);
			dao.readCount(conn, no);
			req.setAttribute("article", article);
			return "/WEB-INF/view/articleRead.jsp";
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
