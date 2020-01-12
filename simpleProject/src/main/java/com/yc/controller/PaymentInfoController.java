package com.yc.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.yc.entity.PaymentInfo;
import com.yc.entity.User;
import com.yc.service.IPaymentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.Map;

/**
 * Created by yuche on 2019/11/9.
 */
@RestController
@RequestMapping("/paymentInfo")
public class PaymentInfoController {

    private static final Logger log = LoggerFactory.getLogger(PaymentInfoController.class);

    @Autowired
    private IPaymentInfoService paymentInfoService;

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public int insertPaymentInfo(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo) {
        paymentInfoService.save(paymentInfo);
        return HttpServletResponse.SC_OK;
    }

    @RequestMapping(value = "getPaymentInfo/{clientName}", method = RequestMethod.GET)
    public String getPaymentInfo(@PathVariable("clientName") String clientName) throws UnsupportedEncodingException {
        // 解码
        clientName = URLDecoder.decode(clientName, "UTF-8");
        // 编码 URLEncoder.encode(clientName, "UTF-8");

        log.info("clientName=" + clientName);
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<PaymentInfo>();
        queryWrapper.eq("client_name", clientName);
        return JSON.toJSONString(paymentInfoService.getOne(queryWrapper));
    }

    @RequestMapping(value = "updateDocumentId", method = RequestMethod.POST)
    public int updateDocumentId(@RequestBody PaymentInfo paymentInfo) {
        Map<String, String> map = Maps.newHashMap();
        map.put("tableName", paymentInfo.getClientName());
        map.put("documentId", paymentInfo.getDocumentId());
        map.put("idClmPaymentInfo", paymentInfo.getIdClmPaymentInfo());
        paymentInfoService.updateDocumentId(map);
        return HttpServletResponse.SC_OK;
    }

    @RequestMapping(value = "insertPaymentInfo1", method = RequestMethod.POST)
    public int insertPaymentInfo1(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo) {
        paymentInfoService.insertPaymentInfo(paymentInfo);
        return HttpServletResponse.SC_OK;
    }

    @RequestMapping(value = "getPaymentInfo1ByClientBankCode/{clientBankCode}", method = RequestMethod.GET)
    public String getPaymentInfo1ByClientName(@PathVariable("clientBankCode") String clientBankCode) {
        PaymentInfo paymentInfo = PaymentInfo.builder().clientBankCode(clientBankCode).build();
        return JSON.toJSONString(paymentInfoService.getPaymentInfoList(paymentInfo));
    }
}
