/*MealLogDaoクラスから呼び出される
 * 
 * 
 * */

package BalanceRecipe.Dto;

import java.io.Serializable;

public class FoodDto implements Serializable {
    private String id;
    private String name;
    private float calories;
    private float protein;
    private float fat;
    private float carbs; 
    private float salt;

    public FoodDto() {}

    public FoodDto(String id, String name, float calories, float protein, float fat, float carbs, float salt) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.salt = salt;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public float getCalories() { return calories; }
    public float getProtein() { return protein; }
    public float getFat() { return fat; }
    public float getCarbs() { return carbs; }
    public float getSalt() { return salt; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCalories(float calories) { this.calories = calories; }
    public void setProtein(float protein) { this.protein = protein; }
    public void setFat(float fat) { this.fat = fat; }
    public void setCarbs(float carbs) { this.carbs = carbs; }
    public void setSalt(float salt) { this.salt = salt; }

}