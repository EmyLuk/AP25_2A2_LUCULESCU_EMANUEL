Salut, in folderul Laboratorul_3_PA in src vor fi 2 fisiere .java, unul Main.java si unul homework.java. 
In Main.java am implementat rezolvarea pentru compulsory pe care am prezentat-o ora trecuta:
In homework.java am implementat cerintele pentru homework .
In ce consta implementarea? Homework:-> clasa Flight reprezinta un zbor care contine un nr-ul zborului, ora de inceput a aterizatii si ora de sfarsit. Acesta sunt implementate prin diferite metode.
                                     ->clasa Runway reprezinta pista de aterizare, unde sunt programate zborurile. 
                                            -> isAvailable(Flight flight) : verifica daca pista este disponibila
                                            ->scheduleFlight(Flight flight): adauga un zbor pe pista
                                            ->getScheduledFlights()->returneaza lista de zboruri programata
                                     ->clasa Airport contine mai multe piste de aterizare
                                     ->clasa FlightScheduler: aceasta clasa gestioneaza alocarea zborurilor pe pistele 
                                                              disponibile, folosind un Map<Flight,Runway> pentru a asocia fiecare 
                                                              zbor pe o pista.
                                     -> Metoda: scheduleFlights(Airport airport, List<Flight> flights): asociaza fiecare zbor unei piste
                                                 disponibile, evitand suprapunerile. (doua avioane nu au cum sa aterizeze pe aceasi 
                                                 pista in intervale ce se suprapun).
                                                    