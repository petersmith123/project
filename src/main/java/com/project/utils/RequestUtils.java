package com.project.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * HttpServletRequest帮助类
 */
public class RequestUtils {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("x-requested-with");
		if (StringUtils.isEmpty(requestedWith)) {
			return false;
		} else if (StringUtils.isNotEmpty(requestedWith) && requestedWith.equals("XMLHttpRequest")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	* 获取请求的URI参数字符串
	* @param request
	* @return eg:a=1&b=2
	*
	 */
	public static String getQueryParm(HttpServletRequest request){
		boolean hasPassword = WebUtils.hasSubmitParameter(request, "password");
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(request.getRequestURI()).query(request.getQueryString());
		if(hasPassword){
			uriComponentsBuilder.replaceQueryParam("password", "********");
		}
		return uriComponentsBuilder.toUriString();
	}
	

	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)
				&& StringUtils.contains(ip, ",")) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			ip = StringUtils.substringBefore(ip, ",");
		}
		// 处理localhost访问
		if (StringUtils.isBlank(ip) || "unkown".equalsIgnoreCase(ip)
				|| StringUtils.split(ip, ".").length != 4) {
			try {
				InetAddress inetAddress = InetAddress.getLocalHost();
				ip = inetAddress.getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return ip;
	}

	//获取带有项目名的域名
	public static String getDomain(HttpServletRequest request){
		String contextPath = request.getContextPath();
		if (StringUtils.isNotEmpty(contextPath)) {
			return contextPath;
		} else {
			if (request.getServerPort() == 80 || request.getServerPort() == 443) {
				return request.getScheme() + "://" + request.getServerName() + request.getContextPath();
			} else {
				return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			}
		}
	}
	
	//获取域名
	public static String getSimpleDomain(HttpServletRequest request){
		String contextPath = request.getContextPath();
		if (StringUtils.isNotEmpty(contextPath)) {
			return contextPath;
		} else {
			if (request.getServerPort() == 80 || request.getServerPort() == 443) {
				return request.getScheme() + "://" + request.getServerName();
			} else {
				return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			}
		}
	}
}
