/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.entities;

import com.bizstudio.fileService.dtos.Attendance;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author obinna.asuzu
 */
@Entity
public class AttendanceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private StudentEntity student;
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date entryTime;
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date exitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the student
     */
    public StudentEntity getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    /**
     * @return the entryTime
     */
    public Date getEntryTime() {
        return entryTime;
    }

    /**
     * @param entryTime the entryTime to set
     */
    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    /**
     * @return the exitTime
     */
    public Date getExitTime() {
        return exitTime;
    }

    /**
     * @param exitTime the exitTime to set
     */
    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttendanceEntity)) {
            return false;
        }
        AttendanceEntity other = (AttendanceEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bizstudio.fileService.entities.AttendanceEntity[ id=" + id + " ]";
    }
    
    
    public Attendance toDto(){
        Attendance attendance = new Attendance();
        attendance.setEntryTime(entryTime);
        attendance.setExitTime(exitTime);
        attendance.setStudent(student.toDto());
        return attendance;
    }
}








