package db.oracle;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection; // 접속 성공 후, 해당 DB에 대한 
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class TestOracle{

	public static void main(String[] args){

		String url = "jdbc:oracle://localhost:3306/javase"; // 정해진 문자열 ( 암기 )
		String user = "root";
		String pass = "1234";
		Connection con = null; // 접속 성공 후 , 접속 정보를 가진 객체
		PreparedStatement pstmt = null; // 쿼리문 수행 객체
		ResultSet rs = null; // select문 수행 후 그 결과 표집합을 담는 객체
		/*
		1.사용하려는 DB에 맞는 드라이버 로드(클래스 로드)
		    Class.forName("원하는 클래스명")
		2.DB접속 
		3.쿼리문 수행
		4.DB관련 객체 해제
		*/
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 로드 성공!!");
			
			con = DriverManager.getConnection(url, user, pass);


			if(con != null){
				System.out.println("접속 성공");
				
				// 쿼리 실행이 아닌, 쿼리를 실행 할 수 있는 객체 생성 즉 준비만 한거다.
				pstmt = con.prepareStatement("select * from member");

				rs = pstmt.executeQuery(); //select문인 경우 반환객체가 있기 때문에
				
				while(rs.next()){
					// 현재 커서가 가리키는 row의 컬럼들을 하나씨 접근해보자!
					/*
					int member_id = rs.getInt("member_id"); // 인수로는 컬럼명이 온다.
					String user_id = rs.getString("user_id"); // 인수로는 컬럼명이 온다.
					String password = rs.getString("password"); // 인수로는 컬럼명이 온다.
					String name = rs.getString("name"); //인수로는 컬럼명이 온다.
					String regdate = rs.getString("regdate"); // 인수로는 컬럼명이 온다.
					*/
					
					// 컬럼의 index로도 접근해보자.
					int member_id = rs.getInt(1); // 첫번째 컬럼
					String user_id = rs.getString(2); // 2번째 컬럼
					String password = rs.getString(3); // 3번째 컬럼
					String name = rs.getString(4); // 4번째 컬럼
					String regdate = rs.getString(5); // 5번째 컬럼

					System.out.println(member_id+"\t"+user_id+"\t"+password+"\t"+name+"\t"+regdate);

				}
				//ResultSet이 반환된 직후에는 이떠한 레코드도 가르키지 않고 있다.
			}else{
				System.out.println("접속 실패");
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace(); //개발자가 원인을 분석하기 위한 출력
			System.out.println("드라이버를 찾을 수 없습니다ㅜㅜ");//유저를 위한 친절한 메시지

		}catch(SQLException e){ // 욕먹는다. 욕먹는 이유는 ?
			// DriverManager.getConenction() 으로 접속시도하다가 에러가 나도 SQLException
			// 쿼리문 수행시 에러가 나ㅏ도 SQLException이다.
			// 즉 sun에서는 세분화시키지 않았다. 즉 SQLException이 너무 광범위한 예외를 담당한다.ㄴ
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException e){e.printStackTrace();}
			}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){e.printStackTrace();}
			}
			if(con != null){
				try{con.close();}catch(SQLException e){e.printStackTrace();}
			}
		}

	}
}