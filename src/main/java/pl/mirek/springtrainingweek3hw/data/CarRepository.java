package pl.mirek.springtrainingweek3hw.data;

import org.springframework.stereotype.Repository;
import pl.mirek.springtrainingweek3hw.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    private List<Car> cars;

    public CarRepository() {
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        return cars;
    }
}
