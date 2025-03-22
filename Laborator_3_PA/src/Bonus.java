import java.time.LocalTime;
import java.util.*;

public class Bonus {
    private static final int MAX_DELAY_MINUTES = 30; // maxim 30 minute intarziere
    private static final int DELAY_STEP = 5;         // pasul de intarziere (5 minute)

    /**
     * Programez zborurile astfel incat pistele sa fie folosite echitabil
     */
    public static void equitableScheduleWithDelays(List<homework.Flight> flights, homework.Airport airport) {
        Map<homework.Runway, List<homework.Flight>> schedule = new HashMap<>();

        // initializez mapul cu piste goale
        for (homework.Runway runway : airport.getRunways()) {
            schedule.put(runway, new ArrayList<>());
        }

        // sortez zborurile dupa ora de inceput
        flights.sort(Comparator.comparing(homework.Flight::getLandingStart));

        // incerc sa aloc fiecare zbor unei piste disponibile
        for (homework.Flight flight : flights) {
            boolean scheduled = false;
            int delay = 0;

            // incerc programarea zborului cu intarzieri pana la 30 minute
            while (delay <= MAX_DELAY_MINUTES && !scheduled) {
                LocalTime newStart = flight.getLandingStart().plusMinutes(delay);
                LocalTime newEnd = flight.getLandingEnd().plusMinutes(delay);
                homework.Flight delayedFlight = new homework.Flight(flight.getFlightNumber(), newStart, newEnd);

                // sortez pistele dupa numarul de zboruri programate
                List<homework.Runway> sortedRunways = new ArrayList<>(airport.getRunways());
                sortedRunways.sort(Comparator.comparingInt(r -> r.getScheduledFlights().size()));

                // caut prima pista disponibila
                for (homework.Runway runway : sortedRunways) {
                    if (runway.isAvailable(delayedFlight)) {
                        runway.scheduleFlight(delayedFlight);
                        schedule.get(runway).add(delayedFlight);
                        scheduled = true;
                        break;
                    }
                }

                delay += DELAY_STEP;
            }

            if (!scheduled) {
                System.out.println("Zborul " + flight.getFlightNumber() + " nu a putut fi programat nici macar cu intarziere.");
            }
        }

        // afisez programarea finala
        System.out.println("\n[Programare echitabila cu intarzieri daca este nevoie]");
        for (homework.Runway runway : airport.getRunways()) {
            System.out.println(runway.getId() + " are " + runway.getScheduledFlights().size() + " zboruri:");
            for (homework.Flight f : runway.getScheduledFlights()) {
                System.out.println("  " + f);
            }
        }

        // verific daca programarea este echitabila
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (homework.Runway runway : airport.getRunways()) {
            int size = runway.getScheduledFlights().size();
            min = Math.min(min, size);
            max = Math.max(max, size);
        }

        if (max - min <= 1) {
            System.out.println("\nProgramare echitabila realizata!");
        } else {
            System.out.println("\nNu s-a reusit programarea echitabila.");
        }
    }
}
