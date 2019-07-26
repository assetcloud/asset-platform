package com.asset.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Mail implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 接收人邮箱(多个逗号分开)
     */
    private String receiveEmail;

    /**
     * 主题
     */
    private String subject;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 模板
     */
    private String template;

    /**
     * 发送时间
     */
    private Timestamp sendTime;
}
