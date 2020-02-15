/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.repositories;

import com.bizstudio.fileService.entities.StudentEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author obinna.asuzu
 */
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    
    public Optional<StudentEntity> findByPersonId(String personId);
    
    @Query("select s from StudentEntity s where s.regNo = :regNo or s.personId = :personId")
    public Optional<StudentEntity> findByPersonIdOrRegNo(
            @Param("regNo") String regNo,
            @Param("personId") String personId);
}









