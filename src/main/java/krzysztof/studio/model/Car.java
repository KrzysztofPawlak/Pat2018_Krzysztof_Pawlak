package krzysztof.studio.model;

import krzysztof.studio.validation.*;
import krzysztof.studio.validation.Enum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Car {

    @Id
    private String vin; // vehicle identification number
    @NotNull
    @Enum(enumClass = MakeEnum.class, ignoreCase = true)
    private String make;
    private String model;
    @NotNull
    @Size(max = 10)
    @RegistrationNumber
    private String registrationNumber;
    private Integer nrOfSeats;
    @NotNull
    @InRange(
            min = 50,
            max = 6999,
            message = "minimum capacity is 50, maximum 6999"
    )
    private Integer cylinderCapacity;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @InDateRange
    private Date dateOfFirstRegistration;
    @NotNull
    @InDateRange
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfRegistration;

    public Car() {

    }

    public Car(String vin, String make, String model) {
        this.vin = vin;
        this.make = make;
        this.model = model;
    }

    public Car(String vin, String make, String model, String registrationNumber, Integer nrOfSeats, Integer cylinderCapacity, Date dateOfFirstRegistration, Date dateOfRegistration, Customer customer) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.nrOfSeats = nrOfSeats;
        this.cylinderCapacity = cylinderCapacity;
        this.dateOfFirstRegistration = dateOfFirstRegistration;
        this.dateOfRegistration = dateOfRegistration;
    }

    public Integer getNrOfSeats() {
        return nrOfSeats;
    }

    public void setNrOfSeats(Integer nrOfSeats) {
        this.nrOfSeats = nrOfSeats;
    }

    public Integer getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(Integer cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public Date getDateOfFirstRegistration() {
        return dateOfFirstRegistration;
    }

    public void setDateOfFirstRegistration(Date dateOfFirstRegistration) {
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}