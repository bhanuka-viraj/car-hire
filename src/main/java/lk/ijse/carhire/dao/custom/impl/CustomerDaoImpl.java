package lk.ijse.carhire.dao.custom.impl;

import lk.ijse.carhire.dao.custom.CustomerDao;
import lk.ijse.carhire.dbHibernate.HibernateUtil;
import lk.ijse.carhire.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private final SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
    private final Session session=sessionFactory.openSession();

    @Override
    public boolean save(Customer customer) throws Exception {
        Transaction transaction=session.beginTransaction();
        session.persist(customer);

        transaction.commit();

        return true;

    }

    @Override
    public List<Customer> getAll() throws Exception {
        Transaction transaction=session.beginTransaction();
        String hql="FROM Customer";
        Query<Customer> query=session.createQuery(hql,Customer.class);

        List<Customer>customerList=query.list();

        transaction.commit();
        return customerList;




    }

    @Override
    public boolean delete(Integer nic) throws Exception {
        Transaction transaction=session.beginTransaction();

        try{
            Customer customer=session.get(Customer.class,nic);

            if(customer!=null){
                session.remove(customer);
                transaction.commit();
                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }
}
