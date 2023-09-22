package com.quanxiaoha.weblog.common.utils;

import com.quanxiaoha.weblog.common.enums.ResponseCodeEnum;
import com.quanxiaoha.weblog.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author yjf
 * @description 访客IP归属地工具类
 */
@Slf4j
public class AgentRegionUtils {

    /**
     * 获取http请求ip
     * @param request 请求
     * @return ipAddress
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if ("127.0.0.1".equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    assert inet != null;
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割 "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * 根据ip 获取归属地
     * @param ip 访问ip
     * @return 返回归属地结果
     */
    public static String getIpRegion(String ip, String xdbPath) {
        // 1、创建 searcher 对象
        String country = "中国";
        String hdu = "0";
        Searcher searcher;
        try {
            searcher = Searcher.newWithFileOnly(xdbPath);
        } catch (IOException e) {
            log.error("failed to create searcher with {}: {}\n", xdbPath, e);
            return "外太空";
        }

        // 2、查询 ip = "175.24.184.183";
        try {
            String region = searcher.search(ip);
            region = region.replace("|", " ");
            String[] cityList = region.split(" ");
            if (cityList.length > 0) {
                // 国内的显示到具体的省
                if (country.equals(cityList[0])) {
                    if (cityList.length > 1) {
                        log.info(cityList[0]+"-"+cityList[2]+"-"+cityList[3]+"-"+cityList[4]);
                        return cityList[0]+"-"+cityList[2]+"-"+cityList[3]+"-"+cityList[4];
                    }
                } else if (hdu.equals(cityList[0])) {
                    return "中国-浙江省-杭州市-HDU";
                } else {
                    // 国外显示到国家城市
                    if (cityList.length > 1) {
                        return cityList[0]+"-"+cityList[2];
                    }
                }

            }
        } catch (Exception e) {
            log.error("failed to search({}): {}\n", ip, e);
            throw new BizException(ResponseCodeEnum.AGENT_REGION_SEARCH_ERROR);
        } finally {
            // 3、关闭资源
            try {
                searcher.close();
            } catch (IOException e) {
                log.error("failed to close searcher:", e);
            }
        }

        // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。

        return "未知";
    }
}
