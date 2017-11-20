package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 消息推送历史表
 * 记录用户给哪台设备、推送的内容、接受者等信息
 */

@Entity
@Table(name = "TB_PUSH_HISTORY")
public class PushHsitory {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;


    // 推送的具体实体存储的都是JSON字符串
    // BLOB 是比TEXT更多的一个大字段类型
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    private String entity;


    // 推送的实体类型
    @Column(nullable = false)
    private int entityType;

    // 接收者
    // 接收者不允许为空
    // 一个接收者可以接收很多推送消息
    // FetchType.EAGER：加载一条推送消息的时候之间加载用户信息
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiverId")// 默认是：receiver_id
    private User receiver;
    @Column(nullable = false, updatable = false, insertable = false)
    private String receiverId;

    // 发送者
    // 发送者可为空，因为可能是系统消息
    // 一个发送者可以发送很多推送消息
    // FetchType.EAGER：加载一条推送消息的时候之间加载用户信息
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "senderId")
    private User sender;
    @Column(updatable = false, insertable = false)
    private String senderId;


    // 接收者当前状态下的设备推送ID
    // User.pushId 可为null
    @Column
    private String receiverPushId;

    // 定义为创建时间戳，在创建时就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // 定义为更新时间戳，在创建时就已经写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    // 消息送达的时间，可为空
    @Column
    private LocalDateTime arrivalAt;

    public String getId() {
        return id;
    }

    public PushHsitory setId(String id) {
        this.id = id;
        return this;
    }

    public String getEntity() {
        return entity;
    }

    public PushHsitory setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public PushHsitory setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public User getReceiver() {
        return receiver;
    }

    public PushHsitory setReceiver(User receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public PushHsitory setReceiverId(String receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    public User getSender() {
        return sender;
    }

    public PushHsitory setSender(User sender) {
        this.sender = sender;
        return this;
    }

    public String getSenderId() {
        return senderId;
    }

    public PushHsitory setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getReceiverPushId() {
        return receiverPushId;
    }

    public PushHsitory setReceiverPushId(String receiverPushId) {
        this.receiverPushId = receiverPushId;
        return this;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public PushHsitory setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public PushHsitory setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public LocalDateTime getArrivalAt() {
        return arrivalAt;
    }

    public PushHsitory setArrivalAt(LocalDateTime arrivalAt) {
        this.arrivalAt = arrivalAt;
        return this;
    }
}
