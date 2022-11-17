package com.fc.sms.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.aliyun.tea.TeaException;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fc.sms.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author Everglow
 * Created at 2022/08/21 16:48
 */
@Service
public class MsmServiceImpl implements MsmService {

    public com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
    public boolean sendMsgPro(Map<String, Object> param, String phoneNumber) throws Exception {
        // 初始化 Client，采用 AK&SK 鉴权访问的方式，此方式可能会存在泄漏风险，建议使用 STS 方式。鉴权访问方式请参考：https://help.aliyun.com/document_detail/378657.html
        // 获取 AK 链接：https://usercenter.console.aliyun.com
        com.aliyun.dysmsapi20170525.Client client = this.createClient("LTAI5tQYP53Cy1ZsRoTxFsFB", "s2F13nzyNUkTTPPzWTh24qDY7ze96S");
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(phoneNumber)
                .setTemplateParam(JSONObject.toJSONString(param));
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
            return true;
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            return false;
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            return false;
        }
    }



    @Override
    public boolean sendMsg(Map<String, Object> param, String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) return false;
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI5tQYP53Cy1ZsRoTxFsFB", "s2F13nzyNUkTTPPzWTh24qDY7ze96S");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关参数

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phoneNumber);
        request.putQueryParameter("SignName", "阿里云短信测试");
        request.putQueryParameter("TemplateCode", "SMS_154950909");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));


        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }

}
