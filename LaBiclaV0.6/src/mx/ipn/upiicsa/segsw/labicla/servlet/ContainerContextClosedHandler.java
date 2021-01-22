package mx.ipn.upiicsa.segsw.labicla.servlet;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;


@WebListener // register it as you wish
public class ContainerContextClosedHandler implements ServletContextListener {
    //private static final Logger logger = LoggerFactory.getLogger(ContainerContextClosedHandler.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // nothing to do
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	System.out.println("ContainerContextClosedHandler.contextDestroyed() - Drivers will be de-registered");
        Enumeration<Driver> drivers = DriverManager.getDrivers();     

        Driver driver = null;

        // clear drivers
        while(drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);

            } catch (SQLException ex) {
                // deregistration failed, might want to do something, log at the very least
            }
        }

        AbandonedConnectionCleanupThread.checkedShutdown();
    }

}