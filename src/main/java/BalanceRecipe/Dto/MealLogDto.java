package BalanceRecipe.Dto;

public class MealLogDto {
    private int id;
    private String userId;   
    private String mealDate;
    private String mealType;
    private String foodName;
    private double calorie;
    private double protein;
    private double fat;
    private double carbohydrate;
    private double weight;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMealDate() { return mealDate; }
    public void setMealDate(String mealDate) { this.mealDate = mealDate; }
    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public double getCalorie() { return calorie; }
    public void setCalorie(double calorie) { this.calorie = calorie; }
    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }
    public double getFat() { return fat; }
    public void setFat(double fat) { this.fat = fat; }
    public double getCarbohydrate() { return carbohydrate; }
    public void setCarbohydrate(double carbohydrate) { this.carbohydrate = carbohydrate; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
}