package com.div.dao.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.div.dao.FormDetailDAO;
import com.div.pojo.FormDetail;
import com.div.pojo.SearchCriteria;
import com.div.pojo.User;
import com.div.dto.FormDetailDTO;
import com.div.dto.UserDTO;

@Repository
public class FormDetailDAOImpl implements FormDetailDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public FormDetailDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<FormDetailDTO> findAll() {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FormDetail> cq = cb.createQuery(FormDetail.class);
        Root<FormDetail> root = cq.from(FormDetail.class);
        cq.select(root);
        List<FormDetail> formDetails = session.createQuery(cq).getResultList();
        return formDetails.stream().map(FormDetailDTO::fromModel).collect(Collectors.toList());
    }

    @Override
    public List<FormDetailDTO> findByUser(UserDTO userDto) {
        User user = userDto.toModel();
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FormDetail> cq = cb.createQuery(FormDetail.class);
        Root<FormDetail> root = cq.from(FormDetail.class);
        cq.select(root).where(cb.equal(root.get("user"), user));
        List<FormDetail> formDetails = session.createQuery(cq).getResultList();
        return formDetails.stream().map(FormDetailDTO::fromModel).collect(Collectors.toList());
    }

    @Override
    public List<FormDetailDTO> findFreeAccessWithExpiry(Date today) {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FormDetail> cq = cb.createQuery(FormDetail.class);
        Root<FormDetail> root = cq.from(FormDetail.class);
        cq.select(root).where(
                cb.and(cb.equal(root.get("accessCategory"), "free"), cb.equal(root.get("freeViewExpiry"), today)));
        List<FormDetail> formDetails = session.createQuery(cq).getResultList();
        return formDetails.stream().map(FormDetailDTO::fromModel).collect(Collectors.toList());
    }

    @Override
    public List<FormDetailDTO> getSortedFormDetails(String sortField, String sortOrder) {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FormDetail> cq = cb.createQuery(FormDetail.class);
        Root<FormDetail> root = cq.from(FormDetail.class);
        Order order = "asc".equalsIgnoreCase(sortOrder) ? cb.asc(root.get(sortField))
                : cb.desc(root.get(sortField));
        cq.select(root).orderBy(order);
        List<FormDetail> formDetails = session.createQuery(cq).getResultList();
        return formDetails.stream().map(FormDetailDTO::fromModel).collect(Collectors.toList());
    }

    @Override
    public void save(FormDetailDTO formDetailDto) {
        getCurrentSession().persist(formDetailDto.toModel());
    }

    @Override
    public void save(FormDetail formDetail) {
        getCurrentSession().persist(formDetail);
    }

    @Override
    public void update(FormDetailDTO formDetailDto) {
        getCurrentSession().merge(formDetailDto.toModel());
    }

    @Override
    public FormDetailDTO findOneDTO(Integer id) {
        FormDetail formDetail = getCurrentSession().get(FormDetail.class, id);
        return FormDetailDTO.fromModel(formDetail);
    }

    @Override
    public void deleteByIdDTO(Integer id) {
        Session session = getCurrentSession();
        FormDetail formDetail = session.get(FormDetail.class, id);
        if (formDetail != null) {
            session.delete(formDetail);
        }
    }

    @Override
    public List<FormDetailDTO> findByUserWithCriteria(UserDTO userDto, SearchCriteria searchCriteria) {
        User user = userDto.toModel();
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FormDetail> cq = cb.createQuery(FormDetail.class);
        Root<FormDetail> root = cq.from(FormDetail.class);

        Predicate predicate = cb.equal(root.get("user"), user);

        if (searchCriteria.getSearchTitle() != null && !searchCriteria.getSearchTitle().isEmpty()) {
            predicate = cb.and(predicate, cb.like(root.get("title"), "%" + searchCriteria.getSearchTitle() + "%"));
        }

        cq.where(predicate);

        if (searchCriteria.getSortField() != null && searchCriteria.getSortOrder() != null) {
            Order order = "asc".equalsIgnoreCase(searchCriteria.getSortOrder())
                    ? cb.asc(root.get(searchCriteria.getSortField()))
                    : cb.desc(root.get(searchCriteria.getSortField()));
            cq.orderBy(order);
        }

        List<FormDetail> formDetails = session.createQuery(cq).getResultList();
        return formDetails.stream().map(FormDetailDTO::fromModel).collect(Collectors.toList());
    }
}