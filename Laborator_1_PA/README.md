Salut, in folderul Laboratorul_1_PA in src vor fi 2 fisiere .java, unul Laboratorul_1_PA.java si unul homework.java. 
In Laboratorul_1_PA am implementat rezolvarea pentru compulsory pe care am prezentat-o ora trecuta:
In homomework.java  sunt implementate si homework si bonusul.
In ce consta implementarea? Homework:-> generez un graf random in care o clique si sunt set stabil.
                                     -> Verific nr de muchii numarand muchiile de deasupra diagonalei principale, graful fiind neorientat si simetric.
                                     -> suma gradelor ar trebui sa fie 2 * m fiind un graf simetric.
                                     -> Afisez gradul maxim, gradul minim
                                     -> iar cu functia addRandomEdges() adaug muchii aleatorii folosind rand.nextBoolean().
                                     -> se mai implementeaza si problema unui n > 30.000 in care masuram timpul, si afisam o parte din operatii.
                            Bonus:   -> folosesc o functie verifica_clique() ajutandu-ma de backtracking in care caut o clique de lungime >=k
                                     -> In gaseste_clique()verific recursiv daca exista un grup de k-noduri complet conectate.
                                     -> apoi folosind tehnica grafului invers, inversez graful si caut o clique in graful inversat( = un set stabil in graful original)
                                     -> functia inverseaza_graf() deci transforma graful in complemetarul sau.

                            Main :  -> In main fac afisarile, declararea variabilelor, masurarea timpul de executie.