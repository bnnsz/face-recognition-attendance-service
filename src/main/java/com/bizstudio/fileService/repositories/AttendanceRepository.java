/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.repositories;

import com.bizstudio.fileService.entities.AttendanceEntity;
import com.bizstudio.fileService.entities.StudentEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author obinna.asuzu
 */
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    @Query("SELECT a FROM AttendanceEntity a WHERE a.exitTime IS NULL AND a.student = :student")
    public Optional<AttendanceEntity>  findAttendance(@Param("student") StudentEntity student);
}




