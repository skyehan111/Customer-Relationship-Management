package dao;

import domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface LinkManDao {
    Integer findCount(DetachedCriteria dc);

    List<LinkMan> findByPage(DetachedCriteria dc, Integer begin);

    void save(LinkMan linkMan);

    LinkMan findById(Long lkm_id);

    void update(LinkMan linkMan);

    void delete(LinkMan linkMan);
}
