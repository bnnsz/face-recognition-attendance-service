/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.services;

import com.bizstudio.fileService.dtos.Student;
import com.bizstudio.fileService.entities.StudentEntity;
import com.bizstudio.fileService.enums.ErrorStatus;
import com.bizstudio.fileService.exceptions.ServiceException;
import com.bizstudio.fileService.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author obinna.asuzu
 */
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student createStudent(Student student) {
        if (!studentRepository
                .findByPersonIdOrRegNo(student.getPersonId(), student.getRegNo())
                .isPresent()) {
            StudentEntity entity = student.toEntity();
            studentRepository.save(entity);
        }
        return student;
    }

    public Student getStudent(String personId) throws ServiceException {
        return studentRepository.findByPersonId(personId)
                .orElseThrow(() -> new ServiceException(ErrorStatus.user_not_exist))
                .toDto();
    }
}





