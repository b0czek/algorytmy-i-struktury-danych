
import java.util.Iterator;
import java.util.ListIterator;

// metody z parametrem index rzucaj¹ wyj¹tkiem IndexOutOfBoundException
// w przypadku b³êdu zakresu jego wartoci

public interface IList<E> extends Iterable<E> {
    // dodanie do kolekcji, gdzie - zale¿y od typu kolekcji
    // zwraca true, jeli element zosta³ dodany
    boolean add(E value);
    // dodanie do kolekcji na okrelon¹ pozycjê
    // zwraca wynika jak dla poprzedniego add
    boolean add(int index, E value);
    // czyci kolekcjê
    void clear();
    // zwraca, czy kolekcja zawiera podan¹ wartoæ
    boolean contains(E value);
    // pobiera (bez usuwania) wartoc spod podanej pozycji
    E get(int index);
    // ustawia now¹ wartoc na podanej pozycji, zwraca star¹ wartoæ
    E set(int index, E value);
    // wzraca pozycjê podanej wartoci lub -1
    int	indexOf(E value);
    // zwraca, czy kolekcja jest pusta
    boolean	isEmpty();
    // zwraca zwyk³y iterator dla tej kolekcji
    Iterator<E>	iterator();
    // zwraca dwukierunkowy iterator dla listy
    ListIterator<E>	listIterator();
    // usuwa element z podaje pozycji, zwracaj¹c jego wartoæ
    // lub null jeli go nie ma
    E remove(int index);
    // usuwa wartoæ oraz zwraca true lub zwraca false
    boolean	remove(E value);
    // zwraca liczbê elementów kolekcji
    int size();
}
