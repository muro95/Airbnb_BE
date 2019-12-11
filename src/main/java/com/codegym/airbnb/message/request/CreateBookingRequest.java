package com.codegym.airbnb.message.request;

import com.codegym.airbnb.model.OrderHouse;
import com.codegym.airbnb.model.StatusOrder;

import java.util.Date;

public class CreateBookingRequest {
    private Long house;
    private Long tenant;
    private Date checkin;
    private Date checkout;
    private Long numberGuest;
    private Long cost;
    private Date orderTime;
    private StatusOrder statusOrder;

//    public OrderHouse cloneToEntity() {
//        OrderHouse orderHouse = new OrderHouse();
////        orderHouse.setHouse(house);
////        orderHouse.setTenant(tenant);
//        orderHouse.setCheckin(checkin);
//        orderHouse.setCheckout(checkout);
//        orderHouse.setNumberGuest(numberGuest);
//        orderHouse.setCost(cost);
//        orderHouse.setOrderTime(orderTime);
//        orderHouse.setStatusOrder(statusOrder.PROCESSING);
//        return  orderHouse;
//    }
    public Long getHouse() {
        return house;
    }

    public void setHouse(Long house) {
        this.house = house;
    }

    public Long getTenant() {
        return tenant;
    }

    public void setTenant(Long tenant) {
        this.tenant = tenant;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Long getNumberGuest() {
        return numberGuest;
    }

    public void setNumberGuest(Long numberGuest) {
        this.numberGuest = numberGuest;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }
}
