package cn.itcast.itcaststore.domain;

public class User {
    private String name;
    private String password;
    private String email;
    private double money;
    private String id;
    private String role;
    
    
    /**用户名*/
    public String getId()
    {
    	return id;
    }
    public void setId(String id)
    {
    	this.id = id;
    }
    /**昵称*/
    public String getName()
    {
    	return name;
    }
    public void setName(String name)
    {
    	this.name = name;
    }
    
    /**密码*/
    public String getPassword()
    {
    	return password;
    }
    public void setPassword(String password)
    {
    	this.password = password;
    }

    
    /**邮箱*/
    public String getEmail()
    {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    
    /**金钱*/
    public double getMoney() {
    	return money;
    }
    public void setMoney(double money) {
    	this.money = money;
    }
    
    /**角色*/
    public String getRole() {
    	return role;
    }
    public void setRole(String role) {
    	this.role = role;
    }
}
