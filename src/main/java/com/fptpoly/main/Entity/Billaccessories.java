package com.fptpoly.main.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billaccessories {


    @Id
    @Column(name = "Mahd")
    private String mahd;
    @Basic
    @Column(name = "Matv",insertable = false,updatable = false)
    private String matv;
    @Basic
    @Column(name = "Ngaymua")
    private Date ngaymua;
    @Basic
    @Column(name = "Ngaynhan")
    private Date ngaynhan;
    @Basic
    @Column(name = "Tongtien")
    private Double tongtien;
    @Basic
    @Column(name = "Trangthai")
    private String trangthai;
    @Basic
    @Column(name = "Ghichu")
    private String ghichu;
    @ManyToOne
    @JoinColumn(name = "Matv", referencedColumnName = "Matv")
    private Account accountByMatv;

    @JsonIgnore
    @OneToMany(mappedBy = "billaccessoriesByMahd")
    private List<Billaccessoriesdetail> billaccessoriesdetailsByMahd;

    
}
