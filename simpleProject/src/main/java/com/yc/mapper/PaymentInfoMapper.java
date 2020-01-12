package com.yc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.entity.PaymentInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by yuche on 2019/11/9.
 */
@Component
public interface PaymentInfoMapper extends BaseMapper<PaymentInfo> {

    void updateDocumentId(Map<String, String> map);
    void insertPaymentInfo(PaymentInfo paymentInfo);

}
