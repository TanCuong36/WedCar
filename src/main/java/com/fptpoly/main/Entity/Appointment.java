package com.fptpoly.main.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Stt")
    private int stt;
    @Basic
    @Column(name = "Matv",insertable = false,updatable = false)
    private String matv;
    @Basic
    @Column(name = "Manv",insertable = false,updatable = false)
    private String manv;
    @Basic
    @Column(name = "Maxe",insertable = false,updatable = false)
    private String maxe;
    @Basic
    @Column(name = "Ngayhen")
    private Date ngayhen;
    @Basic
    @Column(name = "Khunggio")
    private String khunggio;
    @Basic
    @Column(name = "Loai")
    private String loai;
    @Basic
    @Column(name = "Ghichu")
    private String ghichu;
    @ManyToOne
    @JoinColumn(name = "Matv", referencedColumnName = "Matv")
    private Account accountByMatv;
    @ManyToOne
    @JoinColumn(name = "Maxe", referencedColumnName = "Idcar")
    private Car carByMaxe;

    
}
