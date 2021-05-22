package com.zlenadmin.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="admin")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String userId;
	
	private String userName;
	
	private String userMobile;
	
	private String userEmailID;
	
	private String userPassword;
	
	private String deviceID;
	
	private String deviceType;
	
	private String longitude;
	
	private String latitude;
	
	private Date createdOn;
	
	private Date modifiedOn;
	
	private String userProfileImagePath;
	
	private String zlenCode;
	
	private String QRCodePath;
	
	private String notificationID;
	
	private String readReceiptStatus;
	
	private String lastSeenStatus;
	
	private String displayName;
	
	private String onlineStatus;
	
	private String statusMessage;
	
	private String SignUpDeviceId;
	
	private String isActive; 
	
	private String isDeActivatedByAdmin;
		
	public UserDetails() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmailID() {
		return userEmailID;
	}

	public void setUserEmailID(String userEmailID) {
		this.userEmailID = userEmailID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getUserProfileImagePath() {
		return userProfileImagePath;
	}

	public void setUserProfileImagePath(String userProfileImagePath) {
		this.userProfileImagePath = userProfileImagePath;
	}

	public String getZlenCode() {
		return zlenCode;
	}

	public void setZlenCode(String zlenCode) {
		this.zlenCode = zlenCode;
	}

	public String getQRCodePath() {
		return QRCodePath;
	}

	public void setQRCodePath(String qRCodePath) {
		QRCodePath = qRCodePath;
	}

	public String getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(String notificationID) {
		this.notificationID = notificationID;
	}

	public String getReadReceiptStatus() {
		return readReceiptStatus;
	}

	public void setReadReceiptStatus(String readReceiptStatus) {
		this.readReceiptStatus = readReceiptStatus;
	}

	public String getLastSeenStatus() {
		return lastSeenStatus;
	}

	public void setLastSeenStatus(String lastSeenStatus) {
		this.lastSeenStatus = lastSeenStatus;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getSignUpDeviceId() {
		return SignUpDeviceId;
	}

	public void setSignUpDeviceId(String signUpDeviceId) {
		SignUpDeviceId = signUpDeviceId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsDeActivatedByAdmin() {
		return isDeActivatedByAdmin;
	}

	public void setIsDeActivatedByAdmin(String isDeActivatedByAdmin) {
		this.isDeActivatedByAdmin = isDeActivatedByAdmin;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userMobile=" + userMobile
				+ ", userEmailID=" + userEmailID + ", userPassword=" + userPassword + ", deviceID=" + deviceID
				+ ", deviceType=" + deviceType + ", longitude=" + longitude + ", latitude=" + latitude + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", userProfileImagePath=" + userProfileImagePath
				+ ", zlenCode=" + zlenCode + ", QRCodePath=" + QRCodePath + ", notificationID=" + notificationID
				+ ", readReceiptStatus=" + readReceiptStatus + ", lastSeenStatus=" + lastSeenStatus + ", displayName="
				+ displayName + ", onlineStatus=" + onlineStatus + ", statusMessage=" + statusMessage
				+ ", SignUpDeviceId=" + SignUpDeviceId + ", isActive=" + isActive + ", isDeActivatedByAdmin="
				+ isDeActivatedByAdmin + "]";
	}
	
}
