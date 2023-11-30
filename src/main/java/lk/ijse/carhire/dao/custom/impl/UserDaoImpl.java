package lk.ijse.carhire.dao.custom.impl;

import lk.ijse.carhire.dao.custom.UserDao;
import lk.ijse.carhire.dbHibernate.HibernateUtil;
import lk.ijse.carhire.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public boolean save(User user) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();

            session.persist(user);

            transaction.commit();
            return true;
        }catch (Exception e){
            throw e;

        }
    }

    @Override
    public User getUser(String username) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();

            Query<User> query = session.createQuery("FROM User WHERE username = :username",User.class);
            query.setParameter("username", username);

            User user = query.uniqueResult();

            transaction.commit();

            return user;

        }catch (Exception e){
            throw e;

        }
    }
}
