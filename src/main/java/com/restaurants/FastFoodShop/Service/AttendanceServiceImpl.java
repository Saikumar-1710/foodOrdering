package com.restaurants.FastFoodShop.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurants.FastFoodShop.Entity.Attendance;
import com.restaurants.FastFoodShop.Repository.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance updateAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(Integer id) {
        attendanceRepository.deleteById(id);
    }

    @Override
    public Optional<Attendance> getAttendanceById(Integer id) {
        return attendanceRepository.findById(id);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getAttendanceByStaff(Integer staffId) {
        return attendanceRepository.findByStaffId(staffId);
    }

    @Override
    public Optional<Attendance> getAttendanceByStaffAndDate(
            Integer staffId, LocalDate date) {
        return attendanceRepository.findByStaffIdAndDate(staffId, date);
    }
}