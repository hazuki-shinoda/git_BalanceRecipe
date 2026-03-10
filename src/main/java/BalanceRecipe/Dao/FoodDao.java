/*FoodDao
 * DBへの接続 
 * searchByName(String keyword)メソッド：
 *  SaveMealSurveyから呼び出される
 * 	入力された食事をfood_dictionaryのなかでLIKE検索、List<FoodDto>で返す
 * findById(String id)メソッド：		
 * 	SaveMealSurveyから呼び出される
 *  food_dictionaryのなかで各パラメーターをFoodDtoに収納、FoodDtoで返す
 */

package BalanceRecipe.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import BalanceRecipe.Dto.FoodDto;

public class FoodDao {
	private final String URL    = System.getenv("DB_URL");
	private final String USER     = System.getenv("DB_USER");
	private final String PASS   = System.getenv("DB_PASS");

    public List<FoodDto> searchByName(String keyword) {
        List<FoodDto> list = new ArrayList<>();
        String sql = "SELECT * FROM food_dictionary WHERE name LIKE ?";
        	try (
        		Connection conn = getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
        			pstmt.setString(1, "%" + keyword + "%");
        			try (ResultSet rs = pstmt.executeQuery()) {
        				while (rs.next()) {
        					FoodDto dto = new FoodDto(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getFloat("calories"),
                            rs.getFloat("protein"),
                            rs.getFloat("fat"),
                            rs.getFloat("carbs"),
                            rs.getFloat("salt")
                        );
                        list.add(dto);
                    }
                }
        } catch (Exception e) {
        	e.printStackTrace();
        	}
        return list;
        }
    
    public FoodDto findById(String id) {
        FoodDto dto = null;
        String sql = "SELECT * FROM food_dictionary WHERE id = ?";
        try (Connection conn = getConnection();
        
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, id); 
            try (ResultSet rs = pstmt.executeQuery()) {
            	if (rs.next()) {
            		dto = new FoodDto(
            				rs.getString("id"),
                            rs.getString("name"),
                            rs.getFloat("calories"),
                            rs.getFloat("protein"),
                            rs.getFloat("fat"),
                            rs.getFloat("carbs"),
                            rs.getFloat("salt")
                        );
                    }
                }
        } catch (Exception e) { 
        	e.printStackTrace();
        	}
        return dto; 
    }
    
    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}