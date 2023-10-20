package lk.ijse.carhire.dao.custom.impl;

import lk.ijse.carhire.dao.custom.RentDao;
import lk.ijse.carhire.dbHibernate.HibernateUtil;
import lk.ijse.carhire.entity.car.Rent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RentDaoImpl implements RentDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public boolean save(Rent rent) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();

            session.persist(rent);

            transaction.commit();
            return true;
        }catch (Exception e){
            throw e;

        }
    }

    @Override
    public boolean delete(Long id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();

            Rent rent=session.get(Rent.class,id);
            session.remove(rent);

            transaction.commit();
            return true;

        }catch (Exception e){
            throw e;

        }
    }

    @Override
    public Rent getRent(Long id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();

            Rent rent=session.get(Rent.class,id);

            transaction.commit();
            return rent;
        }catch (Exception e){
            throw e;

        }
    }

    @Override
    public List<Rent> getRents() {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();

            String hql = "FROM Rent";
            Query<Rent> query = session.createQuery(hql, Rent.class);

            List<Rent> rentList=query.list();
            transaction.commit();

            return rentList;

        }catch (Exception e){
            throw e;

        }
    }
}
