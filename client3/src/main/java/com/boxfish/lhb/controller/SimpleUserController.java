package com.boxfish.lhb.controller;

import com.boxfish.lhb.Utils.RequestUtil;
import com.boxfish.lhb.entity.User;
import com.boxfish.lhb.service.UserService;
import com.boxfish.lhb.vo.DTParameter;
import com.boxfish.lhb.vo.DTResult;
import com.boxfish.lhb.vo.Search;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by boxfish on 15/12/22.
 */
@RestController
@RequestMapping(value="/user")
public class SimpleUserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 为<b>simple user</b>表提供数据
     *
     * @param request 请求中必须传递datatables请求参数对象，为json格式
     * @return
     * @throws Exception 运行时异常
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_DBA"})
    @RequestMapping(value = "/getTableData", method = RequestMethod.GET)
    public Object getAllTest(HttpServletRequest request) throws Exception {

        logger.info("start");
        DTParameter dtParameter = RequestUtil.getParameters(request); //从请求中获取databases参数
        DTResult<User> result = new DTResult<>(dtParameter.getDraw()); //返回结果
        try {
            if (dtParameter == null) {
                throw new Exception("请求参数有误，" + System.currentTimeMillis());
            }
            int page_size = dtParameter.getLength() > 0 ? dtParameter.getLength() : 10;
            int start_num = dtParameter.getStart() < 0 ? 0 : dtParameter.getStart();
            int page_number = start_num / page_size; //计算页码，页码从0开始

            Search allSearch = dtParameter.getSearch();
            if (allSearch != null && !StringUtils.isBlank(allSearch.getValue())) {
                //搜索，like查询，暂时只支持名字和手机号搜索查询
                Page<User> page = userService.searchPageUser(page_number, page_size, allSearch.getValue().trim());
                result.setData(page.getContent());
                result.setRecordsTotal(userService.getTotalNumbers());
                result.setRecordsFiltered(page.getTotalElements());
                logger.info(String.format("分页搜索查询：根据(%s)搜索查询第%d页共%d条记录，总共%d条记录，过滤后记录数%d",
                        allSearch.getValue(), page_number, result.getData().size(),
                        result.getRecordsFiltered(), result.getRecordsTotal()));
            } else {
                //非搜索
                Page<User> page = userService.getPageUser(page_number, page_size);
                result.setData(page.getContent());
                result.setRecordsTotal(page.getTotalElements());
                result.setRecordsFiltered(page.getTotalElements());
                logger.info(String.format("分页非搜索查询：查询第%d页共%d条记录，总共%d条记录，过滤后记录数%d",
                        page_number, result.getData().size(),
                        result.getRecordsFiltered(), result.getRecordsTotal()));
            }
        } catch (Exception e) {
            long id = System.currentTimeMillis();
            result.setError(String.format("获取数据失败,id=%d", id));
            logger.warn("获取数据失败，id=%d\n%s", id, e.getMessage());
        } finally {
            logger.info(String.format("results:%s", result.toString()));
            return result;
        }
    }

}
