package com.zjq.model;

public class SenseData {
	private int id;
	private int addressId;//设备Id
	private float voltage;// 电压
	private float current;// 电流
	private float temp;// 温度
	private float dampness;// 湿度
	private String update_time;//检测时间  ，有一定的日期格式，具体格式见数据库对应字段
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public float getVoltage() {
		return voltage;
	}
	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}
	public float getCurrent() {
		return current;
	}
	public void setCurrent(float current) {
		this.current = current;
	}
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	public float getDampness() {
		return dampness;
	}
	public void setDampness(float dampness) {
		this.dampness = dampness;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String updateTime) {
		update_time = updateTime;
	}
    
	
}
