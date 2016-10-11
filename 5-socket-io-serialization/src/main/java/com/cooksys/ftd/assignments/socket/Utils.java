package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Shared static methods to be used by both the {@link Client} and
 * {@link Server} classes.
 */
public class Utils {
	/**
	 * @return a {@link JAXBContext} initialized with the classes in the
	 *         com.cooksys.socket.assignment.model package
	 * @throws JAXBException
	 */
	public static JAXBContext createJAXBContext() {
		JAXBContext jaxb = null;
		try {
			jaxb = JAXBContext.newInstance(Student.class, Config.class);
		} catch (JAXBException e) {
			System.out.println("Failed to create context");
			System.exit(0);
		}
		return jaxb;
	}

	/**
	 * Reads a {@link Config} object from the given file path.
	 *
	 * @param configFilePath
	 *            the file path to the config.xml file
	 * @param jaxb
	 *            the JAXBContext to use
	 * @return a {@link Config} object that was read from the config.xml file
	 * @throws JAXBException
	 */
	public static Config loadConfig(String configFilePath, JAXBContext jaxb) {
		Config config = null;
		try {
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			File configFile = new File(configFilePath);
			config = (Config) jaxbUnmarshaller.unmarshal(configFile);
		} catch (JAXBException e) {
			System.out.println("Could not load config!");
			System.exit(0);
		}
		return config;
	}

	public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
		File file = new File(studentFilePath);
		Student student = (Student) jaxbUnmarshaller.unmarshal(file);
		return student;
	}
}
