package com.khrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.khrd.dto.Article;
import com.khrd.jdbc.JDBCUtil;

public class ArticleDAO {
	private static final ArticleDAO dao = new ArticleDAO();
	
	public static ArticleDAO getInstance() {
		return dao;
	}

	public ArticleDAO() {}
	
	public int insertArticle(Connection conn, Article article) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into article value(null, ?, ?, ?, now(), now(), 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter_id());
			pstmt.setString(2, article.getWriter_name());
			pstmt.setString(3, article.getTitle());
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}//insertArticle
	
	public int insertContent(Connection conn, String content) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into article_content value(last_insert_id(), ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}//insertContent
	
	public List<Article> selectArticleList(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from article order by article_no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Article> list = new ArrayList<>();
			while (rs.next()) {
				Article article = new Article(
										rs.getInt("article_no"),
										rs.getString("writer_id"),
										rs.getString("writer_name"),
										rs.getString("title"),
										rs.getDate("regdate"),
										rs.getTimestamp("moddate"),
										rs.getInt("read_cnt"),
										null);
				list.add(article);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return null;
	}//selectArticleList
	
	public Article selectByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from article a left join article_content c on a.article_no = c.article_no where a.article_no=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Article article = new Article(
										rs.getInt("article_no"),
										rs.getString("writer_id"),
										rs.getString("writer_name"),
										rs.getString("title"),
										rs.getTimestamp("regdate"),
										rs.getTimestamp("moddate"),
										rs.getInt("read_cnt"),
										rs.getString("content"));
				return article;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return null;
	}//selectByNo
	
	public int deleteArticleByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from article where article_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}//deleteArticleByNo
	
	public int deleteContentByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from article_content where article_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}//deleteContentByNo
	
	public int updateArticle(Connection conn, int no, String title) {
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set title=? where article_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}//updateArticle
	
	public int updateContent(Connection conn, int no, String content) {
		PreparedStatement pstmt = null;
		try {
			String sql = "update article_content set content=? where article_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}//updateContent
	
	public int readCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set read_cnt=read_cnt+1 where article_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}
}
