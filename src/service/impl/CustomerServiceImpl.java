package service.impl;

import dao.CustomerDao;
import domain.Customer;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.CustomerService;

import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao cd;

    public void setCd(CustomerDao cd) {
        this.cd = cd;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Customer customer) {
        cd.save(customer);
    }

    @Override
    public PageBean<Customer> findByPage(DetachedCriteria dc, Integer currentPage) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        //封装当前页数
        pageBean.setCurrentPage(currentPage);
        //封装每页记录数
        pageBean.setPageSize(5);
        //封装总记录数
        //调用dao查询总记录数
        Integer totalCount = cd.findCount(dc);
        pageBean.setTotalCount(totalCount);
        //封装页数
        double tc = totalCount.doubleValue();
        double num = Math.ceil(tc / 5);
        pageBean.setTotalPage((int) num);
        //封装每页显示记录的集合
        Integer begin = (currentPage-1) * 5;
        List<Customer> list = cd.findByPage(dc,begin);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public Customer findById(Long cust_id) {
        return cd.findById(cust_id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Customer customer) {
        cd.delete(customer);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Customer customer) {
        cd.update(customer);
    }

    @Override
    public List<Customer> findAll() {
        return cd.findAll();
    }
}
