package com.handsome.auction.bean;

import com.wang.db2.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * by wangrongjun on 2017/7/12.
 */
@Entity(name = "auctionUser")
public class User {

    @com.wang.db2.Id
    @Id
    @GeneratedValue
    private int userId;
    @Column(length = 20, nullable = false, unique = true)
    private String username;//用户名
    @Column(length = 20, nullable = false)
    private String password;//密码
    @Column(length = 20)
    private String idNumber;//身份证
    @Column(length = 20)
    private String phone;//电话
    @Column(length = 200)
    private String address;//地址
    @Column(length = 6)
    private String postNumber;//邮编
    @Column(columnDefinition = "int default 0")
    private Integer root;//是否管理员： 0表示普通用户  1表示管理员
    @Column(length = 100)
    private String passwordQuestion;//找回密码（问题）
    @Column(length = 100)
    private String passwordAnswer;//答案

    public User() {
    }

    public User(String username, String password, String idNumber, String phone, String address,
                String postNumber, Integer root, String passwordQuestion, String passwordAnswer) {
        this.username = username;
        this.password = password;
        this.idNumber = idNumber;
        this.phone = phone;
        this.address = address;
        this.postNumber = postNumber;
        this.root = root;
        this.passwordQuestion = passwordQuestion;
        this.passwordAnswer = passwordAnswer;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String userCardNo) {
        this.idNumber = userCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String userTel) {
        this.phone = userTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String userAddress) {
        this.address = userAddress;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String userPostNumber) {
        this.postNumber = userPostNumber;
    }

    public Integer getRoot() {
        return root;
    }

    public void setRoot(Integer userIsadmin) {
        this.root = userIsadmin;
    }

    public String getPasswordQuestion() {
        return passwordQuestion;
    }

    public void setPasswordQuestion(String userQuestion) {
        this.passwordQuestion = userQuestion;
    }

    public String getPasswordAnswer() {
        return passwordAnswer;
    }

    public void setPasswordAnswer(String userAnswer) {
        this.passwordAnswer = userAnswer;
    }
}
