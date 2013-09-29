package egovframework.com.guruguru.dashboard.user.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UserInfo {

	private String userId;
	private String userName;
	private String city;
	private String gender;
	private String age;
	private String firstCreateDate;
	private String lastModifyDate;
	
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getFirstCreateDate() {
		return firstCreateDate;
	}
	public void setFirstCreateDate(String firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}