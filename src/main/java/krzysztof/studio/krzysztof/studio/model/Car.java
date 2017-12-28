package krzysztof.studio.krzysztof.studio.model;

public class Car {

    String id;
    String vehicleBrand;
    String model;
    int modelYear;
    String registrationNumber;

    public Car(String id, String vehicleBrand, String model, int modelYear, String registrationNumber) {
        this.id = id;
        this.vehicleBrand = vehicleBrand;
        this.model = model;
        this.modelYear = modelYear;
        this.registrationNumber = registrationNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }
}