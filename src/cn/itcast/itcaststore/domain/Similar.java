package cn.itcast.itcaststore.domain;

public class Similar {
    private String id;
    private String simi;
    private double score;
    
    public String getId() {
    	return this.id;
    }
    public void setId(String id) {
    	this.id = id;
    }
    
    public String getSimi() {
    	return this.simi;
    }
    public void setSimi(String simi) {
    	this.simi = simi;
    }
    
    public double getScore() {
    	return this.score;
    }
    public void setScore(double score) {
    	this.score = score;
    }
}
