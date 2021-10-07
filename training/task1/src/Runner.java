import Classes.Depot;
import Classes.Train;
import XmlClasses.XmlClass;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws ParserConfigurationException {
        XmlClass xmlClass = new XmlClass();
        Depot depot = new Depot();
        depot = xmlClass.readXml();
        //System.out.println(depot.list.get(0)); ;
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Пассажирский");
        System.out.println("2.Грузовой");
        int choice = sc.nextInt();
        int number=0;
        Train train = new Train();
        switch (choice){
            case(1):{
                System.out.println("Введите количество человек");
                number = sc.nextInt();
                break;
            }
            case(2):{
                System.out.println("Введите необходимый вес груза");
                number = sc.nextInt();
                break;
            }
        }
        for (int i=0;i<depot.list.size();i++){
            if ("MainCarriage".equals(depot.list.get(i).getClass().getSimpleName())){
                train.list.add(depot.list.get(i));
                depot.list.remove(i);
                break;
            }
        }
        if (!"MainCarriage".equals(train.list.get(0).getClass().getSimpleName())){
            System.out.println("Нельзя собрать поезд. Нету свободного тягача");
        }

        if (choice == 1){
            for (int i=0;i<depot.list.size();i++){
                if ("PassCarriage".equals(depot.list.get(i).getClass().getSimpleName())){
                    train.list.add(depot.list.get(i));
                    train.sumCountCarriage();

                    if (number<=train.getTotalCountPeople()){
                        break;
                    }

                }
            }
            if (number>train.getTotalCountPeople()){
                System.out.println("Недостаточно пассажирских вагонов, чтобы вместить всех людей");
            }
            //System.out.println(train.getTotalCountPeople());

        } else {
            for (int i=0;i<depot.list.size();i++){
                if ("Locomotive".equals(depot.list.get(i).getClass().getSimpleName())){
                    train.list.add(depot.list.get(i));
                    train.sumCountCarriage();
                    if (number<=train.getTotalWeight()){
                        break;
                    }
                }
            }
            if (number>train.getTotalWeight()){
                System.out.println("Недостаточно локомотивов, чтобы перевезти весь груз");
            }
        }
        xmlClass.writerXml(train);
    }
}
