package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author slx
 * 用户module对应的数据库
 */

@Entity
@Table(name = "TB_USER")
public class User {

    //主键
    @Id
    @PrimaryKeyJoinColumn
    //主键生成的存储类型
    @GeneratedValue(generator = "uuid")
    //把uuid生成器定义为uuid2,（常规的UUID toStirng）
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    //不允许更改，不允许为null
    @Column(updatable = false, nullable = false)
    private String id;

    //不允许为空，必须为之
    @Column(nullable = false, length = 128, unique = true)
    private String name;

    @Column(nullable = false, length = 62, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    //头像
    @Column
    private String portrait;

    @Column
    private String description;

    //性别（有初始值，不能为空）
    @Column(nullable = false)
    private int sex = 0;

    //用于拉取用户信息，必须唯一
    @Column(unique = true)
    private String token;

    //用于推送的设备
    @Column
    private String pushId;

    //创建时间戳，在创建时已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    //更新时间戳，在创建时已经写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    //最后一次接收到消息的时间
    @Column
    private LocalDateTime lastReceiveAt = LocalDateTime.now();

    //我关注人的列表
    @JoinColumn(name = "originId")//对应UserFollow表的originId字段
    @LazyCollection(LazyCollectionOption.EXTRA)//懒加载，在加载用户信息时不加载次字段
    //一个用户可以有很多人关注
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> following = new HashSet<>();

    //关注我的人
    @JoinColumn(name = "targetId")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> followers = new HashSet<>();


    // 我所有创建的群
    // 对应的字段为：Group.ownerId
    @JoinColumn(name = "ownerId")
    // 懒加载集合方式为尽可能的不加载具体的数据，
    // 当访问groups.size()仅仅查询数量，不加载具体的Group信息
    // 只有当遍历集合的时候才加载具体的数据
    @LazyCollection(LazyCollectionOption.EXTRA)
    // FetchType.LAZY：懒加载，加载用户信息时不加载这个集合
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Group> groups = new HashSet<>();

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPortrait() {
        return portrait;
    }

    public User setPortrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getSex() {
        return sex;
    }

    public User setSex(int sex) {
        this.sex = sex;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public String getPushId() {
        return pushId;
    }

    public User setPushId(String pushId) {
        this.pushId = pushId;
        return this;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public User setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public User setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public LocalDateTime getLastReceiveAt() {
        return lastReceiveAt;
    }

    public User setLastReceiveAt(LocalDateTime lastReceiveAt) {
        this.lastReceiveAt = lastReceiveAt;
        return this;
    }

    public Set<UserFollow> getFollowing() {
        return following;
    }

    public User setFollowing(Set<UserFollow> following) {
        this.following = following;
        return this;
    }

    public Set<UserFollow> getFollowers() {
        return followers;
    }

    public User setFollowers(Set<UserFollow> followers) {
        this.followers = followers;
        return this;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public User setGroups(Set<Group> groups) {
        this.groups = groups;
        return this;
    }
}
