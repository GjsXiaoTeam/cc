package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.ws.Endpoint;

import com.iloosen.imall.module.bigdata.service.GdinService;
import com.iloosen.imall.service.bigdata.IGdinService;

@WebListener
public class MyListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("hello");

		IGdinService send = new GdinService();
		//http://39.108.172.156:8088/webservice/gdin?wsdl 
		//ip 172.18.224.17
		//http://192.168.40.40:8088/webservice/gdin?wsdl
		//��¶����ip  ����ʱ������ip 192.168.1.134  172.18.224.18
        Endpoint.publish("http://192.168.1.134:8088/webservice/gdin", send);
        System.out.println("192.168.1.134 send success");
	}
}
