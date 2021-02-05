package dao.impl;

import dao.UserDao;
import domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
//    private HibernateTemplate ht;

    @Override
    public User getByUserCode(String usercode) {
        //hql
//        return getHibernateTemplate().execute(new HibernateCallback<User>() {
//            @Override
//            public User doInHibernate(Session session) throws HibernateException {
//                String hql = "from User where user_code = ?";
//                Query query = session.createQuery(hql);
//                query.setParameter(0, usercode);
//                User user = (User) query.uniqueResult();
//                return user;
//            }
//        });
        //criteria
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("user_code", usercode));
        List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void save(User u) {
        getHibernateTemplate().save(u);
    }
}
