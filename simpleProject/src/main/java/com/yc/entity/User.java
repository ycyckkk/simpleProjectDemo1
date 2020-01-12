package com.yc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.Date;

/**
 * Created by yuche on 2019/3/26.
 */
@Data
@TableName(value = "tb_user")
@ApiModel("用户实体")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId(value = "user_no", type = IdType.UUID)
    private String userNo;
    /**
     * 是电话号码，也是账号（登录用）
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 姓名
     */
    @TableField("user_name")
    private String username;
    /**
     * 密码
     */
    @TableField("pass_word")
    private String password;
    /**
     * 单位
     */
    private String email;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;
    /**
     * 状态值（1：启用，2：禁用，3：删除）
     */
    @TableField("status")
    private Integer status;
    /**
     * 职位
     */
    @TableField("job")
    private String job;
}
