import java.util.*;

public class Evolve {
     
     public static int time = 0;
     public static int startCount = 0;
     public static int popCount = 0;
     public static ArrayList<Unit> population = new ArrayList<Unit>();
     public static int harshness = (int)(Math.random()*10);
     public static int danger = (int)(Math.random()*10);
     public static int toxicity = 1;
     
     public static void main(String[] args) {
          
          Scanner s = new Scanner(System.in);
          
          System.out.println("Enter the starting population:");
          startCount = Integer.parseInt(s.nextLine());
          System.out.println("Enter the time passed:");
          time = Integer.parseInt(s.nextLine());
          
          
          for (int i = 0; i<startCount; i++) {
               int tempStrength = (int)(Math.random()*10)+1;
               int tempSpeed = (int)(Math.random()*10)+1;
               int tempLongevity = (int)(Math.random()*10)+1;
               boolean tempGender;
               int r = (int)(Math.random()*2);
               if (r==0)
                    tempGender = true;
               else
                    tempGender = false;
               Unit temp = new Unit(tempStrength,tempSpeed,tempLongevity,tempGender);
               population.add(temp);
          }
          
          System.out.println("\nHarshness: " + harshness);
          System.out.println("Danger: " + danger);
          
          System.out.println("\nResults:\nUnit number: Max Age, Age, Gender, Health || Longevity, Strength, Speed\n");
          
          popCount = population.size();
          for (int i = 0; i<startCount; i++) {
               System.out.println("Unit "+i+": \t"+population.get(i).toString());
          }
          
          System.out.println("-------------------------------------------------------------");
          
          start(time);
          
          if (popCount > 0) {
               for (int i = 0; i<popCount; i++) {
                    System.out.println("Unit "+i+": \t"+population.get(i).toString());
               }  
          } else {
               System.out.println("Sorry, all units died");
          }
          
          
     }
     
     public static void start(int iteration)
     {
          for (int i = 0; i < iteration; i++)
          {
               for (int j = 0; j < (popCount); j++) {
                    /*
                     We need:
                     - check age
                     - face environment
                     - regenerate
                     - mate
                     - age add
                     */
                    // dies
                    if ((population.get(j).age > population.get(j).maxAge)||(population.get(j).health <= 0)) {
                         population.remove(j);
                         popCount = population.size();
                    }
                    else {
                         // Env
                         checkEnv(population.get(j));
                         
                         // Regen
                         population.get(j).regenerate(1);
                         
                         // Mate
                         if ((population.get(j).hasChildren < 2)&&(population.get(j).age > 25)&&(population.get(j).age < 40)) {
                              for (int k = 0; k < population.size(); k++) {
                                   //int chance = (int)(Math.random()*10);
                                   //if (chance == 0) {
                                   if (population.get(k).gender != population.get(j).gender) {
                                        int chance2 = (int)(Math.random()*population.size());
                                        if (chance2 == 0) {
                                             Unit m = population.get(k);
                                             population.add(population.get(j).mate(m));
                                             population.get(j).hasChildren++;
                                             popCount = population.size();
                                             break;
                                        }
                                   }
                                   //}
                              }
                              
                         }
                         
                         if ((population.get(j).health <= 0)) {
                              population.remove(j);
                              popCount = population.size();
                         }
                         else {
                              // age
                              population.get(j).age += 1;
                         }
                    }
                    
               }
               
          }
     }
     
     public static void checkEnv(Unit c) {
          if (c.strength < harshness)
               c.health -= harshness-c.strength;
          if (c.speed < danger)
               c.health -= danger-c.speed;
          c.health -= toxicity;
     }
     
     
     
}