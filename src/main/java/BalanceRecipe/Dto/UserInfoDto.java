package BalanceRecipe.Dto;

public class UserInfoDto {

	private String id;
	private String pw;
	private String name;
	private String birthday;
	private String  gender;
	private double height;
	private double weight;
	private double targetWeight;
	private double bmi;
	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getPw() {return pw;}
	public void setPw(String pw) {this.pw = pw;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getBirthday() {return birthday;}
	public void setBirthday(String birthday) {this.birthday = birthday;}
	public String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender;}
	public double getHeight() {return height;}
	public void setHeight(double height) {this.height = height;}
	public double getWeight() {return weight;}
	public void setWeight(double weight) {this.weight = weight;}
	public double getTargetWeight() {return targetWeight;}
	public void setTargetWeight(double targetWeight) {this.targetWeight = targetWeight;}
	public double getBmi() {return bmi;}
	public void setBmi(double bmi) {this.bmi = bmi;}
}
