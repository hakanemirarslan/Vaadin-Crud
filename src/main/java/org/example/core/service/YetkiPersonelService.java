package org.example.core.service;

import org.example.core.dao.impl.YetkiPersonelDaoImpl;
import org.example.core.entity.YetkiPersonel;

import java.util.List;

public class YetkiPersonelService {
    YetkiPersonelDaoImpl yetkiPersonelDao = new YetkiPersonelDaoImpl();
       public void save(YetkiPersonel yetkiPersonel){
           yetkiPersonelDao.save(yetkiPersonel);
       }

       public List<YetkiPersonel> findAll(){
           return yetkiPersonelDao.findAll();
       }

       public YetkiPersonel findById(long id){
           return yetkiPersonelDao.findById(id);
       }

       public void deleteById(long id){
           yetkiPersonelDao.deleteById(id);
       }

       public void update(YetkiPersonel yetkiPersonel){
           yetkiPersonelDao.update(yetkiPersonel);
       }



}
