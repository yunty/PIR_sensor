package week9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class insert_db {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public insert_db() {
		
	}
	public static void create(String s1,String s2,int i)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt = null;
		String url = "jdbc:mysql://localhost:3307/pir_state?user=root&password=s3467423@";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			String sql = "INSERT INTO pir_state VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s1);
			pstmt.setString(2,s2);
			pstmt.setInt(3, i);
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패");
            }
            else{
                System.out.println("데이터 입력 성공");
            }
		}catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch( SQLException e){
            System.out.println("에러 " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
                if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
	}

}
