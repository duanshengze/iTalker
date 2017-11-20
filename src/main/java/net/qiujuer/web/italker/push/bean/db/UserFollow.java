package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户关系
 * 用于记录用户好友关系
 */

@Entity
@Table(name = "TB_USER_FOLLOW")
public class UserFollow {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;


    //定义用户没关注
    //没被一个人关注都是一条记录，你可以被很多人关注
    @ManyToOne(optional = false)
    @JoinColumn(name = "originId")//定义数据库的存储字段
    private User origin;

    @Column(nullable = false, updatable = false, insertable = false)
    private String originId;


    //你关注的人，可以关注很多人

    @ManyToOne
    @JoinColumn(name = "targetId")
    private User target;

    @Column(nullable = false, updatable = false, insertable = false)
    private String targetId;

    //别名，你对你关注的人修改的昵称
    @Column
    private String alias;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();


    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public UserFollow setId(String id) {
        this.id = id;
        return this;
    }

    public User getOrigin() {
        return origin;
    }

    public UserFollow setOrigin(User origin) {
        this.origin = origin;
        return this;
    }

    public String getOriginId() {
        return originId;
    }

    public UserFollow setOriginId(String originId) {
        this.originId = originId;
        return this;
    }

    public User getTarget() {
        return target;
    }

    public UserFollow setTarget(User target) {
        this.target = target;
        return this;
    }

    public String getTargetId() {
        return targetId;
    }

    public UserFollow setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public UserFollow setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public UserFollow setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public UserFollow setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}
