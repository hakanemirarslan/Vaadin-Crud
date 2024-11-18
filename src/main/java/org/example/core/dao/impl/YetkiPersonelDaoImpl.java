package org.example.core.dao.impl;

import org.example.core.dao.YetkiPersonelDao;
import org.example.core.dao.utils.HibernateUtil;
import org.example.core.entity.YetkiPersonel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;

public class YetkiPersonelDaoImpl implements YetkiPersonelDao {
    @Override
    public void save(YetkiPersonel entity) {
        YetkiPersonel yetkiPersonel;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            yetkiPersonel = (YetkiPersonel) session.save(entity);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public YetkiPersonel update(YetkiPersonel entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            YetkiPersonel yetkiPersonel = session.get(YetkiPersonel.class,entity.getId());
            if (yetkiPersonel != null){
                yetkiPersonel.setYetki(entity.getYetki());
                yetkiPersonel.setPersonel(entity.getPersonel());
                yetkiPersonel.setYetki(entity.getYetki());
            }
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        YetkiPersonel yetkiPersonel = new YetkiPersonel();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            yetkiPersonel = (YetkiPersonel) session.get(YetkiPersonel.class, id);
            session.delete(yetkiPersonel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<YetkiPersonel> findAll() {
        List<YetkiPersonel> yetkiPersonelList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Yetki";
            Query query = session.createQuery(hql);
            yetkiPersonelList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return yetkiPersonelList;
    }

    @Override
    public YetkiPersonel findById(Long id) {
        YetkiPersonel yetkiPersonel = new YetkiPersonel();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            yetkiPersonel = session.find(YetkiPersonel.class, id);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return yetkiPersonel;
    }

}
