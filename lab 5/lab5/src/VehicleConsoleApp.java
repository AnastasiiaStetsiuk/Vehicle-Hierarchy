
public class VehicleConsoleApp {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter distance (km): ");
        double distance = scanner.nextDouble();
        System.out.print("Enter fuel price (per liter): ");
        double fuelPrice = scanner.nextDouble();

        java.util.List<Vehicle> vehicles = new java.util.ArrayList<>();
        vehicles.add(new Car("Ferrari SF90 Spider", 12.5, 2));
        vehicles.add(new Truck("Volvo FH12", 29.0, 20000));
        vehicles.add(new Motorcycle("Honda CBR 500", 3.8));

        for (Vehicle vehicle : vehicles) {
            double fuelNeeded = vehicle.calculateFuelNeeds(distance);
            double costPerKm = vehicle.calculateCostPerKm(fuelPrice);
            double totalCost = costPerKm * distance;

            System.out.printf("%s: Fuel Needed: %.2f liters, Total Cost: $%.2f%n",
                    vehicle.getModel(), fuelNeeded, totalCost);
        }
    }
}