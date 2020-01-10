package com.liucg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liucg.until.aes.WXBizMsgCrypt;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;

@Controller
@RequestMapping("/weixin")
@Slf4j
public class Weixincontroller {
//	@ResponseBody
	@RequestMapping("/getMsg")
	public void getWeixin(HttpServletResponse response, String msg_signature, String timestamp, String nonce,
			String echostr) throws IOException {
		log.info("微信请求数据{}---{}---{},---{}", msg_signature, timestamp, nonce, echostr);
		/**
		 * PrintWriter out = response.getWriter(); try { if
		 * (WXPublicUtils.verifyUrl(msg_signature, timestamp, nonce)) {
		 * out.print(echostr); } } catch (AesException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(" wxc5771e1e819ee85b"); // 设置微信公众号的appid
		config.setSecret("614FFRtsLPD4tpRv_9d6zR1ATQ-rjsLVGX0hKbHOXzs"); // 设置微信公众号的app corpSecret
		config.setToken("weixin"); // 设置微信公众号的token
		config.setAesKey("3AdqQ83EsQcHwQJQQdDAOMUCy91Qu22jm0yN8XbQpov"); // 设置微信公众号的EncodingAESKey
		PrintWriter out = response.getWriter();
		WxMpService wxMpService = new me.chanjar.weixin.mp.api.WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(config);
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		log.info("判断:{}",wxMpService.checkSignature(timestamp, nonce, msg_signature));
		if (!wxMpService.checkSignature(timestamp, nonce, msg_signature)) {
			out.print(echostr);
		}
		out.print(echostr);
		out.close();
		out = null;

	}
	@RequestMapping(value = "callback",method = RequestMethod.GET)
    public void connect(HttpServletRequest request, HttpServletResponse response){
 
        //当你提交以上信息时，企业号将发送GET请求到填写的URL上，GET请求携带四个参数，企业在获取时需要做urldecode处理，否则会验证不成功
 
        // 微信加密签名
        String msg_signature = request.getParameter("msg_signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echoStr = request.getParameter("echostr");
 
        //回调key值
        String sEchoStr = null;
 
        String contacts_token = "weixin";//PropertiesUtil.getProperty("contacts_token");
        String contacts_encodingaeskey = "3AdqQ83EsQcHwQJQQdDAOMUCy91Qu22jm0yN8XbQpov";//PropertiesUtil.getProperty("contacts_encodingaeskey");
        String corpId = "wxc5771e1e819ee85b";//PropertiesUtil.getProperty("corpid");
 
        try {
 
            PrintWriter out = response.getWriter();
 
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(contacts_token,contacts_encodingaeskey,corpId);
            sEchoStr = wxcpt.VerifyURL(msg_signature,timestamp,nonce,echoStr);
 
            if(StringUtils.isBlank(sEchoStr)){
                log.error("URL验证失败");
            }
            out.write(sEchoStr);
            out.flush();
 
        }catch (Exception e){
            log.error("企业微信回调url验证错误",e);
        }
    }

}
