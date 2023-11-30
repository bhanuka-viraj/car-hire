package lk.ijse.carhire.dao.custom.impl;

import lk.ijse.carhire.dao.custom.RentDao;
import lk.ijse.carhire.dbHibernate.HibernateUtil;
import lk.ijse.carhire.entity.car.Rent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RentDaoImpl implements RentDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public boolean save(Rent rent) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(rent);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    @Override
    public boolean update(Rent rent) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(rent);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    @Override
    public boolean delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Rent rent = session.get(Rent.class, id);
                session.remove(rent);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    @Override
    public Rent getRent(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Rent rent = session.get(Rent.class, id);
                transaction.commit();
                return rent;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    @Override
    public List<Rent> getRents() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                String hql = "FROM Rent";
                Query<Rent> query = session.createQuery(hql, Rent.class);
                List<Rent> rentList = query.list();
                transaction.commit();
                return rentList;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    @Override
    public boolean setIsReturn(String id, boolean value) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Rent rent = session.get(Rent.class, id);
                rent.setReturn(value);
                session.merge(rent);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    @Override
    public LocalDate getLastUpdatedDate(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                String hql = "SELECT r.updateDate FROM Rent r WHERE r.id = :id";
                Query<java.sql.Timestamp> query = session.createQuery(hql, java.sql.Timestamp.class);
                query.setParameter("id", id);
                java.sql.Timestamp updateDate = query.uniqueResult();
                transaction.commit();
                if (updateDate != null) {
                    LocalDateTime localDateTime = updateDate.toLocalDateTime();
                    return localDateTime.toLocalDate();
                } else {
                    return null;
                }
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    @Override
    public LocalDate getCreatedDate(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                String hql = "SELECT r.createDate FROM Rent r WHERE r.id = :id";
                Query<java.sql.Timestamp> query = session.createQuery(hql, java.sql.Timestamp.class);
                query.setParameter("id", id);
                java.sql.Timestamp createDate = query.uniqueResult();
                transaction.commit();
                if (createDate != null) {
                    LocalDateTime localDateTime = createDate.toLocalDateTime();
                    return localDateTime.toLocalDate();
                } else {
                    return null;
                }
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    @Override
    public boolean setOverdueAmt(double overdueAmt, String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                String hql = "UPDATE Rent r SET r.overdueAmt = :overdueAmt WHERE r.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("overdueAmt", overdueAmt);
                query.setParameter("id", id);
                int updatedRows = query.executeUpdate();
                transaction.commit();
                return updatedRows > 0;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }
}
