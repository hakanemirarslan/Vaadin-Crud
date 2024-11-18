package org.example.core.service;

import org.example.core.dao.impl.IsDaoImpl;
import org.example.core.entity.Is;
import org.example.core.entity.Personel;
import org.example.core.entity.dto.IsDto;

import java.util.List;

public class IsService {
    private final IsDaoImpl isDao = new IsDaoImpl();

    public void save(Is is) {
        isDao.save(is);
    }

    public List<Is> findAll() {
        return isDao.findAll();
    }

    public String deleteById(long id) {
        Is is = findById(id);
        if (is == null) {
            return "Is bulunamadı!";
        } else {


            isDao.deleteById(id);
            return "Is silindi!";
        }
    }

    public Is findById(long id) {
        return isDao.findById(id);
    }

    public void update(Is is) {
        isDao.update(is);
    }

    public List<Is> findAllByDto(IsDto isDto) {
        return isDao.findAllByDto(isDto);
    }

}
