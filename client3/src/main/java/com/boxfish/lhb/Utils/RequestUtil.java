package com.boxfish.lhb.Utils;

import com.boxfish.lhb.vo.DTParameter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by boxfish on 15/12/18.
 */
public class RequestUtil {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(RequestUtil.class);

    /**
     * 从Request请求中获取参数pData
     * <p>pData是一个datatables发送的请求参数，为json格式</p>
     * 解析并返回DTParameter对象
     *
     * @param request HttpServletRequest对象
     * @return 获取到pdata并解析成功，返回DTParameter对象，否则返回null
     * @throws Exception 解析时发生的异常
     */
    public static DTParameter getParameters(HttpServletRequest request) throws Exception {
        String data = request.getParameter("pData");
        logger.info("data:\t" + data);
        if (StringUtils.isBlank(data)) {
            return null;
        }
        return JsonUtil.getFromJson(data, DTParameter.class);

    }
}
