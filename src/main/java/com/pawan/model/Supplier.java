package com.pawan.model;
public class Supplier {
    private int id;
    private String supp_name;
    private String contact_Info;
    private int order_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSupp_name() {
		return supp_name;
	}
	public void setSupp_name(String supp_name) {
		this.supp_name = supp_name;
	}
	public String getContact_Info() {
		return contact_Info;
	}
	public void setContact_Info(String contact_Info) {
		this.contact_Info = contact_Info;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
}
