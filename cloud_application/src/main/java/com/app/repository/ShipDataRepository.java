package com.app.repository;


import com.app.entity.ShipData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipDataRepository extends JpaRepository<ShipData,String>,PagingAndSortingRepository<ShipData,String> {

    List<ShipData> findByShipName(String shipName);

}
