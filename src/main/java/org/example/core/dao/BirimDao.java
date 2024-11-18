package org.example.core.dao;

import org.example.core.entity.Birim;
import org.example.core.entity.dto.BirimDto;

import java.util.List;


public interface BirimDao extends GenericDao<Birim>{
    List<Birim> findByBaslik(String baslik);

    List<Birim> findAllByDto(BirimDto dto);
}
