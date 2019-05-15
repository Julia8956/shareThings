package com.kh.st.member.model.dao;

import static com.kh.st.common.JDBCTemplate.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.kh.st.member.model.vo.Member;
import com.kh.st.member.model.vo.Mlevel;
import com.kh.st.member.model.vo.Payback;
import com.kh.st.member.model.vo.Refund;
import com.kh.st.member.model.vo.Report;
import com.kh.st.common.PageInfo;

public class MemberDao {
	private Properties prop = new Properties();
	
	//properties 연결
	public MemberDao() {
		String fileName = MemberDao.class
								   .getResource("/sql/member/member-query.properties")
								   .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//도연이꺼
	//전체 회원 수 리턴
	public int getListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("listCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}

	public ArrayList<Member> selectList(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		
		String query = prop.getProperty("selectList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member m = new Member();
				
				m.setUno(rset.getInt("UNO"));
				m.setUserId(rset.getString("USER_ID"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setProfits(rset.getInt("PROFITS"));
				m.setTotalPoint(rset.getInt("TOTAL_POINT"));
				m.setPenaltyPoint(rset.getInt("PENALTY_POINT"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				System.out.println(rset.getString("STATUS"));
				if(rset.getString("STATUS").equals("Y")) {
					m.setStatus("가입");
				}else {
					m.setStatus("탈퇴");
				}
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

	//전체 회원등급 리턴용
	public ArrayList<Mlevel> selectMlevelList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Mlevel> list = null;
		
		String query = prop.getProperty("selectMlevelList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Mlevel>();
			
			while(rset.next()) {
				Mlevel ml = new Mlevel();
				
				ml.setLevelCode(rset.getString("LEVEL_CODE"));
				ml.setLevelName(rset.getString("LEVEL_NAME"));
				ml.setPerPoint(rset.getInt("PER_POINT"));
				ml.setLevelStd(rset.getInt("LEVEL_STD"));
				
				list.add(ml);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}
	
	//회원등급 수정용
	public int updateMlevel(Connection con, ArrayList<Mlevel> list) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMlevel");
		
		try {
			for(int i = 0; i < list.size(); i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, list.get(i).getLevelName());
				pstmt.setInt(2, list.get(i).getLevelStd());
				pstmt.setInt(3, list.get(i).getPerPoint());
				pstmt.setString(4, list.get(i).getLevelCode());
				
				result += pstmt.executeUpdate();
			}
			
			if(result == list.size()) {
				result = 1;
			}else{
				result = 0;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	//신고이력 전체 카운트용
	public int getReportListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("reportListCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}
	
	//신고이력 전체 조회용
	public ArrayList<Report> selectReportList(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Report> list = null;
		
		String query = prop.getProperty("selectReportList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Report>();
			
			while(rset.next()) {
				Report r = new Report();
				
				r.setReportNo(rset.getInt("REPORT_NO"));
				r.setTargetUser(rset.getString("USER1"));
				r.setReportName(rset.getString("REPORT_NAME"));
				r.setReportContent(rset.getString("REPORT_CONTENT"));
				r.setReportUser(rset.getString("USER2"));
				r.setReportDate(rset.getDate("REPORT_DATE"));
				r.setComplateDate(rset.getDate("COMPLETE_DATE"));
				r.setSumPenalty(rset.getInt("PENALTY_POINT"));
				r.setReject(rset.getString("REJECT"));
				r.setPenalty(rset.getInt("PENALTY"));
				System.out.println(rset.getString("REPORT_RESULT"));
				
				if(rset.getString("REPORT_RESULT").equals("Y")) {
					r.setReportResult("적합");
				}else {
					r.setReportResult("부적합");
				}
				
				if(rset.getString("REPORT_RESULT") != null) {
					r.setStatus("처리완료");
				}else{
					r.setStatus("처리대기");
				}
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	
	//수익금 환급이력 전체 카운트용
	public int getPaybackListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("paybackListCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}
	
	//수익금 환급 전체 조회용
	public ArrayList<Payback> selectPaybackList(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Payback> list = null;
		
		String query = prop.getProperty("selectPaybackList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Payback>();
			
			while(rset.next()) {
				Payback p = new Payback();
				
				p.setPbNo(rset.getInt("PB_NO"));
				p.setUserId(rset.getString("USER_ID"));
				p.setUserName(rset.getString("USER_NAME"));
				p.setBank(rset.getString("BANK"));
				p.setAccount(rset.getString("ACCOUNT"));
				p.setAccName(rset.getString("ACCNAME"));
				p.setReqDate(rset.getDate("REQ_DATE"));
				p.setPbAmount(rset.getInt("PB_AMOUNT"));
				p.setPbDate(rset.getDate("PB_DATE"));
				p.setPbStatus(rset.getString("PB_STATUS"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	
	//회원환불 전체 카운트용
	public int getRefundListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("refundListCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}
	
	//회원환불 전체 리스트 조회용
	public ArrayList<Refund> selectRefundList(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Refund> list = null;
		
		String query = prop.getProperty("selectRefundList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Refund>();
			
			while(rset.next()) {
				Refund r = new Refund();
				
				r.setRfNo(rset.getInt("RF_NO"));
				r.setUserId(rset.getString("USER_ID"));
				r.setUserName(rset.getString("USER_NAME"));
				r.setPayNo(rset.getInt("PAY_NO"));
				r.setRfType(rset.getString("RF_TYPE"));
				r.setReqDate(rset.getDate("REQ_DATE"));
				r.setRfStatus(rset.getString("RF_STATUS"));
				r.setRfDate(rset.getDate("RF_DATE"));
				r.setRfReason(rset.getString("RF_REASON"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	
	
	public int reportOk(Connection con, String[] reportsNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("reportOk");
		
		try {
			for(int i = 0; i < reportsNo.length; i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(reportsNo[i]));
				
				result += pstmt.executeUpdate();
			}
			
			if(result == reportsNo.length) {
				result = 1;
			}else {
				result = 0;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
//민지
	public Member login(Connection con, String userId, String userPwd) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginUser = null;
		String query = prop.getProperty("loginSelect");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				loginUser = new Member();
				loginUser.setUno(rset.getInt("UNO"));
				loginUser.setUserId(rset.getString("USER_ID"));
				loginUser.setUserPwd(rset.getString("USER_PWD"));
				loginUser.setUserName(rset.getString("USER_NAME"));
				loginUser.setGender(rset.getString("GENDER"));
				loginUser.setBirthDate(rset.getDate("BIRTH_DATE"));
				loginUser.setPhone(rset.getString("PHONE"));
				loginUser.setEmail(rset.getString("EMAIL"));
				loginUser.setAddress(rset.getString("ADDRESS"));
				loginUser.setSubPhone(rset.getString("SUB_PHONE"));
				loginUser.setEnrollDate(rset.getDate("ENROLL_DATE"));
				loginUser.setModifyDate(rset.getDate("MODIFY_DATE"));
				loginUser.setmLevel(rset.getString("MEMBER_LEVEL"));
				loginUser.setTotalPoint(rset.getInt("TOTAL_POINT"));
				loginUser.setProfits(rset.getInt("PROFITS"));
				loginUser.setPenaltyPoint(rset.getInt("PENALTY_POINT"));
				loginUser.setOptionCheck(rset.getString("OPTION_CHECK"));
				loginUser.setSocialLink(rset.getString("SOCIAL_LINK"));
				loginUser.setEmailVerif(rset.getString("EMAIL_VERIF"));
				loginUser.setStatus(rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return loginUser;
	}

	public int idCheck(Connection con, String userId) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("idCheck");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public int emailCheck(Connection con, String email) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("emailCheck");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public int insertMember(Connection con, Member newMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertMember");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newMember.getUserId());
			pstmt.setString(2, newMember.getUserPwd());
			pstmt.setString(3, newMember.getUserName());
			pstmt.setString(4, newMember.getGender());
			pstmt.setDate(5, newMember.getBirthDate());
			pstmt.setString(6, newMember.getPhone());
			pstmt.setString(7, newMember.getEmail());
			pstmt.setString(8, newMember.getAddress());
			pstmt.setString(9, newMember.getSubPhone());
			pstmt.setString(10, newMember.getOptionCheck());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int setEmailChecked(Connection con, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("emailCheck");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertPlusPoint(Connection con, Member newMember, int pointPrice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertPoint");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newMember.getUserId());
			pstmt.setString(2, "적립");
			pstmt.setInt(3, pointPrice);
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	

	

	

	

	

	

	

}






























