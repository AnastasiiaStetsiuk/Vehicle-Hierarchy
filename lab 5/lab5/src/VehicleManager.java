import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class VehicleManager {
    private JFrame frame;
    private JTextArea textArea;
    private List<Vehicle> vehicles;

    public VehicleManager() {
        frame = new JFrame("Vehicle Manager");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton addCarButton = new JButton("Add Car");
        addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCar();
            }
        });
        panel.add(addCarButton);

        JButton addTruckButton = new JButton("Add Truck");
        addTruckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTruck();
            }
        });
        panel.add(addTruckButton);

        JButton addMotorcycleButton = new JButton("Add Motorcycle");
        addMotorcycleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMotorcycle();
            }
        });
        panel.add(addMotorcycleButton);

        JButton searchBySpecializationButton = new JButton("Search by Specialization");
        searchBySpecializationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBySpecialization();
            }
        });
        panel.add(searchBySpecializationButton);

        JButton searchByTypeButton = new JButton("Search by Fuel");
        searchByTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByFuel();
            }
        });
        panel.add(searchByTypeButton);

        JButton displayAllVehicleButton = new JButton("Display All Vehicle");
        displayAllVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllVehicle();
            }
        });
        panel.add(displayAllVehicleButton);

        JButton clearAllVehicleButton = new JButton("Clear All Vehicle");
        clearAllVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllVehicle();
            }
        });
        panel.add(clearAllVehicleButton);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.add(panel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);

        vehicles = loadVacanciesFromFile("vehicle.dat");
    }

    private void addCar() {
        String model = JOptionPane.showInputDialog(frame, "Enter model:");
        //String description = JOptionPane.showInputDialog(frame, "Enter job description:");
        double fuelUsagePer100Km = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter fuel usage:"));
        int seats = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter seats number:"));

        Car vehicle = new Car(model, fuelUsagePer100Km, seats);
        vehicles.add(vehicle);
        textArea.append("Car added successfully.\n");
        saveVehicleToFile("vehicles.dat", vehicles);
    }

    private void addTruck() {
        String model = JOptionPane.showInputDialog(frame, "Enter model:");
        //String description = JOptionPane.showInputDialog(frame, "Enter job description:");
        double fuelUsagePer100Km = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter fuel usage:"));
        int cargoCapacity = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter cargo Capacity:"));

        Truck vehicle = new Truck(model, fuelUsagePer100Km, cargoCapacity);
        vehicles.add(vehicle);
        textArea.append("Truck added successfully.\n");
        saveVehicleToFile("vehicles.dat", vehicles);
    }

    private void addMotorcycle() {
        String model = JOptionPane.showInputDialog(frame, "Enter model:");
        //String description = JOptionPane.showInputDialog(frame, "Enter job description:");
        double fuelUsagePer100Km = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter fuel usage:"));
        //int hours = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter total hours:"));

        Motorcycle vehicle = new Motorcycle(model, fuelUsagePer100Km);
        vehicles.add(vehicle);
        textArea.append("Truck added successfully.\n");
        saveVehicleToFile("vehicle.dat", vehicles);
    }

    private void searchBySpecialization() {
        // Запит введення спеціалізації від користувача
        String specialization = JOptionPane.showInputDialog(frame, "Enter specialization to search:");

        // Перевірка чи було щось введено користувачем
        if (specialization == null || specialization.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Search field cannot be empty.");
            return;
        }

        textArea.setText("");
        boolean found = false;

        // Перебір усіх транспортних засобів у списку
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getModel().toLowerCase().contains(specialization.toLowerCase())) {
                textArea.append(vehicle.getModel() + " - " + vehicle.getFuelUsagePer100km() + " L/100km\n");
                found = true;
            }
        }

        // Якщо не знайдено жодного транспортного засобу, що відповідає критеріям
        if (!found) {
            textArea.setText("No vehicles found with specialization: \"" + specialization + "\".");
        }
    }
    private void searchByFuel() {
        // Запит введення споживання палива від користувача
        String input = JOptionPane.showInputDialog(frame, "Enter maximum fuel usage to search:");
        if (input != null && !input.isEmpty()) {
            double maxFuelUsage;
            try {
                maxFuelUsage = Double.parseDouble(input); // Перетворення введеного тексту на число
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                return;
            }

            textArea.setText("");

            // Перебір усіх транспортних засобів у списку
            for (Vehicle vehicle : vehicles) {
                // Додавання до textArea лише тих транспортних засобів, що відповідають критерію
                if (vehicle.getFuelUsagePer100km() <= maxFuelUsage) {
                    textArea.append(vehicle.getModel() + " - " + vehicle.getFuelUsagePer100km() + " L/100km\n");
                }
            }

            // Якщо textArea залишився порожнім після пошуку, вивести повідомлення, що нічого не знайдено
            if (textArea.getText().isEmpty()) {
                textArea.setText("No vehicles found with fuel usage less than or equal to " + maxFuelUsage + " L/100km.");
            }
        }
    }
    private void displayAllVehicle() {
        textArea.setText("");

        for (Vehicle vehicle : vehicles) {
            textArea.append(vehicle.getModel() + " - " + vehicle.getFuelUsagePer100km() + "\n");
        }
    }

    private void clearAllVehicle() {
        vehicles.clear();
        textArea.setText("All vehicle cleared successfully.\n");
        saveVehicleToFile("vehicle.dat", vehicles);
    }

    private List<Vehicle> loadVacanciesFromFile(String filename) {
        List<Vehicle> loadedVehicle= new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            loadedVehicle = (List<Vehicle>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedVehicle;
    }

    private void saveVehicleToFile(String filename, List<Vehicle> vehicles) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(vehicles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        VehicleManager gui = new VehicleManager();
        gui.show();
    }
}
