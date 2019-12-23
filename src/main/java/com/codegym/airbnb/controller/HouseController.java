package com.codegym.airbnb.controller;

import com.codegym.airbnb.message.response.*;
import com.codegym.airbnb.model.*;
import com.codegym.airbnb.security.services.UserPrinciple;
import com.codegym.airbnb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HouseController {

    @Autowired
    private HouseService houseService;

//    @Autowired
//    private ImageHouseService imageHouseService;

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

    @Autowired
    private StatusHouseService statusHouseService;

    @RequestMapping(value = "/houses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage> listAllHouse(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<HouseList> houses = this.houseService.getListHouse(1, 2);

        if (houses.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

//        for (House house : houses) {
//            List<String> listImageUrlOfHouse = imageHouseService.getListImageUrlOfHouseByHouseId(house.getId());
//            house.setImageUrls(listImageUrlOfHouse);
//            List<OrderHouse> orderHouses = orderHouseService.findOrderHousesByHouseId(house.getId());
//            house.setOrderHouses(orderHouses);
//        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get list all house", houses),
                HttpStatus.OK);
    }

//    @RequestMapping(value = "/houses/{id}", method = RequestMethod.GET)
//    public ResponseEntity<ResponseMessage> getHouse(@PathVariable Long id) {
//        House house = this.houseService.findById(id);
//
//        if (house == null) {
//            return new ResponseEntity<ResponseMessage>(
//                    new ResponseMessage(false, "Fail. Not found data", null),
//                    HttpStatus.OK);
//        }
//
////        List<String> listImageUrlOfHouse = imageHouseService.getListImageUrlOfHouseByHouseId(house.getId());
////        house.setImageUrls(listImageUrlOfHouse);
//        return new ResponseEntity<ResponseMessage>(
//                new ResponseMessage(true, "Successfully. Get detail house", house),
//                HttpStatus.OK);
//    }

    @RequestMapping(value = "/houses2/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> getHouseNative(@PathVariable Long id) {
        HouseDetail house = this.houseService.getHouseDetailById(id);

        if (house == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get detail house", house),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/houses/{id}/booking", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> bookingHouse(@PathVariable Long id, @RequestBody OrderHouse orderHouse) {
        boolean isBooked =
                orderHouseService.existsOrderHouseByCheckinGreaterThanEqualAndCheckinLessThanEqualAndHouseId(
                        orderHouse.getCheckin(), orderHouse.getCheckout(), id)
                        || orderHouseService.existsOrderHouseByCheckoutGreaterThanEqualAndCheckoutLessThanEqualAndHouseId(
                        orderHouse.getCheckin(), orderHouse.getCheckout(), id)
                        || orderHouseService.existsOrderHouseByCheckinGreaterThanEqualAndCheckoutLessThanEqualAndHouseId(
                        orderHouse.getCheckin(), orderHouse.getCheckout(), id)
                        || orderHouseService.existsOrderHouseByCheckinLessThanEqualAndCheckoutGreaterThanEqualAndHouseId(
                        orderHouse.getCheckin(), orderHouse.getCheckout(), id)
                        || orderHouseService.existsStatusHouseByEndDateGreaterThanEqualAndEndDateLessThanEqual(orderHouse.getCheckin(), orderHouse.getCheckout(), id)
                        || orderHouseService.existsStatusHouseByStartDateGreaterThanEqualAndEndDateLessThanEqual(orderHouse.getCheckin(), orderHouse.getCheckout(), id)
                        || orderHouseService.existsStatusHouseByStartDateGreaterThanEqualAndStartDateLessThanEqual(orderHouse.getCheckin(), orderHouse.getCheckout(), id)
                        || orderHouseService.existsStatusHouseByStartDateLessThanEqualAndEndDateGreaterThanEqual(orderHouse.getCheckin(), orderHouse.getCheckout(), id);
        if (isBooked) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Another customer already booked this date. Please try to book another date.", null),
                    HttpStatus.OK);
        }
        HouseEntity house = houseService.findById(id);
        orderHouse.setHouse(house);
        User tenant = userService.findById(getCurrentUser().getId());
        orderHouse.setTenant(tenant);
        orderHouse.setStatusOrder(StatusOrder.PROCESSING);
        orderHouseService.createOrderHouse(orderHouse);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Booking success!", null),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/house/all-user-order", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> allUserOder() {
        List<OrderHouse> orderHouses = orderHouseService.findAll();
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(true, "list all order", orderHouses), HttpStatus.OK);
    }


    @RequestMapping(value = "/statusHouses/{houseId}", method = RequestMethod.GET)
    private ResponseEntity<ResponseMessage> listStatusHouse(@PathVariable Long houseId) {
        List<StatusHouse> statusHouses = this.statusHouseService.findAllByHouseId(houseId);

        if (statusHouses.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(true, "Successfully but not found data", null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get list status houses", statusHouses),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/category-list", method = RequestMethod.GET)
    private ResponseEntity<ResponseMessage> listCategoryHouse() {
        List<CategoryList> categoryLists = this.houseService.getListCategory();

        if (categoryLists.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get list all category", categoryLists),
                HttpStatus.OK);

    }

    @RequestMapping(value = "/comments/{houseId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> listCommentsbyHouseId(@PathVariable Long houseId) {
        List<CommentList> commentLists = this.commentService.findAllByHouseId(houseId);

        if (commentLists.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get list comment that was booked by guest", commentLists),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/rates/{houseId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> listRatesbyHouseId(@PathVariable Long houseId) {
        List<Rate> rates = this.rateService.findAllByHouseId(houseId);

        if (rates.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get list comment that was booked by guest", rates),
                HttpStatus.OK);
    }
}