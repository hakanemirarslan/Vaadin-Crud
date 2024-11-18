package org.example.core.dao.impl;


import com.vaadin.ui.Notification;
import org.example.core.dao.PersonelDao;
import org.example.core.dao.utils.HibernateUtil;

import org.example.core.entity.Is;
import org.example.core.entity.Personel;

import org.example.core.entity.dto.PersonelDto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PersonelDaoImpl implements PersonelDao {

    @Override
    public void save(Personel entity) {
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
    public Personel update(Personel entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Personel personel = session.get(Personel.class, entity.getId());
            if (personel != null) {
                personel.setName(entity.getName());
                personel.setKullaniciAdi(entity.getKullaniciAdi());
                personel.setPassword(entity.getPassword());
                transaction.commit();
                session.update(personel);
            }
            return personel;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteById(Long id) {
        Personel personel = new Personel();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            personel = (Personel) session.get(Personel.class, id);
            if (personel != null){
                session.delete(personel);
                transaction.commit();
                session.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Personel> findAll() {
        List<Personel> personelList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Personel";
            Query<Personel> query = session.createQuery(hql,Personel.class);
            personelList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return personelList;
    }

    @Override
    public Personel findById(Long id) {
        Personel personel = new Personel();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            personel = session.find(Personel.class, id);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return personel;
    }

    @Override
    public void delete(Personel personel) {
        Personel deletedPersonel = new Personel();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            deletedPersonel = session.get(Personel.class,personel.getId());
            if(deletedPersonel != null){
                session.delete(deletedPersonel);
                transaction.commit();
            }
            session.close();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }


    @Override
    public List<Personel> findByName(String name) {
        List<Personel> personelList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Personel WHERE name = :data";
            Query<Personel> query = session.createQuery(hql,Personel.class);
            query.setParameter("data", name);
            personelList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return personelList;
    }

    @Override
    public List<Personel> findAllByDto(PersonelDto dto) {
        List<Personel> personelList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Personel p WHERE 1=1 ";

            if (StringUtils.hasText(dto.getName())) {
                hql += "AND p.name LIKE :name ";
            }
            if (StringUtils.hasText(dto.getKullaniciAdi())) {
                hql += "AND p.kullaniciAdi LIKE :kullaniciAdi ";
            }

            Query<Personel> query = session.createQuery(hql,Personel.class);

            if (StringUtils.hasText(dto.getName())) {
                query.setParameter("name", "%" + dto.getName() + "%");
            }
            if (StringUtils.hasText(dto.getKullaniciAdi())) {
                query.setParameter("kullaniciAdi", "%" + dto.getKullaniciAdi() + "%");
            }
            personelList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return personelList;
    }


    public Personel findPersonelById(Long id){
        Personel personel = new Personel();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = " select p FROM Personel p LEFT JOIN Is i ON p.id = i.cozenKisi.id where p.id = :id";
            Query query = session.createQuery(hql, Personel.class);
            query.setParameter("id",id);
            personel= (Personel) query.uniqueResult();

            session.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return personel;
    }

}

