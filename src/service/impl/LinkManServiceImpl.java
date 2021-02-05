package service.impl;

import dao.LinkManDao;
import domain.Customer;
import domain.LinkMan;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.LinkManService;

import java.util.List;
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class LinkManServiceImpl implements LinkManService {
    private LinkManDao lmd;

    public void setLmd(LinkManDao lmd) {
        this.lmd = lmd;
    }

    @Override
    public PageBean<LinkMan> findByPage(DetachedCriteria dc, Integer currentPage) {
        PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
        //封装当前页数
        pageBean.setCurrentPage(currentPage);
        //封装每页记录数
        pageBean.setPageSize(5);
        //封装总记录数
        //调用dao查询总记录数
        Integer totalCount = lmd.findCount(dc);
        pageBean.setTotalCount(totalCount);
        //封装页数
        double tc = totalCount.doubleValue();
        double num = Math.ceil(tc / 5);
        pageBean.setTotalPage((int) num);
        //封装每页显示记录的集合
        Integer begin = (currentPage-1) * 5;
        List<LinkMan> list = lmd.findByPage(dc,begin);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(LinkMan linkMan) {
        lmd.save(linkMan);
    }

    @Override
    public LinkMan findById(Long lkm_id) {
        return lmd.findById(lkm_id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void update(LinkMan linkMan) {
        lmd.update(linkMan);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(LinkMan linkMan) {
        lmd.delete(linkMan);
    }
}
