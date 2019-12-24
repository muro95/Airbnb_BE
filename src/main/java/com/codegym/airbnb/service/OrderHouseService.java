package com.codegym.airbnb.service;

import com.codegym.airbnb.message.response.OrderDetail;
import com.codegym.airbnb.message.response.UserOrderList;
import com.codegym.airbnb.message.response.UserOrderListOfHost;
import com.codegym.airbnb.model.OrderHouse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderHouseService {
    List<OrderHouse> findAll();

    List<OrderHouse> findOrderHousesByTenantId(long id);

    List<OrderHouse> findOrderHousesByHouseId(long id);

    List<Long> getOrderIdsByHouseId(long id);

    OrderHouse findById(Long id);

    boolean existsOrderHouseByCheckinGreaterThanEqualAndCheckinLessThanEqualAndHouseId(Date checkin, Date checkout, Long houseId);

    boolean existsOrderHouseByCheckoutGreaterThanEqualAndCheckoutLessThanEqualAndHouseId(Date checkin, Date checkout, Long houseId);

    boolean existsOrderHouseByCheckinLessThanEqualAndCheckoutGreaterThanEqualAndHouseId(Date checkin, Date checkout,Long houseId);

    boolean existsOrderHouseByCheckinGreaterThanEqualAndCheckoutLessThanEqualAndHouseId(Date checkin, Date checkout,Long houseId);


    void createOrderHouse(OrderHouse house);

    void updateOrderHouse(OrderHouse house);

    void deleteOrderHouse(Long id);

    boolean existsStatusHouseByStartDateGreaterThanEqualAndStartDateLessThanEqual(Date checkin, Date checkout, Long houseId);

    boolean existsStatusHouseByEndDateGreaterThanEqualAndEndDateLessThanEqual(Date checkin, Date checkout, Long houseId);

    boolean existsStatusHouseByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date checkin, Date checkou, Long houseId);

    boolean existsStatusHouseByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date checkin, Date checkout, Long houseId);

    List<UserOrderList> userOrderLists(Long userId);

    OrderDetail findById(Long userId, Long orderId);

    List<UserOrderListOfHost> userOrderListOfHost(Long hostId);

}

