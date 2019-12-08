package com.codegym.airbnb.controller;


import com.codegym.airbnb.message.response.ResponseMessage;
import com.codegym.airbnb.model.HouseEntity;
import com.codegym.airbnb.model.OrderHouse;
import com.codegym.airbnb.security.services.UserPrinciple;
import com.codegym.airbnb.service.HouseService;
import com.codegym.airbnb.service.OrderHouseService;
import com.codegym.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//must login with guest role
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

    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST') or hasRole('ADMIN') or hasRole('HOST')")
    public ResponseEntity<ResponseMessage> listOrderOfGuest() {
        List<OrderHouse> orderHouses = this.orderHouseService.findOrderHousesByTenantId(getCurrentUser().getId());

        if (orderHouses.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get list orders that was booked by guest", orderHouses),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{id}/house-of-order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST') or hasRole('ADMIN')")
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
}
