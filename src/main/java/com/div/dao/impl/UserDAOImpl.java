package com.div.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.div.dao.UserDAO;
import com.div.pojo.User;
import com.div.dto.UserDTO;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public UserDTO findByEmail(String email) {
        Session session = getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();
        return UserDTO.fromModel(user);
    }

    @Override
    public List<UserDTO> findAllDTO() {
        Session session = getCurrentSession();
        Query<User> query = session.createQuery("FROM User", User.class);
        List<User> users = query.list();
        return users.stream().map(UserDTO::fromModel).collect(Collectors.toList());
    }

    @Override
    public void save(UserDTO userDto) {
        Session session = getCurrentSession();
        session.save(userDto.toModel());
    }

    @Override
    public void update(UserDTO userDto) {
        Session session = getCurrentSession();
        session.update(userDto.toModel());
    }

    @Override
    public UserDTO findOneDTO(Integer id) {
        Session session = getCurrentSession();
        User user = session.get(User.class, id);
        return UserDTO.fromModel(user);
    }

    @Override
    public void deleteByIdDTO(Integer id) {
        Session session = getCurrentSession();
        User user = session.load(User.class, id);
        if (user != null) {
            session.delete(user);
        }
    }
}