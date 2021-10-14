package com.liakot.classroommanagement;

public class RoomActivityClass {
    String rooNo, roomStatus, className, courseName, teacherName, departmentName, crUniqueId, startTime, endTime, level, semester, roomRef;

    public RoomActivityClass(){

    }

    public RoomActivityClass(String rooNo, String roomStatus, String className, String courseName, String teacherName, String departmentName, String crUniqueId, String startTime, String endTime) {
        this.rooNo = rooNo;
        this.roomStatus = roomStatus;
        this.className = className;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.departmentName = departmentName;
        this.crUniqueId = crUniqueId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public RoomActivityClass(String rooNo, String roomStatus, String className, String courseName, String teacherName, String departmentName, String level, String semester, String crUniqueId, String startTime, String endTime) {
        this.rooNo = rooNo;
        this.roomStatus = roomStatus;
        this.className = className;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.departmentName = departmentName;
        this.level = level;
        this.semester = semester;
        this.crUniqueId = crUniqueId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RoomActivityClass(String rooNo, String roomStatus, String className, String courseName, String teacherName, String departmentName, String level, String semester, String crUniqueId, String startTime, String endTime, String roomRef) {
        this.rooNo = rooNo;
        this.roomStatus = roomStatus;
        this.className = className;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.departmentName = departmentName;
        this.level = level;
        this.semester = semester;
        this.crUniqueId = crUniqueId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomRef = roomRef;
    }

    public String getRooNo() {
        return rooNo;
    }

    public void setRooNo(String rooNo) {
        this.rooNo = rooNo;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCrUniqueId() {
        return crUniqueId;
    }

    public void setCrUniqueId(String crUniqueId) {
        this.crUniqueId = crUniqueId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoomRef() {
        return roomRef;
    }

    public void setRoomRef(String roomRef) {
        this.roomRef = roomRef;
    }
}
