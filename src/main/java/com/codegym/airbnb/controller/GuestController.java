package com.codegym.airbnb.controller;

import com.codegym.airbnb.message.response.OrderDetail;
import com.codegym.airbnb.message.response.ResponseMessage;
import com.codegym.airbnb.message.response.UserOrderList;
import com.codegym.airbnb.model.*;
import com.codegym.airbnb.security.services.UserPrinciple;
import com.codegym.airbnb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/me")
public class GuestController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private OrderHouseService orderHouseService;


    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RateService rateService;

    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('HOST')")
    public ResponseEntity<ResponseMessage> listOrderOfGuest() {
//        List<OrderHouse> orderHouses = this.orderHouseService.findOrderHousesByTenantId(getCurrentUser().getId());
        long userId = getCurrentUser().getId();
        List<UserOrderList> userOrderLists = this.orderHouseService.userOrderLists(userId);

        if (userOrderLists.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get list orders that was booked by guest", userOrderLists),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{id}/house-of-order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> getHouseOfOrder(@PathVariable long id) {
        OrderHouse orderHouse = this.orderHouseService.findById(id);

        if (orderHouse == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

        HouseEntity house = orderHouse.getHouse();

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get the house of order", house),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> getDetailOrder(@PathVariable Long id) {
//        OrderHouse orderHouse = this.orderHouseService.findById(id);
        long userId = getCurrentUser().getId();
        long orderId = id;
        OrderDetail orderDetail = this.orderHouseService.findById(userId,orderId);

        if (orderDetail == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get detail order that was booked by guest", orderDetail),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{id}/delete", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> deleteOrderHouse(@PathVariable Long id) {
        OrderHouse orderHouse = this.orderHouseService.findById(id);
        Date checkin = orderHouse.getCheckin();
        Date now = new Date();
        int day = 86400 * 1000;
        double nowToCheckinByDay = (double) (checkin.getTime() - now.getTime()) / day;
        if (nowToCheckinByDay < 1.0) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Cannot cancel the order", null),
                    HttpStatus.OK);
        }
        orderHouse.setStatusOrder(StatusOrder.CANCELED);
        this.orderHouseService.updateOrderHouse(orderHouse);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Confirm order cancel", null),
                HttpStatus.OK);
    }

    @PostMapping("{houseId}/comments")
    public ResponseEntity<ResponseMessage> createComment(@RequestBody Comment comment, @PathVariable Long houseId) {
        comment.setUser(this.userService.findById(getCurrentUser().getId()));
        HouseEntity house = houseService.findById(houseId);
        comment.setHouse(house);
        this.commentService.createComment(comment);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Comment Successful", null),
                HttpStatus.CREATED);
    }

    @PostMapping("/rates")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseMessage> createRate(@RequestBody Rate rate) {
        rate.setUser(this.userService.findById(getCurrentUser().getId()));
        if (this.rateService.existsRateByUserIdAndHouseId(rate.getUser().getId(), rate.getHouse().getId() ) ){
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(true, "You can rate one", null),
                    HttpStatus.CREATED);
        }
        this.rateService.createRate(rate);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Rate Successful", null),
                HttpStatus.CREATED);
    }

    @GetMapping("/rates/{houseId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseMessage> getRateByUserIdAndHouseId(@PathVariable Long houseId){
        Rate rate = this.rateService.findByUserIdAndHouseId(getCurrentUser().getId(), houseId);
        if(rate == null){
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(false, "You have not rate this house!", null), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<ResponseMessage>(new ResponseMessage(true, "House Rate", rate), HttpStatus.OK);
    }

}
