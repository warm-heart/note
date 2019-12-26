package org.cloud.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cloud.note.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author wangqianlong
 * @create 2019-12-25 14:59
 */
@Mapper
@Repository
public interface UserDao {

     User findByUserName(String userName);
}
