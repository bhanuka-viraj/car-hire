    package lk.ijse.carhire.dao.custom.impl;

    import lk.ijse.carhire.dao.custom.CustomerDao;
    import lk.ijse.carhire.dbHibernate.HibernateUtil;
    import lk.ijse.carhire.entity.customer.Customer;
    import org.hibernate.Session;
    import org.hibernate.SessionFactory;
    import org.hibernate.Transaction;
    import org.hibernate.query.Query;

    import java.time.LocalDate;
    import java.time.ZoneId;
    import java.util.List;

    public class CustomerDaoImpl implements CustomerDao {
        private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


        @Override
        public boolean save(Customer customer) {
            System.out.println("Dao save method called");
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();

                try {
                    session.persist(customer);

                    transaction.commit();

                    return true;
                } catch (Exception e) {
                    throw e;
                }
            }


        }
        @Override
        public boolean update(Customer customer) {
            System.out.println("Dao update method called");
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();

                try {
                    session.merge(customer);

                    transaction.commit();

                    return true;
                } catch (Exception e) {
                    throw e;
                }
            }


        }

        @Override
        public List<Customer> getAll(){
            try (Session session = sessionFactory.openSession();) {
                Transaction transaction = session.beginTransaction();
                try {
                    String hql = "FROM Customer";
                    Query<Customer> query = session.createQuery(hql, Customer.class);

                    List<Customer> customerList = query.list();

                    transaction.commit();
                    return customerList;
                } catch (Exception e) {
                    throw e;
                }


            }


        }

        @Override
        public Customer get(String nic) {
            try (Session session = sessionFactory.openSession();) {
                Transaction transaction = session.beginTransaction();
                try {
                    Customer customer = session.get(Customer.class, nic);

                    transaction.commit();

                    if (customer!=null){
                        return customer;
                    }else {
                        return null;
                    }

                } catch (Exception e) {
                    throw e;
                }
            }


        }

        @Override
        public boolean delete(String nic){
            try (Session session = sessionFactory.openSession();) {
                Transaction transaction = session.beginTransaction();
                try {
                    Customer customer = session.get(Customer.class, nic);

                    if (customer != null) {
                        session.remove(customer);
                        transaction.commit();
                        return true;
                    } else {
                        return false;
                    }

                } catch (Exception e) {
                    transaction.rollback();
                    throw e;
                }
            }



        }

        @Override
        public LocalDate registrationDate(String nic) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();

                try {
                    Customer customer=get(nic);

                    transaction.commit();

                    return customer.getCreateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                } catch (Exception e) {
                    throw e;
                }
            }
        }

    }
