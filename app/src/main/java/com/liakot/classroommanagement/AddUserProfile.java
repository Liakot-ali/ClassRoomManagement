package com.liakot.classroommanagement;

public class AddUserProfile {

    private String userName;
    private String userPhoneNumber;
    private String userSID;
    private String userLevel;
    private String userSemester;
    private String userSession;
    private String userPass;
    private String userEmail;
    private String userDepartment;

    AddUserProfile()
    {

    }

    AddUserProfile(String userName, String userEmail, String userPhoneNumber, String userSID, String userDepartment, String userLevel, String userSemester, String userSession, String userPass) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userDepartment = userDepartment;
        this.userSID = userSID;
        this.userLevel = userLevel;
        this.userSemester = userSemester;
        this.userSession = userSession;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserSID() {
        return userSID;
    }

    public void setUserSID(String userSID) {
        this.userSID = userSID;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserSemester() {
        return userSemester;
    }

    public void setUserSemester(String userSemester) {
        this.userSemester = userSemester;
    }

    public String getUserSession() {
        return userSession;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void setUserSession(String userSession) {
        this.userSession = userSession;
    }

}
