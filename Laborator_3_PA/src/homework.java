import java.time.LocalTime;
import java.util.*;

public class homework {
    /**
     * Clasa care reprezinta un zbor cu un interval de aterizare.
     */
    public static class Flight {
        private String flightNumber;
        private LocalTime landingStart;
        private LocalTime landingEnd;

        public Flight(String flightNumber, LocalTime landingStart, LocalTime landingEnd) {
            this.flightNumber = flightNumber;
            this.landingStart = landingStart;
            this.landingEnd = landingEnd;
        }

        public String getFlightNumber() {
            return flightNumber;
        }

        public LocalTime getLandingStart() {
            return landingStart;
        }

        public LocalTime getLandingEnd() {
            return landingEnd;
        }

        @Override
        public String toString() {
            return "Flight " + flightNumber + " (" + landingStart + " - " + landingEnd + ")";
        }
    }

    /**
     * Clasa care reprezinta o pista de aterizare.
     * O pista poate avea mai multe zboruri programate.
     */
    public static class Runway {
        private String id;
        private List<Flight> scheduledFlights;

        public Runway(String id) {
            this.id = id;
            this.scheduledFlights = new ArrayList<>();
        }

        public String getId() {
            return id;
        }

        /**
         * Verifica daca pista este libera pentru un anumit zbor.
         * @param flight Zborul care trebuie verificat.
         * @return true dacă pista este disponibila, altfel false.
         */
        public boolean isAvailable(Flight flight) {
            for (int i = 0; i < scheduledFlights.size(); i++) {
                Flight scheduled = scheduledFlights.get(i);

                boolean before = flight.getLandingEnd().isBefore(scheduled.getLandingStart());
                boolean after = flight.getLandingStart().isAfter(scheduled.getLandingEnd());

                if (!before && !after) {
                    return false; // Suprapunere de intervale
                }
            }
            return true;
        }

        /**
         * Adaugă un zbor pe pista.
         * @param flight Zborul care va fi programat pe aceasta pista.
         */
        public void scheduleFlight(Flight flight) {
            scheduledFlights.add(flight);
        }

        public List<Flight> getScheduledFlights() {
            return scheduledFlights;
        }

        @Override
        public String toString() {
            return "Runway " + id + " -> " + scheduledFlights;
        }
    }

    /**
     * Clasa care reprezintă un aeroport cu mai multe piste de aterizare.
     */
    public static class Airport {
        private String name;
        private List<Runway> runways;

        public Airport(String name, int numberOfRunways) {
            this.name = name;
            this.runways = new ArrayList<>();

            // Inițializăm pistele aeroportului
            for (int i = 1; i <= numberOfRunways; i++) {
                runways.add(new Runway("Pista" + i));
            }
        }

        public String getName() {
            return name;
        }

        public List<Runway> getRunways() {
            return runways;
        }
    }

    /**
     * Clasa pentru gestionarea programarii zborurilor utilizand map.
     */
    public static class FlightScheduler {
        private Map<Flight, Runway> flightAssignments;

        public FlightScheduler() {
            this.flightAssignments = new HashMap<>();
        }

        /**
         * Programeaza zborurile pe piste utilizand Map pentru a le asocia corect.
         * @param airport Aeroportul unde se va face programarea.
         * @param flights Lista zborurilor care trebuie programate.
         */
        public void scheduleFlights(Airport airport, List<Flight> flights) {
            for (int i = 0; i < flights.size(); i++) {
                Flight flight = flights.get(i);
                boolean scheduled = false;

                for (int j = 0; j < airport.getRunways().size(); j++) {
                    Runway runway = airport.getRunways().get(j);

                    if (runway.isAvailable(flight)) {
                        runway.scheduleFlight(flight);
                        flightAssignments.put(flight, runway); // Punem zborul la pista
                        scheduled = true;
                        break;
                    }
                }

                if (!scheduled) {
                    System.out.println("Nu s-a putut programa " + flight.getFlightNumber());
                }
            }
        }

        /**
         * Afișeaza programarea finala a zborurilor.
         */
        public void printSchedule() {
            System.out.println("\nProgramarea finala a zborurilor:");
            List<Map.Entry<Flight, Runway>> entries = new ArrayList<>(flightAssignments.entrySet());

            for (int i = 0; i < entries.size(); i++) {
                Map.Entry<Flight, Runway> entry = entries.get(i);
                System.out.println(entry.getKey() + " -> " + entry.getValue().getId());
            }
        }
    }
}
