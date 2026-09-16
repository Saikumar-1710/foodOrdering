package com.restaurants.FastFoodShop.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.restaurants.FastFoodShop.Entity.Attendance;

public interface AttendanceService {

    Attendance saveAttendance(Attendance attendance);

    Attendance updateAttendance(Attendance attendance);

    void deleteAttendance(Integer id);

    Optional<Attendance> getAttendanceById(Integer id);

    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceByStaff(Integer staffId);

    Optional<Attendance> getAttendanceByStaffAndDate(Integer staffId, LocalDate date);
}