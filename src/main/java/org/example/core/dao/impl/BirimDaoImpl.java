package org.example.core.dao.impl;

import org.example.core.dao.BirimDao;
import org.example.core.dao.utils.HibernateUtil;
import org.example.core.entity.Birim;
import org.example.core.entity.dto.BirimDto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BirimDaoImpl implements BirimDao {
    @Override
    public void save(Birim entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Birim update(Birim entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Birim birim = session.get(Birim.class, entity.getId());
            if (birim != null) {
                birim.setName(entity.getName());
                session.update(birim);
                transaction.commit();
            }
            return birim;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Birim birim = new Birim();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            birim = (Birim) session.get(Birim.class, id);
            session.delete(birim);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Birim> findAll() {
        List<Birim> birimList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Birim";
            Query query = session.createQuery(hql);
            birimList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return birimList;
    }

    @Override
    public Birim findById(Long id) {
        Birim birim = new Birim();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            birim = session.find(Birim.class, id);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return birim;
    }

    public Birim findByBirimId(Long id) {
        Birim birim = new Birim();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = " select p FROM Birim p LEFT JOIN Is i ON p.id = i.birim.id where p.id = :id";
            Query query = session.createQuery(hql, Birim.class);
            query.setParameter("id", id);
//            birim = session.find(Birim.class, id);
            birim = (Birim) query.uniqueResult();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return birim;
    }

    @Override
    public List<Birim> findByBaslik(String baslik) {
        List<Birim> birimList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Birim WHERE name = :data";
            Query query = session.createQuery(hql);
            query.setParameter("data", baslik);
            birimList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return birimList;
    }

    @Override
    public List<Birim> findAllByDto(BirimDto dto) {
        List<Birim> birimList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Birim b WHERE 1 = 1 ";
            if (StringUtils.hasText(dto.getName())) {
                hql += " AND b.name LIKE :name ";
            }

            Query query = session.createQuery(hql);

            if (StringUtils.hasText(dto.getName())) {
                query.setParameter("name", "%" + dto.getName() + "%");
            }
            birimList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return birimList;
    }
}
