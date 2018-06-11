package com.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipDataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getShipName(){
        return jdbcTemplate.queryForList("select distinct ship_name from ship_data;",String.class);
    }

}
