package com.boxfish.lhb.dao;

import com.boxfish.lhb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by boxfish on 15/12/14.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询
     * @param userName 用户名
     * @return
     */
    List<User> findByUserName(String userName);

    /**
     * 根据关键字进行模糊并分页查询
     * @param userNameLike 用户名关键字
     * @param mobileLike 手机号关键字
     * @param pageable 分页参数
     * @return
     */
    Page<User> findByUserNameLikeOrMobileLike(String userNameLike, String mobileLike, Pageable pageable);

}
