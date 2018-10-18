package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserVo {
	private String userId;
	private String name;
	private String pass;
	private String addr1;
	private String addr2;
	private String zipcd;
	private Date   birth;
	private String email;
	private String tel;
	private String profile;
	private String alias;
	int rnum;
	
	public UserVo(){
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcd() {
		return zipcd;
	}

	public void setZipcd(String zipcd) {
		this.zipcd = zipcd;
	}

	public Date getBirth() {
		return birth;
	}
	
	public String getBirthFomat(Date birth) {
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String toDate = transFormat.format(birth);
		
		return toDate;
	}

	public void setBirth(Date birth) {

		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", name=" + name + ", pass=" + pass
				+ ", add1=" + addr1 + ", add2=" + addr2 + ", zipcd=" + zipcd
				+ ", birth=" + birth + ", email=" + email + ", tel=" + tel
				+ ", profile=" + profile + ", alias=" + alias + ", rnum="
				+ rnum + "]";
	}

	
	/**
	* Method : authPass
	* 작성자 : pc03
	* 변경이력 :
	* @param encryptPass
	* @return
	* Method 설명 : 비밀번호 검증
	*/
	public boolean authPass(String encryptPass) {
		return getPass().equals(encryptPass);
	}

}
