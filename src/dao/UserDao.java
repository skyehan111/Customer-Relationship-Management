package dao;

import domain.User;

public interface UserDao {
    //根据登录名称查询User对象
    User getByUserCode(String usercode);

    void save(User u);
}
