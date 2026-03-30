/* FoodDao
 * DBの接続
 * ◼︎AppInitListenerから呼び出される
 * 　isDataEmpty()：food_dictionaryのデータが0件か確認
 * 　importFromCsv(String filePath) : isDataEmpty() がtrueであれば読み込む
 * ◼searchByName(String keyword)：
 * SaveMealSurveyから呼び出される
 * 入力された食事をfood_dictionaryのなかでLIKE検索、List<FoodDto>で返す
 * ◼findById(String id)：		
 * SaveMealSurveyから呼び出される
 * food_dictionaryのなかで各パラメーターをFoodDtoに収納、FoodDtoで返す
 */

package BalanceRecipe.Dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import BalanceRecipe.Dto.FoodDto;

public class FoodDao {
    private final String URL  = System.getenv("JDBC_DATABASE_URL");
    private final String USER = System.getenv("JDBC_DATABASE_USERNAME");
    private final String PASS = System.getenv("JDBC_DATABASE_PASSWORD");

    public boolean isDataEmpty() {
        String sql = "SELECT COUNT(*) FROM food_dictionary";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void importFromCsv(String filePath) {
        String sql = "INSERT INTO food_dictionary (id, name, calories, protein, fat, carbs, vit_a, vit_d, vit_e, vit_b1, vit_b2, vit_c, salt) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
        	String header = br.readLine();
        	if (header == null || header.isEmpty()) { //一行目、ヘッダー
            	System.err.println("CSVファイルが空です。CSVファイルの読み取りに失敗しました。ファイルパス: " + filePath);
                return;
            }
            
            String line; //2行目以降
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                conn.setAutoCommit(false);

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",", -1);
                    if (data.length < 13) continue;

                    pstmt.setString(1, data[0]);
                    pstmt.setString(2, data[1]);
                    pstmt.setDouble(3, parseDouble(data[2]));
                    pstmt.setDouble(4, parseDouble(data[3]));
                    pstmt.setDouble(5, parseDouble(data[4]));
                    pstmt.setDouble(6, parseDouble(data[5]));
                    pstmt.setDouble(7, parseDouble(data[6]));
                    pstmt.setDouble(8, parseDouble(data[7]));
                    pstmt.setDouble(9, parseDouble(data[8]));
                    pstmt.setDouble(10, parseDouble(data[9]));
                    pstmt.setDouble(11, parseDouble(data[10]));
                    pstmt.setDouble(12, parseDouble(data[11]));
                    pstmt.setDouble(13, parseDouble(data[12]));
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                conn.commit();
                System.out.println("食品データのインポートに成功しました。");
            }
        } catch (Exception e) {
            System.err.println("CSVファイルの読み取りまたはDB登録に失敗しました。");
            e.printStackTrace();
        }
    }

    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public List<FoodDto> searchByName(String keyword) {
        List<FoodDto> list = new ArrayList<>();
        String sql = "SELECT * FROM food_dictionary WHERE name LIKE ?";
        try (Connection conn = getConnection();
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