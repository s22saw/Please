package please.spring.dto;


public class MemberDTO {
    private String userID;
    private String userPW;
    private String userName;
    private String picFileName;
    private int picFileSize;
    private String userEmail;
    private String userContact; // 휴대폰 번호
    private String userAddress;
    private int userDiv; // 사용자 구분(0:관리자,1:사용자,2:판매자)
    private String interest1;
    private String interest2;

    private String sessionUserID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPicFileName() {
        return picFileName;
    }

    public void setPicFileName(String picFileName) {
        this.picFileName = picFileName;
    }

    public int getPicFileSize() {
        return picFileSize;
    }

    public void setPicFileSize(int picFileSize) {
        this.picFileSize = picFileSize;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserDiv() {
        return userDiv;
    }

    public void setUserDiv(int userDiv) {
        this.userDiv = userDiv;
    }

    public String getInterest1() {
        return interest1;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public void setInterest2(String interest2) {
        this.interest2 = interest2;
    }

//    public Timestamp getJoinDateTime() {
//        return joinDateTime;
//    }
//
//    public void setJoinDateTime(Timestamp joinDateTime) {
//        this.joinDateTime = joinDateTime;
//    }


    public String getSessionUserID() {
        return sessionUserID;
    }

    public void setSessionUserID(String sessionUserID) {
        this.sessionUserID = sessionUserID;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "userID='" + userID + '\'' +
                ", userPW='" + userPW + '\'' +
                ", userName='" + userName + '\'' +
                ", picFileName='" + picFileName + '\'' +
                ", picFileSize=" + picFileSize +
                ", userEmail='" + userEmail + '\'' +
                ", userContact='" + userContact + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userDiv=" + userDiv +
                ", interest1='" + interest1 + '\'' +
                ", interest2='" + interest2 +
                '}';
    }
}
