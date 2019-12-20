package com.codegym.airbnb.message.response;

public class UserOrderList {
    private Long id;
    private String checkin;
    private String checkout;
    private Long numberGuest;
    private Long children;
    private String orderTime;
    private Long house_id;
    private String houseName;

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public Long getNumberGuest() {
        return numberGuest;
    }

    public void setNumberGuest(Long numberGuest) {
        this.numberGuest = numberGuest;
    }

    public Long getChildren() {
        return children;
    }

    public void setChildren(Long children) {
        this.children = children;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Long getHouse_id() {
        return house_id;
    }

    public void setHouse_id(Long house_id) {
        this.house_id = house_id;
    }
}