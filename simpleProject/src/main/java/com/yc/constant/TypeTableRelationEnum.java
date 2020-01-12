package com.yc.constant;

/**
 * Created by yuche on 2019/11/9.
 */
public enum TypeTableRelationEnum {

    PAYMENT_INFO_DOCUMENT("100", "clm_payment_info"),

    CAR_LOSS_DOCUMENT("004", "clm_car_loss");

    private String documentType;

    private String tableName;

    TypeTableRelationEnum(String documentType, String tableName) {
        this.documentType = documentType;
        this.tableName = tableName;
    }
}
