package softuni.exam.service;



import softuni.exam.models.entities.PassengerEntity;

import java.io.IOException;

//ToDo - Before start App implement this Service and set areImported to return false
public interface PassengerService {

    boolean areImported();

    String readPassengersFileContent() throws IOException;
	
	String importPassengers() throws IOException;

	String getPassengersOrderByTicketsCountDescendingThenByEmail();

    PassengerEntity findByEmail(String email);
}