package com.liakot.classroommanagement;

public class RoomManagement {

    private String roomNo;
    private String startTime;
    private String endTime;
    private String enroll;

    public RoomManagement()
    {

    }

    public RoomManagement(String roomNo, String startTime, String endTime, String enroll) {
        this.roomNo = roomNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.enroll = enroll;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }
}
