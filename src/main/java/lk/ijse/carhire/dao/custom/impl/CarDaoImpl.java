package lk.ijse.carhire.dao.custom.impl;

import lk.ijse.carhire.dao.custom.CarDao;
import lk.ijse.carhire.dbHibernate.HibernateUtil;
import lk.ijse.carhire.entity.car.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CarDaoImpl implements CarDao {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public boolean save(Car car) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();

            session.persist(car);

            transaction.commit();
            return true;
        }catch (Exception e){
            throw e;

        }
    }

    @Override
    public List<Car> getAll() {

        return null;
    }

    @Override
    public Car get(String id) {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            try {
                Car car = session.get(Car.class, id);

                transaction.commit();

                return car;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public boolean delete(Car id) {
        return false;
    }
}
