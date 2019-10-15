package com.khrd.handler;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ArticleDAO;
import com.khrd.dto.Article;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;
//json 데이터를 만들어 돌려줌
public class ArticleReadJsonHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 게시물 번호
		int no = Integer.parseInt(req.getParameter("no"));
		
		//db로부터 no 데이터 가져오기
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			ArticleDAO dao = ArticleDAO.getInstance();
			Article article = dao.selectByNo(conn, no);
			
			//article -> json String
			ObjectMapper om = new ObjectMapper();
			String jsonData = om.writeValueAsString(article);
			System.out.println(jsonData);
			
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(jsonData);
			out.flush(); //브라우저에 내보내기(json 객체)
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
