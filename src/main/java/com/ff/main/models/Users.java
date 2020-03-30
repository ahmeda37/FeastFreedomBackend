package com.ff.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users", indexes = {@Index(columnList=("user_email"), unique=true)})
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId;
	
	@Column(name="user_first_name")
	@NotNull
	private String userFirstName;
	
	@Column(name="user_last_name")
	@NotNull
	private String userLastName;
	
	@Column(name="user_email")
	@NotNull
	private String userEmail;
	
	@Column(name="user_password")
	@NotNull
	private String userPassword;
	
	@Column(name="user_security_question_one")
	@NotNull
	private String userSecurityQuestionOne;
	
	@Column(name="user_answer_one")
	@NotNull
	private String userAnswerOne;
	
	@Column(name="user_security_question_two")
	@NotNull
	private String userSecurityQuestionTwo;
	
	@Column(name="user_answer_two")
	@NotNull
	private String userAnswerTwo;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserSecurityQuestionOne() {
		return userSecurityQuestionOne;
	}

	public void setUserSecurityQuestionOne(String userSecurityQuestionOne) {
		this.userSecurityQuestionOne = userSecurityQuestionOne;
	}

	public String getUserAnswerOne() {
		return userAnswerOne;
	}

	public void setUserAnswerOne(String userAnswerOne) {
		this.userAnswerOne = userAnswerOne;
	}

	public String getUserSecurityQuestionTwo() {
		return userSecurityQuestionTwo;
	}

	public void setUserSecurityQuestionTwo(String userSecurityQuestionTwo) {
		this.userSecurityQuestionTwo = userSecurityQuestionTwo;
	}

	public String getUserAnswerTwo() {
		return userAnswerTwo;
	}

	public void setUserAnswerTwo(String userAnswerTwo) {
		this.userAnswerTwo = userAnswerTwo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
}
