package com.fptpoly.main.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @Column(name = "Idcar")
    private String idcar;
    @Basic
    @Column(name = "Tencar")
    private String tencar;
    @Basic
    @Column(name = "Gia")
    private Double gia;
    @Basic
    @Column(name = "Loaixe",insertable = false,updatable = false)
    private String loaixe;
    @Basic
    @Column(name = "Trangthai")
    private String trangthai;
    @Basic
    @Column(name = "Namsx")
    private Integer namsx;
    @Basic
    @Column(name = "Hang",insertable = false,updatable = false)
    private String hang;
    @Basic
    @Column(name = "Sokm")
    private Integer sokm;
    @Basic
    @Column(name = "Dongco")
    private String dongco;
    @Basic  
    @Column(name = "Hopso")
    private String hopso;
    @Basic
    @Column(name = "Nhienlieu")
    private String nhienlieu;
    @Basic
    @Column(name = "Phanh")
    private String phanh;
    @Basic
    @Column(name = "Ghichu")
    private String ghichu;

    @JsonIgnore
    @OneToMany(mappedBy = "carByMacar")
    private List<Accessories> accessoriesByIdcar;

    @JsonIgnore
    @OneToMany(mappedBy = "carByMaxe")
    private List<Appointment> appointmentsByIdcar;

    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private List<Billcar> billcarsByIdcar;

    @ManyToOne
    @JoinColumn(name = "Loaixe", referencedColumnName = "Maloai")
    private Typecar typecarByLoaixe;

    @ManyToOne
    @JoinColumn(name = "Hang", referencedColumnName = "Ma")
    private Brand brandByHang;

    @JsonIgnore
    @OneToMany(mappedBy = "carByMaxe")
    private List<Cartcar> cartcarsByIdcar;

    @JsonIgnore
    @OneToMany(mappedBy = "carByMacar")
    private List<Imagescar> imagescarsByIdcar;

    @JsonIgnore
    @OneToMany(mappedBy = "carByMaxe")
    private List<Votecar> votecarsByIdcar;

}
