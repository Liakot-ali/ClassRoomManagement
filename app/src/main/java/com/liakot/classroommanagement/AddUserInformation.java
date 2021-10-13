package com.liakot.classroommanagement;

public class AddUserInformation {

    private String userName;
    private String userPhoneNumber;
    private String userSID;
    private String userLevel;
    private String userSemester;
    private String userSession;
    private String userPass;
    private String userEmail;
    private String userDepartment;
    private String userUniqueId;
    private String profilePicture;
    private String pictureVisibility;

    public AddUserInformation()
    {

    }


    public  AddUserInformation(String userName, String userEmail, String userPhoneNumber, String userSID, String userDepartment, String userLevel, String userSemester, String userSession, String userPass, String profilePicture, String userUniqueId) {
        this.userName=userName;
        this.userEmail=userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userSID = userSID;
        this.userDepartment = userDepartment;
        this.userLevel = userLevel;
        this.userSemester = userSemester;
        this.userSession = userSession;
        this.userPass = userPass;
        this.profilePicture = profilePicture;
        this.userUniqueId = userUniqueId;
    }

    public  AddUserInformation(String userName, String userEmail, String userPhoneNumber, String userSID, String userDepartment, String userLevel, String userSemester, String userSession, String userPass, String profilePicture, String userUniqueId, String pictureVisibility) {
        this.userName=userName;
        this.userEmail=userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userSID = userSID;
        this.userDepartment = userDepartment;
        this.userLevel = userLevel;
        this.userSemester = userSemester;
        this.userSession = userSession;
        this.userPass = userPass;
        this.profilePicture = profilePicture;
        this.userUniqueId = userUniqueId;
        this.pictureVisibility = pictureVisibility;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setUserSession(String userSession) {
        this.userSession = userSession;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserUniqueId() {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPictureVisibility() {
        return pictureVisibility;
    }

    public void setPictureVisibility(String pictureVisibility) {
        this.pictureVisibility = pictureVisibility;
    }
}
