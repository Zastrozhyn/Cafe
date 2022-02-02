package by.zastr.cafe.controller.listener;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.cafe.model.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * The type Context listener.
 *
 * @see ContextEvent
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger();


    /**
     * Context destroyed.
     *
     * @param sce the sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
        logger.info("ConnectionPool was destroyed");
    }

}
