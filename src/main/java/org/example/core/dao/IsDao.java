package org.example.core.dao;

import org.example.core.entity.Is;
import org.example.core.entity.dto.IsDto;

import java.util.List;

public interface IsDao extends GenericDao<Is> {


    List<Is> findAllByDto(IsDto dto);
}
