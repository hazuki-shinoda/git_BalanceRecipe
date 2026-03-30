package BalanceRecipe;

import BalanceRecipe.Dao.FoodDao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener 
public class AppInitListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
    	System.out.println("--- AppInitListener: 起動 ---");
        FoodDao dao = new FoodDao();
        if (dao.isDataEmpty()) {
            String path = sce.getServletContext().getRealPath("/WEB-INF/food_data.csv");
            dao.importFromCsv(path);
        }
    }
}