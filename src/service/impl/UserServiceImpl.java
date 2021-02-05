package service.impl;

import dao.UserDao;
import domain.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import utils.MD5Utils;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class UserServiceImpl implements UserService {
    private UserDao ud;

    @Override
    public User getUserByCodePassword(User u) {
        String password = MD5Utils.md5(u.getUser_password());
        u.setUser_password(password);
        //1.根据登录名称查询登录用户
        User existU = ud.getByUserCode(u.getUser_code());
        //2.判断用户是否存在
        if (existU == null){
            throw new RuntimeException("用户名不存在！");
        }
        //3.判断密码是否正确
        if (!existU.getUser_password().equals(u.getUser_password())){
            throw new RuntimeException("密码错误！");
        }
        //4.返回查询到的用户对象
        return existU;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveUser(User u) {
        //对密码进行加密处理
        String password = MD5Utils.md5(u.getUser_password());
        u.setUser_password(password);
        u.setUser_state("1");
        ud.save(u);
    }


    public void setUd(UserDao ud) {
        this.ud = ud;
    }
}
