package com.khrd.handler;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ArticleDAO;
import com.khrd.dto.Article;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ArticleListJsonHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			ArticleDAO dao = ArticleDAO.getInstance();
			List<Article> list = dao.selectArticleList(conn);
			//object => json
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(list);
			//브라우저에 값 전달
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.flush(); //브라우저에 내보냄
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
