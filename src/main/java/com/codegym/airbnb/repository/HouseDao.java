package com.codegym.airbnb.repository;

import com.codegym.airbnb.message.request.CreateHouseRequest;
import com.codegym.airbnb.message.response.HouseDetail;
import com.codegym.airbnb.model.HouseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class HouseDao {
    @PersistenceContext
    private EntityManager em;

    public void insert(HouseEntity house) {
        em.persist(house);
    }

    public HouseDetail getHouseDetailById(Long houseId){
//        private String name;
//        private String catName;
//        private String userName;
//        private String userId;
        String sql = "select h.houseName, c.name catName, u.name userName, u.id userId " +
                " from House h" +
                " LEFT JOIN users u" +
                " ON h.host_id = u.id " +
                " LEFT JOIN Category c " +
                " ON h.category = c.id" +
                " where h.id = :hid " ;

        Query query = em.createNativeQuery(sql);
        query.setParameter("hid", houseId);
        Object [] result = (Object[]) query.getSingleResult();

        int i = 0;
        HouseDetail houseDetail = new HouseDetail();
        houseDetail.setName(""+result[i++]);
        houseDetail.setCatName(""+result[i++]);
        houseDetail.setUserName(""+result[i++]);
        houseDetail.setUserId(""+result[i++]);
        return houseDetail;
    }

//    public List<HouseDetail> getListHouse(int page, int pageSize){
//        String sql = "select "
//    }
}
