package com.codegym.airbnb.repository;

import com.codegym.airbnb.message.response.HouseDetail;
import com.codegym.airbnb.message.response.HouseListOfHost;
import com.codegym.airbnb.message.response.OrderDetail;
import com.codegym.airbnb.message.response.UserOrderList;
import com.codegym.airbnb.model.HouseEntity;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class OrderDao {
    @PersistenceContext
    private EntityManager em;

    public void insert(HouseEntity house) {
        em.persist(house);
    }

    String pattern = "MM/dd/yyyy HH:mm:ss";

    // Create an instance of SimpleDateFormat used for formatting
// the string representation of date according to the chosen pattern
    DateFormat df = new SimpleDateFormat(pattern);

    public List<UserOrderList> userOrderLists(Long userId) {
        String sql = "select oh.id, oh.checkin, oh.checkout, oh.numberGuest, oh.cost, oh.orderTime, oh.house_id, h.houseName\n" +
                "from orderhouse oh \n" +
                "join users u\n" +
                "join house h\n" +
                "join user_roles r\n" +
                "on oh.house_id = h.id and u.id = oh.tenant and u.id = r.user_id\n" +
                "where r.role_id = 2 and u.id = :uid";

        Query query = em.createNativeQuery(sql);
        query.setParameter("uid", userId);

        List<Object[]> listResult = query.getResultList();


        List<UserOrderList> userOrderLists = new ArrayList<>();
        UserOrderList item;
        int i;
        for (Object[] row : listResult) {
            i = 0;
            item = new UserOrderList();
            item.setId(Long.parseLong("" + row[i++]));
            item.setCheckin(" " + row[i++]);
            item.setCheckout(" " + row[i++]);
//            item.setCatName("" + row[i++]);
            item.setNumberGuest(Long.parseLong(("" + row[i++])));
            item.setCost(Long.parseLong("" + row[i++]));
            item.setOrderTime(" " + row[i++]);
            item.setHouse_id(Long.parseLong("" + row[i++]));
            item.setHouseName(" " + row[i++]);
            userOrderLists.add(item);
        }
        return userOrderLists;
    }

    //get detail order

    public OrderDetail orderDetail(Long userId, Long orderId) {
        String sql = "select oh.id, oh.checkin, oh.checkout, oh.numberGuest, oh.cost, oh.orderTime, oh.house_id, h.houseName\n" +
                "from orderhouse oh \n" +
                "join users u\n" +
                "join house h\n" +
                "join user_roles r\n" +
                "on oh.house_id = h.id and u.id = oh.tenant and u.id = r.user_id\n" +
                "where r.role_id = 2 and u.id = :uid and oh.id = :ohid";

        Query query = em.createNativeQuery(sql);
        query.setParameter("uid", userId);
        query.setParameter("ohid", orderId);

        Object[] result = (Object[]) query.getSingleResult();

        List<UserOrderList> userOrderLists = new ArrayList<>();
        UserOrderList item;
        int i = 0;

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(Long.parseLong("" + result[i++]));
        orderDetail.setCheckin("" + result[i++]);
        orderDetail.setCheckout("" + result[i++]);
        orderDetail.setNumberGuest(Long.parseLong("" + result[i++]));
        orderDetail.setCost(Long.parseLong("" + result[i++]));
        orderDetail.setOrderTime("" + result[i++]);
        orderDetail.setHouse_id(Long.parseLong("" + result[i++]));
        orderDetail.setHouseName("" + result[i++]);

        return orderDetail;
    }




}
