package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Billaccessoriesdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillaccessoriesdetailRepository extends JpaRepository<Billaccessoriesdetail, Integer> {


    void deleteAllByBillaccessoriesByMahd_Mahd(String id);

    /*@Query(value = "DELETE FROM Billaccessoriesdetail WHERE Mahd=?1",nativeQuery = true)*/
    void deleteBillaccessoriesdetailsByBillaccessoriesByMahd_Mahd(String id);

    List<Billaccessoriesdetail> findAllByBillaccessoriesByMahd(String id);
}