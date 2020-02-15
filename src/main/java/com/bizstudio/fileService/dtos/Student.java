/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.dtos;

import com.bizstudio.fileService.entities.StudentEntity;

/**
 *
 * @author obinna.asuzu
 */
public class Student {
    private String personId;
    private String regNo;
    private String fullName;
    private String email;
    private String phone;

    public Student() {
    }

    /**
     * @return the personId
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /**
     * @return the regNo
     */
    public String getRegNo() {
        return regNo;
    }

    /**
     * @param regNo the regNo to set
     */
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    public StudentEntity toEntity(){
        StudentEntity entity = new StudentEntity();
        entity.setPersonId(personId);
        entity.setFullName(fullName);
        entity.setRegNo(regNo);
        entity.setEmail(email);
        entity.setPhone(phone);
        return entity;
    }
}




