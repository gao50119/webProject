package cn.itcast.itcaststore.domain;

import java.util.Date;

public class Order {

	private String oNo;
	private double oMoney;
	private int clientState;
	private int adminState;
	private Date ordertime;

	private User user;
	private User saler;
	private Product product;
	

	public String getoNo() {
		return oNo;
	}

	public void setoNo(String oNo) {
		this.oNo = oNo;
	}

	public double getoMoney() {
		return oMoney;
	}

	public void setoMoney(double oMoney) {
		this.oMoney = oMoney;
	}

	public int getClientState() {
		return clientState;
	}

	public void setClientState(int clientState) {
		this.clientState = clientState;
	}
	
	public int getAdminState() {
		return adminState;
	}

	public void setAdminState(int adminState) {
		this.adminState = adminState;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public User getSaler() {
		return saler;
	}

	public void setSaler(User saler) {
		this.saler = saler;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [oNo=" + oNo + ", clientState=" + clientState + ", adminState=" + adminState
				+ ", ordertime=" + ordertime + ", user=" + user + ", saler=" + saler
				+ ", product=" + product + "]";
	}

}
