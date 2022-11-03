package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Billaccessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillaccessoriesRepository extends JpaRepository<Billaccessories, String> {
    Billaccessories findAllByMahd(String mahd);

    List<Billaccessories> findAllByTrangthaiAndAccountByMatv_Matv(String status,String matv);
}