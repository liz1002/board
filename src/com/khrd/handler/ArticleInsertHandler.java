package com.khrd.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ArticleDAO;
import com.khrd.dao.MemberDAO;
import com.khrd.dto.Article;
import com.khrd.dto.Member;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ArticleInsertHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/articleInsertForm.jsp";
		} else if(req.getMethod().equalsIgnoreCase("post")) {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			Connection conn = null;
			
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false); //자동 변경 막음(데이터무결성)
				ArticleDAO dao = ArticleDAO.getInstance();
				
				HttpSession session = req.getSession();
				String writer_id = (String)session.getAttribute("Auth");
				
				MemberDAO mDao = MemberDAO.getInstance();
				Member m = mDao.selectId(conn, writer_id);
				Article article = new Article(0, writer_id, m.getName(), title, null, null, 0, null);
				
				dao.insertArticle(conn, article);
				dao.insertContent(conn, content);
				conn.commit(); //table 값 변경
				
				resp.sendRedirect(req.getContextPath() + "/article/list.do");
				return null;// *list로 변경
			}catch (Exception e) {
				e.printStackTrace();
				conn.rollback(); //table 값 변경한 걸 되돌림
			}finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}

}
