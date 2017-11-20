package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 群<- ->成员关联表，一个用户多个群，一个群里多个用户
 */

@Entity
@Table(name = "TB_GROUP_MEMBER")
public class GroupMember {

    //定义一些群用户级别
    public static final int PERMISSION_TYPE_NONE = 0; // 默认权限，普通成员
    public static final int PERMISSION_TYPE_ADMIN = 1;  // 管理员
    public static final int PERMISSION_TYPE_ADMIN_SU = 100; // 创建者

    //定义群消息设置
    public static final int NOTIFY_LEVEL_INVALID = -1; // 默认不接收消息
    public static final int NOTIFY_LEVEL_NONE = 0; // 默认通知级别
    public static final int NOTIFY_LEVEL_CLOSE = 1; // 接收消息不提示

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;

    // 别名
    @Column
    private String alias;

    // 消息通知级别
    @Column(nullable = false)
    private int notifyLevel = NOTIFY_LEVEL_NONE;


    // 成员的权限类型
    @Column(nullable = false)
    private int permissionType = PERMISSION_TYPE_NONE;


    // 定义为创建时间戳，在创建时就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // 定义为更新时间戳，在创建时就已经写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    // 成员信息对应的用户信息
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
    @Column(nullable = false, updatable = false, insertable = false)
    private String userId;

    // 成员信息对应的群信息
    @JoinColumn(name = "groupId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Group group;
    @Column(nullable = false, updatable = false, insertable = false)
    private String groupId;

    public static int getPermissionTypeNone() {
        return PERMISSION_TYPE_NONE;
    }

    public static int getPermissionTypeAdmin() {
        return PERMISSION_TYPE_ADMIN;
    }

    public static int getPermissionTypeAdminSu() {
        return PERMISSION_TYPE_ADMIN_SU;
    }

    public static int getNotifyLevelInvalid() {
        return NOTIFY_LEVEL_INVALID;
    }

    public static int getNotifyLevelNone() {
        return NOTIFY_LEVEL_NONE;
    }

    public static int getNotifyLevelClose() {
        return NOTIFY_LEVEL_CLOSE;
    }

    public String getId() {
        return id;
    }

    public GroupMember setId(String id) {
        this.id = id;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public GroupMember setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public int getNotifyLevel() {
        return notifyLevel;
    }

    public GroupMember setNotifyLevel(int notifyLevel) {
        this.notifyLevel = notifyLevel;
        return this;
    }

    public int getPermissionType() {
        return permissionType;
    }

    public GroupMember setPermissionType(int permissionType) {
        this.permissionType = permissionType;
        return this;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public GroupMember setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public GroupMember setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public User getUser() {
        return user;
    }

    public GroupMember setUser(User user) {
        this.user = user;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public GroupMember setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Group getGroup() {
        return group;
    }

    public GroupMember setGroup(Group group) {
        this.group = group;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public GroupMember setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
}
