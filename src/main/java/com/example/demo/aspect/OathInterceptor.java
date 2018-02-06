package com.example.demo.aspect;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.ExceptionType;
import com.example.demo.common.ResultUtil;
import com.example.demo.conf.PublicConfig;

public class OathInterceptor implements HandlerInterceptor {
	private static final Logger logge = LoggerFactory.getLogger(OathInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String peerid = request.getParameter("peerid");
		boolean isCheck = false;
		if (!StringUtils.isEmpty(peerid)) {
			if (PublicConfig.checkPeerid(peerid)) {
				isCheck = true;
			}
		}

		if (!isCheck) {
			PrintWriter writer = null;
			OutputStreamWriter osw = null;
			try {
				osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
				writer = new PrintWriter(osw, true);
				String jsonStr = JSONObject.toJSONString(ResultUtil.error(ExceptionType.PEERID_ERROR));
				logge.error(jsonStr);
				logge.info(jsonStr);
				writer.write(jsonStr);
				writer.flush();
				writer.close();
				osw.close();
			} catch (UnsupportedEncodingException e) {
				logge.error("过滤器返回信息失败:" + e.getMessage(), e);
			} catch (IOException e) {
				logge.error("过滤器返回信息失败:" + e.getMessage(), e);
			} finally {
				if (null != writer) {
					writer.close();
				}
				if (null != osw) {
					osw.close();
				}
			}
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
