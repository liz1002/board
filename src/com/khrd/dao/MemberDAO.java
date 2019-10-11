package com.khrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.khrd.dto.Member;
import com.khrd.jdbc.JDBCUtil;


public class MemberDAO {
	
	private static final MemberDAO dao = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return dao;
	}

	private MemberDAO() {}
	
	public int insert(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into member values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setTimestamp(4, new Timestamp(member.getRegdate().getTime()));
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}		
		return -1;
	}//insert
	
	public Member selectIdAndPw(Connection conn, String id, String pwd) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from member where member_id=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Member m = new Member(
								rs.getString("member_id"),
								rs.getString("name"),
								rs.getString("password"),
								rs.getTimestamp("regdate"));				
				return m;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return null;
	}//selectIdAndPw
	
	public Member selectId(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from member where member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Member m = new Member(
								rs.getString("member_id"),
								rs.getString("name"),
								rs.getString("password"),
								rs.getTimestamp("regdate"));				
				return m;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return null;
	}//selectId
	
	public List<Member> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Member> list = new ArrayList<>();
			while(rs.next()) {
				Member m = new Member(
								rs.getString("member_id"),
								rs.getString("name"),
								rs.getString("password"),
								rs.getTimestamp("regdate"));				
				list.add(m);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return null;
	}//selectList
	
	public int ChangePwd(Connection conn, String id, String newPwd) {
		PreparedStatement pstmt = null;
		try {
			String sql = "update member set password=? where member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}//ChangePwd
}
