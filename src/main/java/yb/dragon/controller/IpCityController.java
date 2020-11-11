package yb.dragon.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yb.dragon.utils.IpUtil;


@RestController
public class IpCityController {

	@RequestMapping("/nginxTest")
	@CrossOrigin(value = "*") // 支持跨域
	public String nginxTest() {
		return "ipciti-1";
	}
	
	@RequestMapping("/ipAddress")
	@CrossOrigin(value = "*") // 支持跨域
	public String ipAddress(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
	
	@RequestMapping("/IpCityInfo")
	@CrossOrigin(value = "*") // 支持跨域
	public String IpCityInfo(HttpServletRequest request) throws IOException {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		IpUtil ipUtil = new IpUtil();
		String ipCity = ipUtil.getCityInfo(ip);
		
		
		return ipCity;
	}
}
