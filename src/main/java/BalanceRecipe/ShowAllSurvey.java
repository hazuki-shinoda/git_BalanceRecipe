/*
 * 食事履歴一覧を表示するためのサーブレット
 * 過去入力された食事記録のリストを持ってshowall.jspへ移動
 * */

package BalanceRecipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import BalanceRecipe.Dao.MealLogDao;
import BalanceRecipe.Dto.MealLogDto;
import BalanceRecipe.Dto.UserInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ShowAllSurvey extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserInfoDto loginUser = (UserInfoDto) session.getAttribute("LOGIN_INFO");
 
        request.setAttribute("USER_NAME_RAW", loginUser.getName());
        MealLogDao dao = new MealLogDao();
        List<MealLogDto> list = new ArrayList<>();
        try {
            List<MealLogDto> result = dao.getAllLogs(loginUser.getId());
            if (result != null) {list = result;}
        } catch (Exception e) {e.printStackTrace();}
        
        for (MealLogDto dto : list) {
            dto.setFoodName(Util.replaceEscapeChar(dto.getFoodName()));
            dto.setMealType(Util.replaceEscapeChar(dto.getMealType()));
        }

        request.setAttribute("MEAL_LIST", list);
        request.getRequestDispatcher("jsp/showall.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 }