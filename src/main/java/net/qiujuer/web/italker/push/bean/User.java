package net.qiujuer.web.italker.push.bean;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author qiujuer
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

}
