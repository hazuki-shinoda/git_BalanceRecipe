/* Homeサーブレット
 * ◼︎️初回呼び出し
 * 	ログインできていたら呼び出される
 * 	MealLogDaoからその日に摂取した栄養素を取得
 * 	home.jspへ送るため各栄養素の合計値をセットする
 * ◼SaveMealSurvey経由で呼び出し
 * 	食事を入力し、DBへのinsertが成功ならmsg = success 
 * 
 * */
package BalanceRecipe;

import java.io.IOException;

import BalanceRecipe.Dao.MealLogDao;
import BalanceRecipe.Dto.FoodDto;
import BalanceRecipe.Dto.UserInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UserInfoDto loginUser = (UserInfoDto) session.getAttribute("LOGIN_INFO");

        if (loginUser != null) {
        	request.setAttribute("USER_NAME_RAW", loginUser.getName()); 
        	String msg = request.getParameter("msg");
	            if ("success".equals(msg)) {
	                request.setAttribute("MSG", "☑️食事の記録を保存しました。");
	            }
            MealLogDao dao = new MealLogDao();
            FoodDto total=null;
			try {
				total = dao.getTodaysTotal(loginUser.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (total != null) {
                request.setAttribute("TOTAL_CAL",  Math.round(total.getCalories() * 10.0) / 10.0);
                request.setAttribute("TOTAL_PRO",  Math.round(total.getProtein() * 10.0) / 10.0);
                request.setAttribute("TOTAL_FAT",  Math.round(total.getFat() * 10.0) / 10.0);
                request.setAttribute("TOTAL_CARB", Math.round(total.getCarbs() * 10.0) / 10.0);
            } else {
                request.setAttribute("TOTAL_CAL",  0.0);
                request.setAttribute("TOTAL_PRO",  0.0);
                request.setAttribute("TOTAL_FAT",  0.0);
                request.setAttribute("TOTAL_CARB", 0.0);
            }

            request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
        } else {
            response.sendRedirect("Login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {                
        String redirectUrl = "InputSurvey"; 
        String msg = request.getParameter("msg"); 
        if ("success".equals(msg)) {
            redirectUrl += "?msg=success";
        }
        response.sendRedirect(redirectUrl);
    }
}