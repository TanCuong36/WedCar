package com.fptpoly.main.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cartcar {
    @Basic
    @Column(name = "Maxe",insertable = false,updatable = false)
    private String maxe;
    @Basic
    @Column(name = "Matv",insertable = false,updatable = false)
    private String matv;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Stt")
    private int stt;
    @ManyToOne
    @JoinColumn(name = "Maxe", referencedColumnName = "Idcar", nullable = false)
    private Car carByMaxe;
    @ManyToOne
    @JoinColumn(name = "Matv", referencedColumnName = "Matv", nullable = false)
    private Account accountByMatv;

    
}
