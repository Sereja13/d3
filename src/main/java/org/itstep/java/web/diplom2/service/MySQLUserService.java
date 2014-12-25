/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.itstep.java.web.diplom2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.transaction.Transactional;
import org.hibernate.Interceptor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.itstep.java.web.diplom2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sereja
 */
@Repository(value = "userDAO")
@Transactional
public class MySQLUserService implements UserService, UserDetailsService {
    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory sf;
    
//    Properties props;
//    @Autowired
//    Connection conn;
    
    @Override
    public boolean authorize(String name, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Не разработано
    }

    @Override
    public boolean deleteUser(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //Не разработано
    }

    @Override
    public boolean edit(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //Не разработано
    }

    @Override
    public List<User> findAll()  {
        return sf.getCurrentSession().createQuery("from User").list();
    }
    
    @Override
    public int save(User u) { // Не работает
        //Session session = sf.getCurrentSession();
        //session.save(u);
        return  (Integer) sf.getCurrentSession().save(u);
    }
    
    @Override
    public User find(Integer id) {
        return (User) sf.getCurrentSession().get(User.class, id);
    }

    @Override
    public User findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Не разработано
    }

    @Override
    public List<User> findByNames(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //Не разработано
    }


    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException { // Не работает
        User user = null;
        
        try {
            Query q = sf.getCurrentSession().createQuery("from User u where u.email = :email");
            q.setString("email", string);
            List<User> users = q.list();
            if (users.isEmpty()) {
                throw new UsernameNotFoundException("User " + string + " not found");
            } else {
                return users.get(0);
            }
        } catch (Exception e) {
            System.err.println("ohshit");
        }
        return null;
    }
}
