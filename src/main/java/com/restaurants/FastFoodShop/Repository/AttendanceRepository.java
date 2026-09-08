package com.restaurants.FastFoodShop.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.FastFoodShop.Entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByStaffId(Integer staffId);

    Optional<Attendance> findByStaffIdAndDate(Integer staffId, LocalDate date);
}