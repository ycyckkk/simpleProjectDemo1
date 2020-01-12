package com.yc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.entity.PaymentInfo;
import com.yc.mapper.PaymentInfoMapper;
import com.yc.service.IPaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yuche on 2019/11/9.
 */
@Service("paymentInfoService")
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements IPaymentInfoService {

    @Autowired
    PaymentInfoMapper paymentInfoMapper;

    @Override
    public void updateDocumentId(Map<String, String> map) {
        paymentInfoMapper.updateDocumentId(map);
    }

    @Override
    public void insertPaymentInfo(PaymentInfo paymentInfo) {
        paymentInfoMapper.insertPaymentInfo(paymentInfo);
    }

    @Override
    public List<PaymentInfo> getPaymentInfoList(PaymentInfo paymentInfo) {
        QueryWrapper<PaymentInfo>wrapper = new QueryWrapper<PaymentInfo>();
        wrapper.eq("client_bank_code",paymentInfo.getClientBankCode());
        return paymentInfoMapper.selectList(wrapper);
    }
}
