package web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.Customer;
import domain.PageBean;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import service.CustomerService;
import utils.UploadUtils;

import java.io.File;
import java.io.IOException;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    private Customer customer = new Customer();

    public Customer getModel() {
        return customer;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String saveUI(){
        return "saveUI";
    }

    /*
    文件上传所需要的三个属性
     */
    private String uploadFileName;//文件名
    private File upload;//文件本身
    private String uploadContentType; //文件类型

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String add() throws IOException {
        //上传图片
        if (upload != null) {
            //文件上传
            //文件上传的路径
            String path = "D:/upload";
            //随机名
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            //目录分离
            String realPath = UploadUtils.getPath(uuidFileName);
            //创建目录
            String url = path + realPath;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            //将文件上传至此目录
            File dictFile = new File(url + "/" + uuidFileName);
            FileUtils.copyFile(upload, dictFile);
            //设置image的属性值
            customer.setCust_image(url + "/" + uuidFileName);
        }
        //保存客户
        customerService.save(customer);
        return "saveSuccess";
    }

    //使用set方法接收当前页
    private Integer currentPage = 1;

    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public String list() {
        //接收参数，分页参数
        //最好使用DetachedCriteria，有方法可以直接分页查询
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        if (customer.getCust_name() != null) {
            dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }
        PageBean<Customer> pageBean = customerService.findByPage(dc, currentPage);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    public String delete() {
        customer = customerService.findById(customer.getCust_id());
        //删除图片
        if (customer.getCust_image() != null) {
            File file = new File(customer.getCust_image());
            if (file.exists()) {
                file.delete();
            }
        }
        //删除客户
        customerService.delete(customer);
        return "deleteSuccess";
    }

    //编辑客户
    public String edit() {
        customer = customerService.findById(customer.getCust_id());//customer已经保存在值栈中
        return "editSuccess";
    }

    //修改客户
    public String update() throws IOException {
        //文件修改
        if (upload != null) {
            String cust_image = customer.getCust_image();
            if (cust_image != null || !" ".equals(cust_image)) {
                File file = new File(cust_image);
                file.delete();
            }
            //文件上传
            //文件上传的路径
            String path = "D:/upload";
            //随机名
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            //目录分离
            String realPath = UploadUtils.getPath(uuidFileName);
            //创建目录
            String url = path + realPath;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            //将文件上传至此目录
            File dictFile = new File(url + "/" + uuidFileName);
            FileUtils.copyFile(upload, dictFile);
            //设置image的属性值
            customer.setCust_image(url + "/" + uuidFileName);
        }
        customerService.update(customer);
        return "updateSuccess";
    }
}
