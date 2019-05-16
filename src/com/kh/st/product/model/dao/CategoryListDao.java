package com.kh.st.product.model.dao;

import static com.kh.st.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.st.member.model.dao.MemberDao;
import com.kh.st.product.model.vo.Category;

public class CategoryListDao {
private Properties prop = new Properties();
	
	//properties 연결
	public CategoryListDao() {
		String fileName = MemberDao.class
								   .getResource("/sql/product/product-query.properties")
								   .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Object> loadList(Connection con, String topcategory) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Object> clist = null;
		
		String query = prop.getProperty("loadList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, topcategory);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				
				clist.add(rset.getString("CTG_NAME"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		
		
		
		return clist;
	}

}