package com.khrd.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ArticleDAO;
import com.khrd.dto.Article;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ArticleListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			ArticleDAO dao = ArticleDAO.getInstance();
			List<Article> list = dao.selectArticleList(conn); 
			req.setAttribute("list", list);

			return "/WEB-INF/view/articleList.jsp";
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}
}
