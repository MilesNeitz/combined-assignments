package com.cooksys.ftd.assignments.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client extends Utils {

    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     * @throws JAXBException 
     * @throws IOException 
     * @throws UnknownHostException 
     */
    public static void main(String[] args) throws JAXBException, UnknownHostException, IOException {
    	
    	MyRunnable myRun = new MyRunnable();
    	
    	new Thread(myRun).start();
    	
    	JAXBContext jaxb = createJAXBContext();
    	Config config = loadConfig("config/config.xml",jaxb);
		RemoteConfig remoteConfig = config.getRemote();

		Socket s = new Socket(remoteConfig.getHost(), remoteConfig.getPort());
		System.out.println("Contected");
		
		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
		Student student = (Student) jaxbUnmarshaller.unmarshal(s.getInputStream());
		
		System.out.println(student);
		
		s.close();
    }
}
