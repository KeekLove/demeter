package com.demeter.restaurant.pojo;

public class OrderDto {
    private String foodName;
    private String type;
    private Integer num;
    private Float prize;

    public OrderDto(String foodName, String type, Integer num, Float prize) {
        this.foodName = foodName;
        this.type = type;
        this.num = num;
        this.prize = prize;
    }

    public OrderDto() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Float getPrize() {
        return prize;
    }

    public void setPrize(Float prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "foodName='" + foodName + '\'' +
                ", type='" + type + '\'' +
                ", num=" + num +
                ", prize=" + prize +
                '}';
    }
}
