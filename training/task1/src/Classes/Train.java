package Classes;

import Interface.Carriage;

import java.util.ArrayList;
import java.util.List;

public class Train {
   private int totalWeight=0;
   private int totalCountPeople=0;
   private int countCarriage=1;
   public List<Carriage> list = new ArrayList<Carriage>();

   public int getTotalWeight(){
      totalWeight=0;
      for(int i=1;i<list.size();i++){
         totalWeight +=list.get(i).getCountCapacity();
      }
      return totalWeight;
   }

   public int getTotalCountPeople(){
      totalCountPeople=0;
      for(int i=1;i<list.size();i++){
         totalCountPeople +=list.get(i).getCountPassenger();
      }
      return totalCountPeople;
   }

   public int getCountCarriage(){
      return countCarriage;
   }
   public int sumCountCarriage(){
      return countCarriage +=1;
   }
}
