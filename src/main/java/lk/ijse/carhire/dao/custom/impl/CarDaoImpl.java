package lk.ijse.carhire.dao.custom.impl;

import lk.ijse.carhire.dao.custom.CarDao;
import lk.ijse.carhire.dbHibernate.HibernateUtil;
import lk.ijse.carhire.entity.car.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
    public boolean update(Car car) {
        System.out.println("update method in dao calling -------------------------------------------------------");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Car car1=session.get(Car.class,car.getId());
            car1.setId(car.getId());
            car1.setVehicleNumber(car.getVehicleNumber());
            car1.setVehicleNumber(car.getVehicleNumber());
            car1.setYom(car.getYom());
            car1.setRented(car.isRented());
            car1.setCategory(car.getCategory());
            car1.setMaxKmPerDay(car.getMaxKmPerDay());
            car1.setSeats(car.getSeats());
            car1.setColor(car.getColor());
            car1.setRemarks(car.getRemarks());
            car1.setMinDeposit(car.getMinDeposit());
            car1.setPricePerDay(car.getPricePerDay());

            session.merge(car1);

            System.out.println("car updated");

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

        @Override
    public List<Car> getAll() {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            try {
                String hql="FROM Car";
                Query<Car> query=session.createQuery(hql, Car.class);

                List<Car>carList=query.list();

                transaction.commit();

                return carList;
            } catch (Exception e) {
                throw e;
            }
        }
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
    public boolean delete(String id) {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            try {
                Car car = session.get(Car.class, id);
                session.remove(car);

                transaction.commit();

                return true;
            } catch (Exception e) {
                throw e;
            }
        }

    }

    @Override
    public boolean setIsRented(String id, boolean isRented) {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            try {
                Car car = session.get(Car.class, id);

                car.setRented(isRented);

                session.merge(car);
                transaction.commit();

                return true;


            } catch (Exception e) {
                throw e;
            }
        }
    }
}
