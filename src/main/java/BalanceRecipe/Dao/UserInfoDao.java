/* UserInfoDao
 * doSelect(id, pw) : USERS DBへ接続、id, pw照会、登録されていれば UserInfoDtoを返す
 * doRegist(id,pw,,,) : RegisterSurveyから呼び出される　INSERT文でDBへ保存　booleanで返す
*/
package BalanceRecipe.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import BalanceRecipe.Dto.UserInfoDto;

public class UserInfoDao {
	private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL    = System.getenv("DB_URL");
	private final String USER_ID     = System.getenv("DB_USER");
	private final String USER_PASS   = System.getenv("DB_PASS");
	
	public UserInfoDto doSelect(String inputUserId, String inputPassWord) throws Exception {
		UserInfoDto dto = new UserInfoDto();
		String sql = "SELECT id, name FROM USERS WHERE id = ? AND password = ?";

		try (Connection con = getConnection();
			 PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, inputUserId);
	        ps.setString(2, inputPassWord);

	        try(ResultSet rs = ps.executeQuery();){
	        	if (rs.next()) {
	        		dto.setId(   rs.getString("id")       );
	        		dto.setName( rs.getString("name")     );
	        	}
	        }
		} catch (Exception e) {e.printStackTrace();} 
		return dto;
	}

	public boolean doRegist(String id,String pw, String name, String birthday,String  gender,double height,double weight,double targetWeight, double bmi) throws Exception {
		boolean regist =false;  
		String sql = "INSERT INTO USERS ( id, password, name, birthday, gender, height, weight, target_weight , bmi) "
				   + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			
		try (Connection con = getConnection();
			 PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, id);            
	            ps.setString(2, pw);            
	            ps.setString(3, name);          
	            ps.setString(4, birthday);      
	            ps.setString(5, gender);        
	            ps.setDouble(6, height);        
	            ps.setDouble(7, weight);        
	            ps.setDouble(8, targetWeight);  
	            ps.setDouble(9, bmi);
	            
	            int count = ps.executeUpdate(); 
	            
	            if (count > 0) {
	                regist = true;
	            }
			} catch (Exception e) { e.printStackTrace(); } 
			return regist;
		}
		
		private Connection getConnection() throws Exception {
	        Class.forName(DRIVER_NAME);
	        return DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
	    }
	}
