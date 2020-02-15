/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.controllers;

import com.bizstudio.fileService.dtos.Attendance;
import com.bizstudio.fileService.dtos.Student;
import com.bizstudio.fileService.dtos.UploadFileResponse;
import com.bizstudio.fileService.exceptions.ServiceException;
import com.bizstudio.fileService.services.AttendanceService;
import com.bizstudio.fileService.services.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author obinna.asuzu
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/attendance")
@Api(value = "Attendance API", description = "Attendance Rest API", tags = {"Attendance API"})
public class AttendanceController {
    @Autowired
    StudentService studentService;
    @Autowired
    AttendanceService attendanceService;
    
    @ApiOperation(value = "New Attendance entry")
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Attendance.class, message = "")
    })
    @PostMapping("/{personId}/create")
    public ResponseEntity<Attendance> create(
            @PathVariable String personId) throws ServiceException {
        Attendance attendance = attendanceService.createAttendance(personId);
        return ResponseEntity.ok(attendance);
    }
    
    @ApiOperation(value = "Register new student")
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Student.class, message = "")
    })
    @PostMapping("/student/register")
    public ResponseEntity<Student> create(
            @RequestBody Student student) throws ServiceException {
        Student stud = studentService.createStudent(student);
        return ResponseEntity.ok(stud);
    }

    @ApiOperation(value = "Fetch all attendance")
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Attendance.class, responseContainer = "List", message = "")
    })
    @GetMapping("/all")
    public List<Attendance> fetchAttendances() {
        return attendanceService.fetchAllAttendance();
    }
}

















