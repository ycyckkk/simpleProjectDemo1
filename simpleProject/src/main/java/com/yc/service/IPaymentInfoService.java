package com.yc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.entity.PaymentInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by yuche on 2019/11/9.
 */
public interface IPaymentInfoService extends IService<PaymentInfo> {
    void updateDocumentId(Map<String, String> map);

    void insertPaymentInfo(PaymentInfo paymentInfo);

    List<PaymentInfo> getPaymentInfoList(PaymentInfo paymentInfo);

}
