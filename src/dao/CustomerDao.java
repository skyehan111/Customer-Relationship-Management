package dao;

import domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao {
    void save(Customer customer);

    Integer findCount(DetachedCriteria dc);

    List<Customer> findByPage(DetachedCriteria dc, Integer begin);

    Customer findById(Long cust_id);

    void delete(Customer customer);

    void update(Customer customer);

    List<Customer> findAll();
}
