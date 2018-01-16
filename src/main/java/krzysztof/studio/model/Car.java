package krzysztof.studio.model;

import krzysztof.studio.validation.*;
import krzysztof.studio.validation.Enum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @Column(name = "car_vin")
    @NotNull
    private String vin; // vehicle identification number
    @NotNull
    @Enum(enumClass = MakeEnum.class, ignoreCase = true)
    @Column(name = "car_make")
    private String make;
    @Column(name = "car_model")
    private String model;
    @NotNull
    @Size(max = 10)
    @RegistrationNumber
    @Column(name = "car_registration_number")
    private String registrationNumber;
    @Column(name = "car_seats")
    private Integer nrOfSeats;
    @NotNull
    @InRange(
            min = 50,
            max = 6999,
            message = "minimum capacity is 50, maximum 6999"
    )
    @Column(name = "car_capacity")
    private Integer cylinderCapacity;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @InDateRange
    @Column(name = "car_date_first_registration")
    private Date dateOfFirstRegistration;
    @NotNull
    @InDateRange
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "car_date_registration")
    private Date dateOfRegistration;
    @ManyToOne
    @JoinColumn(name = "customer_pesel")
    private Car car;

    public Car() {

    }

    public Car(String vin, String make, String model) {
        this.vin = vin;
        this.make = make;
        this.model = model;
    }

    public Car(String vin, String make, String model, String registrationNumber, Integer nrOfSeats, Integer cylinderCapacity, Date dateOfFirstRegistration, Date dateOfRegistration, Car car) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.nrOfSeats = nrOfSeats;
        this.cylinderCapacity = cylinderCapacity;
        this.dateOfFirstRegistration = dateOfFirstRegistration;
        this.dateOfRegistration = dateOfRegistration;
        this.car = car;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car1 = (Car) o;

        if (vin != null ? !vin.equals(car1.vin) : car1.vin != null) return false;
        if (make != null ? !make.equals(car1.make) : car1.make != null) return false;
        if (model != null ? !model.equals(car1.model) : car1.model != null) return false;
        if (registrationNumber != null ? !registrationNumber.equals(car1.registrationNumber) : car1.registrationNumber != null)
            return false;
        if (nrOfSeats != null ? !nrOfSeats.equals(car1.nrOfSeats) : car1.nrOfSeats != null) return false;
        if (cylinderCapacity != null ? !cylinderCapacity.equals(car1.cylinderCapacity) : car1.cylinderCapacity != null)
            return false;
        if (dateOfFirstRegistration != null ? !dateOfFirstRegistration.equals(car1.dateOfFirstRegistration) : car1.dateOfFirstRegistration != null)
            return false;
        if (dateOfRegistration != null ? !dateOfRegistration.equals(car1.dateOfRegistration) : car1.dateOfRegistration != null)
            return false;
        return car != null ? car.equals(car1.car) : car1.car == null;
    }

    @Override
    public int hashCode() {
        int result = vin != null ? vin.hashCode() : 0;
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
        result = 31 * result + (nrOfSeats != null ? nrOfSeats.hashCode() : 0);
        result = 31 * result + (cylinderCapacity != null ? cylinderCapacity.hashCode() : 0);
        result = 31 * result + (dateOfFirstRegistration != null ? dateOfFirstRegistration.hashCode() : 0);
        result = 31 * result + (dateOfRegistration != null ? dateOfRegistration.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        return result;
    }
}