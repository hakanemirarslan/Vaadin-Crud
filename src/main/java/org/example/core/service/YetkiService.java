package org.example.core.service;

import org.example.core.dao.impl.YetkiDaoImpl;
import org.example.core.entity.Yetki;


import java.util.List;

public class YetkiService {
    YetkiDaoImpl yetkiDao = new YetkiDaoImpl();

    public void save(Yetki yetki){
         yetkiDao.save(yetki);
    }

    public List<Yetki> findAll(){
        return yetkiDao.findAll();
    }

    public Yetki findById(long id){
        return yetkiDao.findById(id);
    }

    public String deleteById(long id){
        Yetki yetki = findById(id);
        if (yetki == null){
            return "Yetki bulunamadı!";
        }else {
            yetkiDao.deleteById(id);
            return "Yetki silindi!";
        }
    }

    public void update(Yetki yetki){
        yetkiDao.update(yetki);
    }

}
