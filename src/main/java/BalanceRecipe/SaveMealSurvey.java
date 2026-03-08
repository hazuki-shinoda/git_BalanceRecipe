/* doGetメソッド：
 * 	■ savemeal-check.jsのgoToSearch() からの呼び出し
 * 		action = search
 * 		searchResult.jspへ移動する
 *  ■ searchResult.jspからの呼び出し
 * 		action = apply
 * 		savemeal.jspへ移動する
 * */


package BalanceRecipe;

import java.io.IOException;
import java.util.List;

import BalanceRecipe.Dao.FoodDao;
import BalanceRecipe.Dao.MealLogDao;
import BalanceRecipe.Dto.FoodDto;
import BalanceRecipe.Dto.MealLogDto;
import BalanceRecipe.Dto.UserInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class SaveMealSurvey extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        FoodDao dao = new FoodDao();

        if ("search".equals(action)) {
            String keyword = request.getParameter("MEAL");
            String currentWeight = request.getParameter("weight");
            request.setAttribute("preWeight", currentWeight); 
            
            List<FoodDto> list = dao.searchByName(keyword);
            request.setAttribute("foodList", list);
            request.getRequestDispatcher("jsp/searchResult.jsp").forward(request, response);
            
        } else if ("apply".equals(action)) {
            String id = request.getParameter("selectedId");
            String gramsStr = request.getParameter("grams");
            String weight = request.getParameter("weight");
           
            double grams = 100.0;
            if (gramsStr != null && !gramsStr.isEmpty()) {
                grams = Double.parseDouble(gramsStr);
            }

            FoodDto food = dao.findById(id);

            if (food != null) {
                double ratio = grams / 100.0;

                request.setAttribute("CALORIE", (double) Math.round(food.getCalories() * ratio * 10.0) / 10.0);
                request.setAttribute("PROTEIN", (double) Math.round(food.getProtein() * ratio * 10.0) / 10.0);
                request.setAttribute("FAT",     (double) Math.round(food.getFat() * ratio * 10.0) / 10.0);
                request.setAttribute("CARB",    (double) Math.round(food.getCarbs() * ratio * 10.0) / 10.0);
                request.setAttribute("SAVED_WEIGHT", weight);
                request.setAttribute("MEAL_NAME", food.getName());
            }
            
            UserInfoDto loginUser = (UserInfoDto) request.getSession().getAttribute("LOGIN_INFO");
            if (loginUser != null) {
                request.setAttribute("USER_NAME_RAW", loginUser.getName());
            }

            request.getRequestDispatcher("jsp/savemeal.jsp").forward(request, response);
        }
    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
            
            UserInfoDto loginUser = (UserInfoDto) request.getSession().getAttribute("LOGIN_INFO");
            try {
            	MealLogDto dto = new MealLogDto();
            	dto.setUserId(loginUser.getId());
                dto.setMealDate(request.getParameter("mealDate"));
                dto.setMealType(request.getParameter("mealType"));
                dto.setFoodName(request.getParameter("MEAL"));
                
                dto.setCalorie(Double.parseDouble(request.getParameter("calorie")));
                dto.setProtein(Double.parseDouble(request.getParameter("protein")));
                dto.setFat(Double.parseDouble(request.getParameter("fat")));
                dto.setCarbohydrate(Double.parseDouble(request.getParameter("carbohydrate")));
               dto.setWeight(Double.parseDouble(request.getParameter("weight")));

                MealLogDao dao = new MealLogDao();
                boolean success = dao.saveMealLog(dto);
                
                if (success) {
                    response.sendRedirect("Home?msg=success");
                } else {
                    response.sendRedirect("InputSurvey?error=1");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("InputSurvey?error=99");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("InputSurvey?error=1");
            }

    }
}