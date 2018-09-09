package com.easyweb.db.model;
//查询时，可按品牌和燃油类型两个条件进行查询。
public class Car {
	private Long id;// 主键
	private String brand;// 品牌，不能为空，最多24个字符，例如宝马、蓝鸟等
	private String color;// 车身颜色，不能为空。
	private String fuelType;// 燃油类型，只能选择 汽油、柴油、电力。
	private float price;// 价格必须大于0
	private String comments;// 备注，可以为空
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", color=" + color + ", fuelType=" + fuelType + ", price=" + price
				+ ", comments=" + comments + "]";
	}
	
}
