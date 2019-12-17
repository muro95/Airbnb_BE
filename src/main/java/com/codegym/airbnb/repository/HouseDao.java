package com.codegym.airbnb.repository;

import com.codegym.airbnb.message.request.CreateHouseRequest;
import com.codegym.airbnb.message.response.HouseDetail;
import com.codegym.airbnb.message.response.HouseList;
import com.codegym.airbnb.model.HouseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
        String sql = "select h.id, h.houseName, c.name catName,h.picture, h.address, h.bedroomNumber, h.bathroomNumber, h.description, h.price, h.area, u.name userName, u.id userId " +
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
        houseDetail.setId(Long.parseLong(""+result[i++]));
        houseDetail.setName(""+result[i++]);
        houseDetail.setCatName(""+result[i++]);
        houseDetail.setPicture("" + result[i++]);
        houseDetail.setAddress(""+result[i++]);
        houseDetail.setBedroomNumber(""+result[i++]);
        houseDetail.setBathroomNumber(""+result[i++]);
        houseDetail.setDescription(""+result[i++]);
        houseDetail.setPrice(""+result[i++]);
        houseDetail.setArea(""+result[i++]);
        houseDetail.setUserName(""+result[i++]);
        houseDetail.setUserId(""+result[i++]);
        return houseDetail;
    }

    public List<HouseList> getListHouse(int page, int pageSize){
        String sql = "select h.id, h.houseName,h.address, h.price, h.picture\n" +
                "from House h\n" +
                "left join users u\n" +
                "on h.host_id = u.id;";

        Query query = em.createNativeQuery(sql);
        List<Object[]> listResult = query.getResultList();


        List<HouseList> houseDetails = new ArrayList<>();
        HouseList item;
        int i;
        for (Object[] row : listResult) {
            i = 0;
            item = new HouseList();
            item.setId(Long.parseLong(""+ row[i++]));
            item.setName(("" + row[i++]));
//            item.setCatName("" + row[i++]);
            item.setAddress(("" + row[i++]));
            item.setPrice(("" + row[i++]));
            item.setPicture("" + row[i++]);
            houseDetails.add(item);
        }
        return houseDetails;
    }
}
