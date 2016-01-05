package com.boxfish.lhb.service;

import com.boxfish.lhb.dao.UserDao;
import com.boxfish.lhb.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by boxfish on 15/12/14.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 按照给定的页码和大小，查询指定的页
     *
     * @param pageNum 页码,从0开始
     * @param size    每页大小
     * @return
     */
    public Page<User> getPageUser(int pageNum, int size) {
        logger.info(String.format("execute getPageUser()\npageNum=%s\tsize=%d", pageNum, size));
        PageRequest pageRequest = new PageRequest(pageNum, size);
        Page<User> page = userDao.findAll(pageRequest);
        return page;
    }


    /**
     * 根据searchValue搜索查询
     * 目前支持名字和手机号搜索
     *
     * @param pageNum
     * @param size
     * @param searchValue
     * @return
     */
    public Page<User> searchPageUser(int pageNum, int size, String searchValue) {
        searchValue = "%" + searchValue + "%";
        return userDao.findByUserNameLikeOrMobileLike(searchValue, searchValue, new PageRequest(pageNum, size));
    }

    /**
     * 获取表的总记录数
     *
     * @return
     */
    public long getTotalNumbers() {
        return userDao.count();
    }
}
