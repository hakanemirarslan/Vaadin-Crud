package org.example.core.dao;

import org.example.core.entity.Personel;
import org.example.core.entity.dto.PersonelDto;

import java.util.List;

public interface PersonelDao extends GenericDao<Personel> {

    List<Personel> findByName(String name);

    List<Personel> findAllByDto(PersonelDto dto);

    void delete(Personel personel);

}
