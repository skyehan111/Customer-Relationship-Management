package test;

import dao.UserDao;
import domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class HibernateTest {
    @Resource(name = "sessionFactory")
    private SessionFactory sf;

    @Test
    public void fun1(){
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        User u = new User();
        u.setUser_code("User4");
        u.setUser_name("User4name");
        u.setUser_password("123");
        session.save(u);

        tx.commit();
        session.close();
    }
    @Resource(name = "userDao")
    private UserDao ud;
    @Test
    public void fun2(){
        User u = ud.getByUserCode("User1");
        System.out.println(u);
    }

    @Resource(name = "userService")
    private UserService us;
    @Test
    public void fun3(){
        User u = new User();
        u.setUser_code("User6");
        u.setUser_name("User6name");
        u.setUser_password("123");
        us.saveUser(u);
    }
}
