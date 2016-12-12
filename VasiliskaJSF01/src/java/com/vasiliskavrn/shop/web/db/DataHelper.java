package com.vasiliskavrn.shop.web.db;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.vasiliskavrn.shop.web.entity.Brand;
import com.vasiliskavrn.shop.web.entity.Goods;
import com.vasiliskavrn.shop.web.entity.Cloth;
import com.vasiliskavrn.shop.web.entity.HibernateUtil;

public class DataHelper {

    private SessionFactory sessionFactory = null;
    private static DataHelper dataHelper;

    private DataHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public static DataHelper getInstance() {
        return dataHelper == null ? new DataHelper() : dataHelper;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
   
    public List<Goods> getAllGoods() {
        return getSession().createCriteria(Goods.class).list();
    }

    public List<Cloth> getAllCloths() {
        return getSession().createCriteria(Cloth.class).list();
    }

    public List<Brand> getAllBrands() {
        return getSession().createCriteria(Brand.class).list();
    }

    public List<Goods> getGoodsByCloth(Long idCloth) {
        return getSession().createCriteria(Goods.class).add(Restrictions.eq("cloth.id", idCloth)).list();
    }

    public List<Goods> getGoodsByLetter(Character letter) {
        return getGoodsList("name", letter.toString(), MatchMode.START);
    }

    public List<Goods> getGoodsByBrand(String authorName) {
        return getGoodsList("brand", authorName, MatchMode.ANYWHERE);
    }

    public List<Goods> getGoodsByName(String bookName) {
        return getGoodsList("name", bookName, MatchMode.ANYWHERE);
    }

//    public byte[] getContent(Long id) {
//        return (byte[]) getFieldValue("content", id);
//    }

    public byte[] getImage(Long id) {
        return (byte[]) getFieldValue("image", id);
    }

    public Brand getBrand(long id) {
        return (Brand) getSession().get(Brand.class, id);
    }

    private List<Goods> getGoodsList(String field, String value, MatchMode matchMode) {
        return getSession().createCriteria(Goods.class).add(Restrictions.ilike(field, value, matchMode)).list();
    }

    private Object getFieldValue(String field, Long id) {
        return getSession().createCriteria(Goods.class).setProjection(Projections.property(field)).add(Restrictions.eq("id", id)).uniqueResult();
    }
}