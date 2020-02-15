/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.services;

import com.bizstudio.fileService.dtos.Attendance;
import com.bizstudio.fileService.entities.AttendanceEntity;
import com.bizstudio.fileService.entities.StudentEntity;
import com.bizstudio.fileService.enums.ErrorStatus;
import com.bizstudio.fileService.exceptions.ServiceException;
import com.bizstudio.fileService.repositories.AttendanceRepository;
import com.bizstudio.fileService.repositories.StudentRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author obinna.asuzu
 */
@Service
public class AttendanceService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AttendanceRepository attendanceRepository;

    public Attendance createAttendance(String personId) throws ServiceException {
        StudentEntity student = studentRepository.findByPersonId(personId)
                .orElseThrow(() -> new ServiceException(ErrorStatus.user_not_exist));
        Optional<AttendanceEntity> lastAttendance = attendanceRepository.findAttendance(student);
        AttendanceEntity attendance;
        if (lastAttendance.isPresent()) {
            attendance = lastAttendance.get();
            attendance.setExitTime(new Date());
            attendanceRepository.save(attendance);
        } else {
            attendance = new AttendanceEntity();
            attendance.setEntryTime(new Date());
            attendance.setStudent(student);
            attendanceRepository.save(attendance);
        }
        return attendance.toDto();
    }

    public List<Attendance> fetchAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(att -> att.toDto())
                .collect(toList());
    }
}

