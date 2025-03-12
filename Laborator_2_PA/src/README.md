Salut, in folderul Laboratorul_2_PA in src vor fi 3 fisiere .java, unul Compulsory.java, unul Main.java si unul Bonus.java.
In Compulsory.java am implementat rezolvarea pentru compulsory pe care am prezentat-o ora trecuta:
In Main.java am implementat cerintele pentru homework .
In ce consta implementarea? Homework:->creez o superclasa Person ce va fi un model comun pentru student si profesor, 					      continand un nume si o data de nastere.
                                     -> Clasa Student ce contine in plus un nr de inregistrare, fiecare student poate avea 					alocat un singur proiect                                     
                                     -> Clasa Teacher avand proiecte propuse pentru stuenti
                                     -> Clasa Project unde fiecare proiect are un titlu si un tip(teoretic sau practic) si care poate fi alocat sau nu unui student.
                                     -> Clasa Problem, clasa principala care gestioneaza lista de student si profesori, apoi implementeaza cu ajutorul alg Greedy, un mod de distribuire a proiectelor.
                            Main :  -> In main creez instante, adaug elementele in sistem, si rulez alg Greedy afisand rezultatele.

-> parcurg lista de student si incerc sa aloc fiecarui student un proiect
-> pentru fiecare student, verificam profesorii si lista de proiecte. 
-> daca un proiect nu este alocat, il atribuim studentului.
-> continuam pana cand fiecare student  primeste un proiect. ( daca sunt destule proiecte pentru fiecare, daca nu, ceilalti nu vor avea proiecte).
Compulsory si tema am prezentat-o online, am adaugat acum si partea de bonus.
In ce consta bonusul: studentii reprezinta un set de noduri in graf iar profesori alt set de noduri.
-> exista muchie intre un student si un proiect daca acel proiect este disponibil pentru acel student.
-> Am folosit parcurgerea DFS pentru a explora posibilitatea de a aloca fiecare student unui proiect disponibil.
->Daca parcurgerea DFS reuseste sa gaseasca o potrivire perfecta, atunci conditia teoremei lui Hall este respectata: (daca pt o submultime S de studenti , nr total de proiecte pentru acei studenti este >= |S|).
