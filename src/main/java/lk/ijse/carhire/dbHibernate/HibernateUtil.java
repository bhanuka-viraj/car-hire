package lk.ijse.carhire.dbHibernate;

import lk.ijse.carhire.entity.car.Car;
import lk.ijse.carhire.entity.car.CarCategory;
import lk.ijse.carhire.entity.car.Rent;
import lk.ijse.carhire.entity.customer.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .loadProperties("lk/ijse/carhire/config/Application.properties")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(CarCategory.class)
                .addAnnotatedClass(Rent.class)
                .addAnnotatedClass(Car.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        return sessionFactory;
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
