/* getTodaysTotal(String userId) メソッド ： 
 * 	Homeサーブレットから呼び出される
 * 	その日に摂取した栄養素の合計を計算するSQLの実行
 * 		cal, fat, pro, carb をセットして合計をFoodDtoで返す
 * saveMealLog(Id, Date,,,) メソッド ： 
 *  SaveMealSurveyサーブレットdoPostから呼び出される
 *  DBへパラメータをinsertで保存するSQLの実行
 * */

package BalanceRecipe.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BalanceRecipe.Dto.FoodDto;
import BalanceRecipe.Dto.MealLogDto;

public class MealLogDao {
	private final String URL    = System.getenv("DB_URL");
	private final String USER     = System.getenv("DB_USER");
	private final String PASS   = System.getenv("DB_PASS");
    
    public FoodDto getTodaysTotal(String userId) throws Exception {
        String today = java.time.LocalDate.now().toString();
        FoodDto total = new FoodDto();
        
        String sql = "SELECT SUM(calorie) as cal, SUM(protein) as pro, " +
                     "SUM(fat) as fat, SUM(carbohydrate) as carb " +
                     "FROM meal_logs WHERE user_id = ? AND meal_date = ?";

        try (Connection con  = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setString(2, today);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total.setCalories(rs.getFloat("cal"));
                total.setProtein(rs.getFloat("pro"));
                total.setFat(rs.getFloat("fat"));
                total.setCarbs(rs.getFloat("carb"));
                }
        } catch (Exception e) {e.printStackTrace();}
        return total;
    }
    
    public boolean saveMealLog(MealLogDto dto) {
        
            String sql = "INSERT INTO meal_logs (user_id, meal_date, meal_type, food_name, calorie, protein, fat, carbohydrate, weight) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            

            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
            
            	ps.setString(1, dto.getUserId());
                ps.setString(2, dto.getMealDate());
                ps.setString(3, dto.getMealType());
                ps.setString(4, dto.getFoodName());
                ps.setDouble(5, dto.getCalorie());
                ps.setDouble(6, dto.getProtein());
                ps.setDouble(7, dto.getFat());
                ps.setDouble(8, dto.getCarbohydrate());
                ps.setDouble(9, dto.getWeight());
            
            return ps.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
           
        }

    public List<MealLogDto> getAllLogs(String userId) throws Exception {
        List<MealLogDto> list = new ArrayList<>();
        String sql = "SELECT * FROM meal_logs WHERE user_id = ? ORDER BY meal_date DESC, id DESC";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MealLogDto dto = new MealLogDto();
                dto.setId(rs.getInt("id"));
                dto.setMealDate(rs.getString("meal_date"));
                dto.setMealType(rs.getString("meal_type"));
                dto.setFoodName(rs.getString("food_name"));
                dto.setCalorie(rs.getDouble("calorie"));
                dto.setProtein(rs.getDouble("protein"));
                dto.setFat(rs.getDouble("fat"));
                dto.setCarbohydrate(rs.getDouble("carbohydrate"));
                dto.setWeight(rs.getDouble("weight"));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
}