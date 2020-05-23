package cn.itcast.itcaststore.domain;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String gNo;
	private String gName;
	private double gPrice;
	private String gType;
	private String gImgurl;
	private String gDescription;
	private Date gPostTime;
	private double gScore;
	private int gState;
	private int number;
	

	public String getgNo() {
		return gNo;
	}

	public void setgNo(String gNo) {
		this.gNo = gNo;
	}
	
	public int getNum() {
		return number;
	}

	public void setNum(int number) {
		this.number = number;
	}
	
	public int getgState() {
		return gState;
	}

	public void setgState(int gState) {
		this.gState = gState;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public double getgPrice() {
		return gPrice;
	}

	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}

	public String getgType() {
		return gType;
	}

	public void setgType(String gType) {
		this.gType = gType;
	}

	public String getgImgurl() {
		return gImgurl;
	}

	// a.jpg a_s.jpg
	public String getgImgurl_s() {
		int index = gImgurl.lastIndexOf("."); // �õ����.������
		String first = gImgurl.substring(0, index);

		String last = gImgurl.substring(index);

		return first + "_s" + last;
	}

	public void setgImgurl(String gImgurl) {
		this.gImgurl = gImgurl;
	}

	public String getgDescription() {
		return gDescription;
	}

	public void setgDescription(String gDescription) {
		this.gDescription = gDescription;
	}
	
	public double getgScore() {
		return gScore;
	}

	public void setgScore(double gScore) {
		this.gScore = gScore;
	}

	public Date getgPostTime() {
		return gPostTime;
	}

	public void setgPostTime(Date gPostTime) {
		this.gPostTime = gPostTime;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gNo == null) ? 0 : gNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (gNo == null) {
			if (other.gNo != null)
				return false;
		} else if (!gNo.equals(other.gNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [gNo=" + gNo + ", gName=" + gName + ", gPrice=" + gPrice + ", gState=" + gState
				+ ", gType=" + gType + ", gScore=" + gScore + ", gPostTime=" + gPostTime + ", gImgurl="
				+ gImgurl + ", gDescription=" + gDescription + ", totalSaleNum="
				+ "]";
	}

}
