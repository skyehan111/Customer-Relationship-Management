package web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.User;
import service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private UserService userService;
    private User user =new User();

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*
    用户登录
     */
    public String login() throws Exception{
        //1.调用service执行登录
        User u = userService.getUserByCodePassword(user);
        //2.返回的user对象放入session域中
        ActionContext.getContext().getSession().put("user",u );
        //3.重定向到项目首页
        return "toHome";
    }

    /*
    用户注册
     */
    public String regist(){
        userService.saveUser(user);
        return LOGIN;
    }

    public User getModel(){
        return user;
    }
}
