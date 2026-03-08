/* ExecuteLogin サーブレット
 * ログイン実行
 * セッションを取得し、未ログインならば入力したid, pwをDBで検索
 * ログイン成功ならば HOME サーブレット
 * 失敗ならば Login サーブレットに戻る
 */

package BalanceRecipe;

import java.io.IOException;

import BalanceRecipe.Dao.UserInfoDao;
import BalanceRecipe.Dto.UserInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ExecuteLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {       
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			response.sendRedirect("Home");
			return;
		} else {
			String userId   = request.getParameter("ID");     
			String passWord = request.getParameter("PW");   
			if (userId == null || passWord == null || userId.isEmpty() || passWord.isEmpty()) {
	            response.sendRedirect("Login");
	            return;
	        }
			UserInfoDao logic = new UserInfoDao();
			UserInfoDto dto = null;
			
			try {
				dto = logic.doSelect(userId, passWord);
				if (dto.getId() != null) {
					session.setAttribute("LOGIN_INFO", dto);
					response.sendRedirect("Home"); //ログイン成功
				} else {
					response.sendRedirect("Login");  //ログイン失敗
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}
}
