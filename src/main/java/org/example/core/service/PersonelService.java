package org.example.core.service;

import org.example.core.dao.impl.PersonelDaoImpl;
import org.example.core.entity.Personel;
import org.example.core.entity.dto.PersonelDto;

import java.util.ArrayList;
import java.util.List;

public class PersonelService {
    PersonelDaoImpl personelDao = new PersonelDaoImpl();

    public void save(Personel personel) {
        personelDao.save(personel);
    }

    public List<Personel> findAll() {
        List<Personel> personelList = new ArrayList<>();
        for (Personel personel : personelDao.findAll()) {
            personelList.add(personel);
        }
        return personelList;
    }

    public String deleteById(long id) {
        Personel personel = personelDao.findById(id);
        if (personel != null){
            personelDao.deleteById(id);
            return "Personel silindi!";
        }else {
            return "Personel bulunamadı!";
        }
    }
    public Personel findById(long id) {
        return personelDao.findById(id);
    }

    public void update(Personel personel) {
        personelDao.update(personel);
    }

    public List<Personel> findByName(String name) {
        return personelDao.findByName(name);
    }

    public List<Personel> findAllByDto(PersonelDto personelDto) {
        return personelDao.findAllByDto(personelDto);
    }
    public Personel findPersonelById(Long id){
        return personelDao.findPersonelById(id);
    }

}
