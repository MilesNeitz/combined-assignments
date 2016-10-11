package com.cooksys.ftd.assignments.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

public class MyRunnable extends Utils implements Runnable {

	public void run() {
		System.out.println("Hello from a thread!");

		JAXBContext jaxb = createJAXBContext();
		Config config = loadConfig("config/config.xml", jaxb);
		LocalConfig localConfig = config.getLocal();
		
		try (ServerSocket ss = new ServerSocket(localConfig.getPort()); 
				Socket connectionFromClient = ss.accept();) {

			System.out.println("Waiting for connection ...");
			System.out.println("Connection complete");

			Student student = loadStudent(config.getStudentFilePath(), jaxb);

			Marshaller jaxbMarshaller = jaxb.createMarshaller();
			jaxbMarshaller.marshal(student, connectionFromClient.getOutputStream());

		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

}
