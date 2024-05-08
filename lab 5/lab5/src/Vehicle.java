abstract class Vehicle {
    private String model;
    private double fuelUsagePer100km; // паливо на 100 км

    public Vehicle(String model, double fuelUsagePer100km) {
        this.model = model;
        this.fuelUsagePer100km = fuelUsagePer100km;
    }

    public String getModel() {
        return model;
    }

    public double getFuelUsagePer100km() {
        return fuelUsagePer100km;
    }

    public double calculateFuelNeeds(double distance) {
        return (fuelUsagePer100km * distance) / 100;
    }

    public abstract double calculateCostPerKm(double fuelPrice);
}

// Класи для конкретних транспортних засобів
class Car extends Vehicle {
    private int seats;

    public Car(String model, double fuelUsagePer100km, int seats) {
        super(model, fuelUsagePer100km);
        this.seats = seats;
    }

    @Override
    public double calculateCostPerKm(double fuelPrice) {
        return (getFuelUsagePer100km() * fuelPrice) / 100;
    }
}

class Truck extends Vehicle {
    private double cargoCapacity;

    public Truck(String model, double fuelUsagePer100km, double cargoCapacity) {
        super(model, fuelUsagePer100km);
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double calculateCostPerKm(double fuelPrice) {
        return (getFuelUsagePer100km() * fuelPrice) / 70; // Припустимо, вантажівки ефективніші з паливом на товарний км
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String model, double fuelUsagePer100km) {
        super(model, fuelUsagePer100km);
    }

    @Override
    public double calculateCostPerKm(double fuelPrice) {
        return (getFuelUsagePer100km() * fuelPrice) / 150; // Мотоцикли економніші
    }
}
