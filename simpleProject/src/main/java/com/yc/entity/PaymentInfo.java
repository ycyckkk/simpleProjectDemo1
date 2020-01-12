package com.yc.entity;

/**
 * Created by yuche on 2019/11/9.
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yuche on 2019/3/26.
 */
@Data
@Builder
@TableName(value = "clm_payment_info")
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createdDate;

    /**
     * 创建人
     */
    @TableField("updated_by")
    private String updatedBy;

    /**
     * 创建时间
     */
    @TableField("updated_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String updatedDate;

    /**
     * 用户主键
     */
    //, type = IdType.UUID
    @TableId(value = "id_clm_payment_info")
    private String idClmPaymentInfo;
    /**
     */
    @TableField("client_name")
    private String clientName;

    /**
     */
    @TableField("client_type")
    private String clientType;

    /**
     */
    @TableField("client_bank_account")
    private String clientBankAccount;

    /**
     */
    @TableField("client_bank_code")
    private String clientBankCode;

    /**
     */
    @TableField("client_bank_name")
    private String clientBankName;

    /**
     */
    @TableField("bank_info_attribute")
    private String bankInfoAttribute;

    /**
     */
    @TableField("cert_type")
    private String certType;

    /**
     */
    @TableField("cert_no")
    private String certNo;

    /**
     */
    @TableField("payment_info_status")
    private String paymentInfoStatus;

    /**
     * 删除时间
     */
    @TableField("delete_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String deleteTime;

    /**
     */
    @TableField("document_id")
    private String documentId;
}
