package softuni.exam.service;



import softuni.exam.models.entities.CarEntity;

import java.io.IOException;

//ToDo - Before start App implement this Service and set areImported to return false
public interface CarService {

    boolean areImported();

    String readCarsFileContent() throws IOException;
	
	String importCars() throws IOException;

    String getCarsOrderByPicturesCountThenByMake();

    CarEntity findCarById(int car);
}