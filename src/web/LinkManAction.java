package web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.Customer;
import domain.LinkMan;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import service.CustomerService;
import service.LinkManService;

import java.util.List;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private LinkMan linkMan = new LinkMan();
    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String saveUI(){
        List<Customer> list = customerService.findAll();
        ActionContext.getContext().getValueStack().set("list",list );
        return "saveUI";
    }

    //使用set方法接收当前页
    private Integer currentPage = 1;

    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public String list(){
        //接收参数，分页参数
        //最好使用DetachedCriteria，有方法可以直接分页查询
        DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
        if (linkMan.getLkm_name() != null) {
            dc.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
        }
        PageBean<LinkMan> pageBean = linkManService.findByPage(dc, currentPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    //保存联系人
    public String save(){
        linkManService.save(linkMan);
        return "saveSuccess";
    }

    //跳转到修改页面
    public String edit(){
        //查询所有客户
        List<Customer> list = customerService.findAll();
        //根据id查询联系人
        linkMan = linkManService.findById(linkMan.getLkm_id());
        //将list和联系人对象带到页面上
        ActionContext.getContext().getValueStack().set("list",list );//存入值栈
        ActionContext.getContext().getValueStack().push(linkMan);
        return "editSuccess";
    }
    //修改
    public String update(){
        linkManService.update(linkMan);
        return "updateSuccess";
    }
    //删除
    public String delete(){
        linkMan = linkManService.findById(this.linkMan.getLkm_id());
        linkManService.delete(linkMan);
        return "deleteSuccess";
    }
}
