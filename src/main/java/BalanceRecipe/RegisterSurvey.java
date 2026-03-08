/* login.jspから新規登録リンク（doGet)で呼び出し
 * register.jsp で情報を入力、doPostが呼び出される
 * 
 * */
package BalanceRecipe;

import java.io.IOException;

import BalanceRecipe.Dao.UserInfoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/register.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String pw = request.getParameter("password");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");
        
        try {
	        double height = Double.parseDouble(request.getParameter("height"));
	        double weight = Double.parseDouble(request.getParameter("weight"));
	        double targetWeight = Double.parseDouble(request.getParameter("target_weight"));
	        double bmi = 0;
	        if (height > 0) {
	        	bmi = weight / ((height/100) * (height/100));
	        	bmi = Math.round(bmi * 10.0) / 10.0; 
	        }
	        UserInfoDao dao = new UserInfoDao();
	        boolean success = dao.doRegist(id, pw, name, birthday, gender, height, weight, targetWeight, bmi);
	      
	        if (success) { 
	        	request.setAttribute("registeredName", name);
	            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	        } else {
	            response.sendRedirect(request.getContextPath() + "/jsp/register.jsp?error=1");
		    }
		}catch(NumberFormatException e){
	        response.sendRedirect(request.getContextPath() + "/jsp/register.jsp?error=99");
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect(request.getContextPath() + "/jsp/register.jsp?error=1");
	    }
			
	}}

