package pl.mirek.springtrainingweek3hw.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.mirek.springtrainingweek3hw.model.Car;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository cars;
    Logger logger = LoggerFactory.getLogger(CarService.class);

    @Autowired
    public CarService(CarRepository cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

    public Car getCarById(long id) {
        Optional<Car> first = cars.getCars().stream().filter(car -> car.getId() == id).findFirst();
        if (first.isPresent()) {
            return first.get();
        }
        return null;
    }

    public List<Car> getCarsByColor(String color) {
        List<Car> allSelectedCars = cars.getCars().stream().filter(car -> car.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
        if (allSelectedCars.size() > 0) {
            return allSelectedCars;
        }
        logger.warn("---> there are no cars on the list");
        return null;
    }

    public Boolean addCar(Car car) {
        return cars.getCars().add(car);
    }

    public Boolean deleteCar(Car car) {
        return cars.getCars().remove(car);
    }

    public Boolean modifyField(Car car, String field, String value) {

        switch (field) {
            case "mark":
                car.setMark(value);
                return true;
            case "model":
                car.setModel(value);
                return true;
            case "color":
                car.setColor(value);
                return true;
            default:
                return false;
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeRepository() {
        addCar(new Car(1L, "Mercedes", "GLK", "beige"));
        addCar(new Car(2L, "Citroen", "C3", "RED"));
        addCar(new Car(3L, "Fiat", "Panda", "red"));
        System.out.println("---> CarService is ready");
    }
}
