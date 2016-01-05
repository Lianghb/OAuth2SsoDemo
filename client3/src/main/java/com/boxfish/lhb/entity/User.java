package com.boxfish.lhb.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by boxfish on 15/12/14.
 */

@Entity
@Table(name = "simple_user")
public final class User implements Serializable {


    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue
    private Long id;


    /**
     * 用户名
     */
    @Column(name = "username")
    private String userName;

    /**
     * 手机号，唯一，最长11位
     */
    @Column(length = 11, unique = true)
    private String mobile;

    /**
     * 性别：0 女， 1 男
     */
    private Byte gender;

    /**
     * 创建时间，非空，不可更新
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User() {
    }

    public User(String userName, String mobile, Byte gender, Date createTime) {
        this.userName = userName;
        this.mobile = mobile;
        this.gender = gender;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender=" + gender +
                ", createTime=" + createTime +
                '}';
    }

    @PrePersist
    public void prePersist() {
        if (getCreateTime() == null) {
            setCreateTime(Calendar.getInstance().getTime()); //设置默认值
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj == this) {
            return true;
        }
        if (obj instanceof User) {
            return getId() == ((User) obj).getId();
        }
        return false;
    }
}
