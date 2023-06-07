package Przedszkole;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Przedszkole {
    private static ArrayList<Dziecko> dzieci = new ArrayList<>();
    @Override
    public String toString() {
        String str = "";
        for(Dziecko dziecko : dzieci) {
            str += dziecko.getName() + " zna ";
            str += dzieci
                    .stream()
                    .filter(dziecko1 -> dziecko1 != dziecko && dziecko1.czyZna(dziecko))
                    .map(dziecko1 -> dziecko1.getName())
                    .collect(Collectors.joining(", "));
            str +="\n";
        }
        return str;

    }
    public static void main(String[] args){
        Przedszkole przedszkole = new Przedszkole();
        Dziecko michal = new Dziecko("michal");
        Dziecko krzys = new Dziecko("krzys");
        Dziecko dominik = new Dziecko("dominik");
        Dziecko igor = new Dziecko("igor");
        Dziecko anastazja = new Dziecko("anastazja");

        dzieci.add(michal);
        dzieci.add(krzys);
        dzieci.add(dominik);
        dzieci.add(igor);
        dzieci.add(anastazja);


        dominik.zapoznaj(igor);
        igor.zapoznaj(anastazja);

        System.out.println(przedszkole);


        System.out.println("czy michal zna krzysia: "+ michal.czyZna(krzys));
        michal.zapoznaj(krzys);
        System.out.println("czy michal zna krzysia: "+ michal.czyZna(krzys));

        System.out.println(przedszkole);


        igor.zapoznaj(krzys);
        System.out.println(przedszkole);

    }
}
