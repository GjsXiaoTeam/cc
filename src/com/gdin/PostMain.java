package com.gdin;

import javax.xml.ws.Endpoint;

import com.iloosen.imall.module.bigdata.service.GdinService;
import com.iloosen.imall.service.bigdata.IGdinService;

public class PostMain {
	public static void main(String[] args) {
		IGdinService send = new GdinService();
		//http://39.108.172.156:8080/webservice/gdin?wsdl 
		//ip 172.18.224.17
		//http://192.168.40.34:8080/webservice/gdin?wsdl
		//��¶����ip  ����ʱ������ip
        Endpoint.publish("http://192.168.40.34:8080/webservice/gdin", send);
        System.out.println("192.168.40.34 send success");
	}
}
