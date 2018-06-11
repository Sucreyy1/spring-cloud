package com.app.repository;

import com.app.entity.ShipDataNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipDataNewRepository extends JpaRepository<ShipDataNew,String>,PagingAndSortingRepository<ShipDataNew,String> {

}
