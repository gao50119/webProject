package cn.itcast.itcaststore.domain;

public class User {
    private String name;
    private String password;
    private String email;
    private double money;
    private String id;
    private String role;
    private String gtype;
    
    
    /**�û���*/
    public String getId()
    {
    	return id;
    }
    public void setId(String id)
    {
    	this.id = id;
    }
    /**�ǳ�*/
    public String getName()
    {
    	return name;
    }
    public void setName(String name)
    {
    	this.name = name;
    }
    
    /**����*/
    public String getPassword()
    {
    	return password;
    }
    public void setPassword(String password)
    {
    	this.password = password;
    }

    
    /**����*/
    public String getEmail()
    {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    
    /**��Ǯ*/
    public double getMoney() {
    	return money;
    }
    public void setMoney(double money) {
    	this.money = money;
    }
    
    /**��ɫ*/
    public String getRole() {
    	return role;
    }
    public void setRole(String role) {
    	this.role = role;
    }
    
    public String getgType() {
    	return gtype;
    }
    public void setgType(String gtype) {
    	this.gtype = gtype;
    }
}
