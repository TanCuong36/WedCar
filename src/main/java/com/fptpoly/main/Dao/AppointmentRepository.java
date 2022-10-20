package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}