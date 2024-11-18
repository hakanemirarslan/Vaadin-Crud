package org.example.core.service;

import org.example.core.dao.impl.BirimDaoImpl;
import org.example.core.entity.Birim;
import org.example.core.entity.dto.BirimDto;

import java.util.List;


public class BirimService {
    BirimDaoImpl birimDao = new BirimDaoImpl();
    public void save(Birim birim){
         birimDao.save(birim);
    }

    public List<Birim> findAll(){
        return birimDao.findAll();
    }

    public Birim findById(long id){
        return birimDao.findById(id);
    }

    public String deleteById(long id){
        birimDao.deleteById(id);
        return "Birim silindi";
    }

    public void update(Birim birim){
        birimDao.update(birim);
    }

    public List<Birim> findByBaslik(String baslik){ return birimDao.findByBaslik(baslik);}

    public List<Birim> findAllByDto(BirimDto birimDto){return  birimDao.findAllByDto(birimDto);}

    public Birim findByBirimId(Long id){
        return birimDao.findByBirimId(id);
    }

}
