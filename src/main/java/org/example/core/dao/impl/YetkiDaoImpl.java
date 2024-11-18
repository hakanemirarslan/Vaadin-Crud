package org.example.core.dao.impl;

import org.example.core.dao.YetkiDao;
import org.example.core.dao.utils.HibernateUtil;
import org.example.core.entity.Yetki;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;

public class YetkiDaoImpl implements YetkiDao {
    @Override
    public void save(Yetki entity) {
        Yetki yetki;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            yetki = (Yetki) session.save(entity);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Yetki update(Yetki entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Yetki yetki = session.get(Yetki.class, entity.getId());
            if (yetki != null) {
                yetki.setYetkiAdi(entity.getYetkiAdi());
            }
            return yetki;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Yetki yetki = new Yetki();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            yetki = (Yetki) session.get(Yetki.class, id);
            session.delete(yetki);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Yetki> findAll() {
        List<Yetki> yetkiList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Yetki";
            Query query = session.createQuery(hql);
            yetkiList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return yetkiList;
    }

    @Override
    public Yetki findById(Long id) {
        Yetki yetki = new Yetki();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            yetki = session.find(Yetki.class, id);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return yetki;
    }
}
