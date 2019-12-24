package com.codegym.airbnb.service.impl;

import com.codegym.airbnb.message.response.OrderDetail;
import com.codegym.airbnb.message.response.UserOrderList;
import com.codegym.airbnb.message.response.UserOrderListOfHost;
import com.codegym.airbnb.model.OrderHouse;
import com.codegym.airbnb.model.StatusHouse;
import com.codegym.airbnb.repository.OrderDao;
import com.codegym.airbnb.repository.OrderHouseRepository;
import com.codegym.airbnb.repository.StatusHouseRepository;
import com.codegym.airbnb.service.OrderHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderHouseServiceImpl implements OrderHouseService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderHouseRepository orderHouseRepository;

    @Autowired
    private StatusHouseRepository statusHouseRepository;

    @Override
    public List<OrderHouse> findAll() {
        return orderHouseRepository.findAll();
    }

    @Override
    public List<OrderHouse> findOrderHousesByTenantId(long id) {
        return orderHouseRepository.findOrderHousesByTenantId(id);
    }

    @Override
    public List<OrderHouse> findOrderHousesByHouseId(long id) {
        return orderHouseRepository.findOrderHousesByHouseId(id);
    }

    @Override
    public List<Long> getOrderIdsByHouseId(long id) {
        List<Long> listOrderId=new ArrayList<>();
        List<OrderHouse> orderHouses=orderHouseRepository.findOrderHousesByHouseId(id);
        for (OrderHouse orderHouse:orderHouses){
            listOrderId.add(orderHouse.getId());
        }
        return listOrderId;
    }

    @Override
    public OrderHouse findById(Long id) {
        return orderHouseRepository.findById(id).get();
    }

    @Override
    public boolean existsOrderHouseByCheckinGreaterThanEqualAndCheckinLessThanEqualAndHouseId(Date checkin, Date checkout, Long houseId) {
        return orderHouseRepository.existsOrderHouseByCheckinGreaterThanEqualAndCheckinLessThanEqualAndHouseId(checkin, checkout, houseId);
    }

    @Override
    public boolean existsOrderHouseByCheckoutGreaterThanEqualAndCheckoutLessThanEqualAndHouseId(Date checkin, Date checkout, Long houseId) {
        return orderHouseRepository.existsOrderHouseByCheckoutGreaterThanEqualAndCheckoutLessThanEqualAndHouseId(checkin, checkout, houseId);
    }

    @Override
    public boolean existsOrderHouseByCheckinLessThanEqualAndCheckoutGreaterThanEqualAndHouseId(Date checkin, Date checkout, Long houseId) {
        return orderHouseRepository.existsOrderHouseByCheckinLessThanEqualAndCheckoutGreaterThanEqualAndHouseId(checkin, checkout, houseId);
    }

    @Override
    public boolean existsOrderHouseByCheckinGreaterThanEqualAndCheckoutLessThanEqualAndHouseId(Date checkin, Date checkout, Long houseId) {
        return orderHouseRepository.existsOrderHouseByCheckinGreaterThanEqualAndCheckoutLessThanEqualAndHouseId(checkin, checkout, houseId);
    }

    @Override
    public void createOrderHouse(OrderHouse orderHouse) {
        orderHouseRepository.save(orderHouse);
    }

    @Override
    public void updateOrderHouse(OrderHouse orderHouse) {
        orderHouseRepository.save(orderHouse);

    }

    @Override
    public void deleteOrderHouse(Long id) {
        orderHouseRepository.deleteById(id);
    }

    @Override
    public boolean existsStatusHouseByStartDateGreaterThanEqualAndStartDateLessThanEqual(Date checkin, Date checkout, Long houseId) {
        List<StatusHouse> statusHouses = this.statusHouseRepository.findAllByHouseId(houseId);
        for (Integer i = 0; i < statusHouses.size(); i++) {
            boolean startIn = statusHouses.get(i).getStartDate().after(checkin);
            boolean startOut = statusHouses.get(i).getStartDate().before(checkout);
            if (startIn && startOut) {
                return true;
            }
            ;
        }
        return false;
    }

    @Override
    public boolean existsStatusHouseByEndDateGreaterThanEqualAndEndDateLessThanEqual(Date checkin, Date checkout, Long houseId) {
        List<StatusHouse> statusHouses = this.statusHouseRepository.findAllByHouseId(houseId);
        for (Integer i = 0; i < statusHouses.size(); i++) {
            boolean endIn = statusHouses.get(i).getEndDate().after(checkin);
            boolean endOut = statusHouses.get(i).getEndDate().before(checkout);
            if (endIn && endOut) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsStatusHouseByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date checkin, Date checkout, Long houseId) {
        List<StatusHouse> statusHouses = this.statusHouseRepository.findAllByHouseId(houseId);
        for (Integer i = 0; i < statusHouses.size(); i++) {
            boolean startin = statusHouses.get(i).getStartDate().before(checkin);
            boolean endOut = statusHouses.get(i).getEndDate().after(checkout);
            if (startin && endOut) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsStatusHouseByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date checkin, Date checkout, Long houseId) {
        List<StatusHouse> statusHouses = this.statusHouseRepository.findAllByHouseId(houseId);
        for (Integer i = 0; i < statusHouses.size(); i++) {
            boolean startIn = statusHouses.get(i).getStartDate().after(checkin);
            boolean endOut = statusHouses.get(i).getEndDate().before(checkout);
            if (startIn && endOut) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserOrderList> userOrderLists(Long userId) {
        return orderDao.userOrderLists(userId);
    }

    @Override
    public OrderDetail findById(Long userId, Long orderId) {
        return orderDao.orderDetail(userId,orderId);
    }

    @Override
    public List<UserOrderListOfHost> userOrderListOfHost(Long hostId) {
        return orderDao.userOrderListOfHost(hostId);
    }
}