package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 申请表
 * 好友申请、群申请、管理员申请等
 */

@Entity
@Table(name = "TB_APPLY")
public class Apply {

    public static final int TYPE_ADD_USER = 1; // 添加好友
    public static final int TYPE_ADD_GROUP = 2; // 加入群

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;

    // 描述部分，对我们的申请信息做描述
    // eg: 我想加你为好友！！
    @Column(nullable = false)
    private String description;


    // 附件 可为空
    // 可以附带图片地址，或者其他
    @Column(columnDefinition = "TEXT")
    private String attach;


    // 当前申请的类型
    @Column(nullable = false)
    private int type;


    // 目标Id 不进行强关联，不建立主外键关系
    //判断是入群还是加好友
    // type->TYPE_ADD_USER：User.id
    // type->TYPE_ADD_GROUP：Group.id
    @Column(nullable = false)
    private String targetId;


    // 定义为创建时间戳，在创建时就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // 定义为更新时间戳，在创建时就已经写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();


    // 申请人 可为空 为系统信息
    // 一个人可以有很多个申请
    @ManyToOne
    @JoinColumn(name = "applicantId")
    private User applicant;
    @Column(updatable = false, insertable = false)
    private String applicantId;

    public static int getTypeAddUser() {
        return TYPE_ADD_USER;
    }

    public static int getTypeAddGroup() {
        return TYPE_ADD_GROUP;
    }

    public String getId() {
        return id;
    }

    public Apply setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Apply setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public Apply setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public int getType() {
        return type;
    }

    public Apply setType(int type) {
        this.type = type;
        return this;
    }

    public String getTargetId() {
        return targetId;
    }

    public Apply setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public Apply setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public Apply setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public User getApplicant() {
        return applicant;
    }

    public Apply setApplicant(User applicant) {
        this.applicant = applicant;
        return this;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public Apply setApplicantId(String applicantId) {
        this.applicantId = applicantId;
        return this;
    }
}
