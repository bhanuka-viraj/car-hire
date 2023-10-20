package lk.ijse.carhire.dao.custom.impl;

import lk.ijse.carhire.dao.custom.CategoryDao;
import lk.ijse.carhire.dbHibernate.HibernateUtil;
import lk.ijse.carhire.entity.car.CarCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public boolean save(CarCategory category) {

        try(Session session = sessionFactory.openSession()){
            Transaction transaction=session.beginTransaction();

            session.persist(category);

            transaction.commit();
            return true;
        }catch (Exception e){
            throw e;

        }
    }

    @Override
    public List<CarCategory> getAll() {

        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            try {
                String hql = "FROM CarCategory";
                Query<CarCategory> query = session.createQuery(hql, CarCategory.class);

                List<CarCategory> categoryList = query.list();

                transaction.commit();
                return categoryList;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public CarCategory get(String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                String hql = "FROM CarCategory WHERE name = :categoryName";
                Query<CarCategory> query = session.createQuery(hql, CarCategory.class);
                query.setParameter("categoryName", name);

                CarCategory category = query.getSingleResult(); // Assuming name is unique

                transaction.commit();
                return category;
            } catch (Exception e) {
                throw e;
            }
        }
    }


    @Override
    public boolean delete(Long id) {
        return false;
    }
}
