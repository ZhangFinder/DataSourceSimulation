package com.zjq.model;

public class SenseData {
	private int id;
	private int addressId;//�豸Id
	private float voltage;// ��ѹ
	private float current;// ����
	private float temp;// �¶�
	private float dampness;// ʪ��
	private String update_time;//���ʱ��  ����һ�������ڸ�ʽ�������ʽ�����ݿ��Ӧ�ֶ�
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
