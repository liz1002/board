package com.khrd.handler;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.MemberDAO;
import com.khrd.dto.Member;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class MemberDuplicationHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			String id = req.getParameter("id");
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				MemberDAO dao = MemberDAO.getInstance();
				Member member = dao.selectId(conn, id);
				Map<String, Object> map = new HashMap<String, Object>();
				if(member != null) { //null이 아님(아이디 중복)
					map.put("result", "fail");
				}else {
					map.put("result", "success");
				}
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(map);
				
				resp.setContentType("application/json;charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.print(json);
				out.flush();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}

}
