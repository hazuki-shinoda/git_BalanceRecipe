/* home.jspからリンク(doGet)呼び出し
 * savemeal.jspへ移動
 * 
 * */

package BalanceRecipe;
import java.io.IOException;

import BalanceRecipe.Dto.UserInfoDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class InputSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	HttpSession session = request.getSession();
	        UserInfoDto loginUser = (UserInfoDto) session.getAttribute("LOGIN_INFO");
	        if (loginUser == null) {
	            response.sendRedirect("Login");
	            return;
	        }
	        request.setAttribute("USER_NAME_RAW", loginUser.getName());

	    	String msg = request.getParameter("msg");
	        if (msg != null) {
	            request.setAttribute("MSG", msg);
	        }
	        String error = request.getParameter("error");
	        if (error != null) {
	            String errorMsg = "エラーが発生しました。";
	            if ("1".equals(error)) {
	                errorMsg = "⚠️ 保存に失敗しました。入力内容を確認してください。";
	            } else if ("99".equals(error)) {
	                errorMsg = "⚠️ 数値の入力形式が正しくありません。";
	            }
	            request.setAttribute("ERROR_MSG", errorMsg); 
	        }
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/savemeal.jsp");
	        dispatcher.forward(request, response);
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
