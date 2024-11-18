package org.example.core.dao.impl;

import org.example.core.dao.IsDao;
import org.example.core.dao.utils.HibernateUtil;
import org.example.core.entity.Is;
import org.example.core.entity.dto.IsDto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;

public class IsDaoImpl implements IsDao {

    @Override
    public void save(Is entity) {
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
    public Is update(Is entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Is isler = session.get(Is.class, entity.getId());
            if (isler != null) {
                isler.setYazar(entity.getYazar());
                isler.setTamamlananSure(entity.getTamamlananSure());
                isler.setTarih(entity.getTarih());
                isler.setOnayDurumu(entity.getOnayDurumu());
                isler.setBaslik(entity.getBaslik());
                isler.setCozenKisi(entity.getCozenKisi());
                isler.setAciklama(entity.getAciklama());
                isler.setBirim(entity.getBirim());
                session.update(isler);
                transaction.commit();
            }
            session.close();
            return isler;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Is is = new Is();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            is = (Is) session.get(Is.class, id);
            if (is != null) {
                is.setYazar(null);
                is.setCozenKisi(null);
                session.update(is);
            }
            session.delete(is);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Is> findAll() {
        List<Is> isList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Is";
            Query<Is> query = session.createQuery(hql, Is.class);
            isList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isList;
    }

    //    @Override
//    public Is findById(Long id) {
//        Is is = new Is();
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            is = session.find(Is.class, id);
//            transaction.commit();
//            session.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return is;
//    }
    @Override
    public Is findById(Long id) {
        Is is = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "SELECT i FROM Is i LEFT JOIN Personel p ON p.id= i.cozenKisi.id WHERE i.id = :id";
            Query<Is> query = session.createQuery(hql, Is.class);
            query.setParameter("id", id);

            is = query.uniqueResult();

            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return is;
    }

//    public Is findByBirimId(Long id) {
//        Is is = null;
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            String hql = "SELECT i FROM Is i LEFT JOIN Birim p ON p.id= i.birim.id WHERE i.id = :id";
//            Query<Is> query = session.createQuery(hql, Is.class);
//            query.setParameter("id", id);
//
//            is = query.uniqueResult();
//
//            transaction.commit();
//            session.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return is;
//    }
    @Override
    public List<Is> findAllByDto(IsDto dto) {
        List<Is> islerList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Is i WHERE 1=1 ";

            if (StringUtils.hasText(dto.getAciklama())) {
                hql += "AND i.aciklama LIKE :aciklama ";
            }
            if (StringUtils.hasText(dto.getBaslik())) {
                hql += "AND i.baslik LIKE :baslik ";
            }
            if (dto.getOnayDurumu() != null) {
                hql += "AND i.onayDurumu = :onayDurumu ";
            }
            if (StringUtils.hasText(dto.getTamamlananSure())) {
                hql += "AND i.tamamlananSure = :tamamlananSure ";
            }
            if (dto.getTarih() != null) {
                hql += "AND i.tarih = :tarih ";
            }

            Query<Is> query = session.createQuery(hql, Is.class);

            if (StringUtils.hasText(dto.getAciklama())) {
                query.setParameter("aciklama", "%" + dto.getAciklama() + "%");
            }
            if (StringUtils.hasText(dto.getBaslik())) {
                query.setParameter("baslik", "%" + dto.getBaslik() + "%");
            }
            if (dto.getOnayDurumu() != null) {
                query.setParameter("onayDurumu", "%" + dto.getOnayDurumu() + "%");
            }
            if (StringUtils.hasText(dto.getTamamlananSure())) {
                query.setParameter("tamamlananSure", "%" + dto.getTamamlananSure() + "%");
            }
            if (dto.getTarih() != null) {
                query.setParameter("tarih", "%" + dto.getTarih() + "%");
            }
            islerList = query.list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return islerList;
    }

}
