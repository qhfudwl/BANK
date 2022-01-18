package banking.db;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class InitializeDataSource implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event)  { 
		ServletContext context = event.getServletContext();
		context.setInitParameter("jdbc-properties", "/WEB-INF/data/jdbc.properties");
		String jdbcFilePath = context.getInitParameter("jdbc-properties");
		InputStream is = null;
		try {
			is = context.getResourceAsStream(jdbcFilePath);
			Properties prop = new Properties();
			prop.load(is);
			
			String jdbcDriver = prop.getProperty("jdbcDriver");
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String userName = prop.getProperty("jdbcUser");
			String password = prop.getProperty("jdbcPassword");
			
			DataSource ds = DataSource.getInstance();
			ds.initDataSource(jdbcDriver, userName, password, jdbcUrl);
			System.out.println("InitializeDataSource is initialized....");
		}catch(Exception e) {
			e.printStackTrace();
		}
    }	
	
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("애플리케이션 종료");
    	
    }

	
    
	
}
