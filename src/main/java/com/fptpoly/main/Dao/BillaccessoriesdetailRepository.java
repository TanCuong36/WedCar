package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Billaccessoriesdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillaccessoriesdetailRepository extends JpaRepository<Billaccessoriesdetail, Integer> {
}