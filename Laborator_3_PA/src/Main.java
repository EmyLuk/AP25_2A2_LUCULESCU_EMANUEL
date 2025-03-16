import java.util.*;
import java.time.LocalTime;


public class Main {
    public interface PassengerCapable{
        int getCapacity();
    }

   public interface CargoCapable{
        double getMaxCapacity();
    }
    abstract class Aircraft implements Comparable<Aircraft>{
        private String name;
        public Aircraft(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
        @Override
        public int compareTo(Aircraft o) {
            return this.name.compareTo(o.name);
        }
    }
     public class Airlainer extends Aircraft implements PassengerCapable{
        private int capacity;
        public Airlainer(String name, int capacity){
            super(name);
            this.capacity = capacity;
        }
        @Override
         public int getCapacity() {
            return capacity;
        }
    }
   public class Freighter extends Aircraft implements CargoCapable{
        private double capacityMax;
        public Freighter(String name, double capacityMax){
            super(name);
            this.capacityMax = capacityMax;
        }
        @Override
        public double getMaxCapacity() {
            return capacityMax;
        }
    }
    public class Drone extends Aircraft implements CargoCapable{
        private double capacity;
        public Drone(String name, double capacity){
            super(name);
            this.capacity = capacity;
        }
        @Override
        public double getMaxCapacity() {
            return capacity;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();

        Aircraft[] aircrafts = {main.new Airlainer("Wizzair ", 150),
                main.new Freighter("Boeing 747 Cargo ", 137),
                main.new Drone("Amazon ", 5),
                main.new Airlainer("Rynair ", 150),
                main.new Freighter("Emirates ", 137),};

        Arrays.sort(aircrafts);

        CargoCapable[] cargoAircrafts = new CargoCapable[aircrafts.length];
        int counter = 0;
        for(Aircraft aircraft : aircrafts){
            if(aircraft instanceof CargoCapable){
                cargoAircrafts[counter++] = (CargoCapable)aircraft;
            }
        }
        System.out.println("Aeronavele sunt: ");
        for(Aircraft aircraft : aircrafts){
            System.out.println(aircraft.getName());

        }
        System.out.println("\n");

        System.out.println("Aeronavele capabile de transport de marfa sunt : ");
        for(int i = 0; i < counter; i++){
            Aircraft aircraft = (Aircraft)cargoAircrafts[i];
            System.out.println(aircraft.getName()+ "Capacitatea Cargo : " + cargoAircrafts[i].getMaxCapacity());
        }

        homework hw = new homework();
        homework.Airport airport = new homework.Airport("Otopeni", 3);
        homework.FlightScheduler scheduler = new homework.FlightScheduler();

        List<homework.Flight> flights = Arrays.asList(
                new homework.Flight("RO100", LocalTime.of(10, 0), LocalTime.of(10, 30)),
                new homework.Flight("RO101", LocalTime.of(12, 10), LocalTime.of(12, 45)),
                new homework.Flight("RO102", LocalTime.of(11, 0), LocalTime.of(11, 30)),
                new homework.Flight("RO103", LocalTime.of(10, 35), LocalTime.of(11, 5)),
                new homework.Flight("RO104", LocalTime.of(10, 0), LocalTime.of(10, 30)),
                new homework.Flight("RO105", LocalTime.of(10, 15), LocalTime.of(10, 45)),
                new homework.Flight("RO106", LocalTime.of(15, 0), LocalTime.of(15, 30)),
                new homework.Flight("RO107", LocalTime.of(12, 35), LocalTime.of(13, 5))
        );

        // Programeaza zborurile
        scheduler.scheduleFlights(airport, flights);

        // Afișeaza programarea finala
        scheduler.printSchedule();
    }
}
