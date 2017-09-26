public class Unit {
     
     int age = 0;
     int health;
     int maxAge;
     int strength;
     int speed;
     int longevity;
     boolean gender;
     int hasChildren;
     
     public Unit(int st, int sp, int l, boolean g) {
          strength = st;
          speed = sp;
          longevity = l;
          gender = g;
          hasChildren = 0;
          health = 75;
          maxAge = 100+(longevity+((int)(Math.random()*15))); 
     }
     
     // This can be used to add health back by healing
     public void regenerate(int r) {
          if (health < 100)
               health += r;
     }
     
     // To test
     public String toString() {
          return(+maxAge+", "+age+", "+gender+", "+health+" || "+longevity+", "+strength+", "+speed);
     }
     
     // Mate method that averages the genes, and then randomizes the gender
     public Unit mate(Unit o) {
          
          int newstr;
          int newsp;
          int newl;
          
          int stmutate = (int)(Math.random()*10);
          int spmutate = (int)(Math.random()*10);
          int lmutate = (int)(Math.random()*10);
          
          // Strength genes
          if (this.strength > o.strength)
               newstr = (int)(Math.random()*(this.strength - o.strength))+o.strength;
          else if (this.strength < o.strength)
               newstr = (int)(Math.random()*(o.strength - this.strength))+this.strength;
          else
               newstr = this.strength;
          
          // Speed genes
          if (this.speed > o.speed)
               newsp = (int)(Math.random()*(this.speed - o.speed))+o.speed;
          else if (this.speed < o.speed)
               newsp = (int)(Math.random()*(o.speed - this.speed))+this.speed;
          else
               newsp = this.speed;
          
          // Longevity genes
          if (this.longevity > o.longevity)
               newl = (int)(Math.random()*(this.longevity - o.longevity))+o.longevity;
          else if (this.longevity < o.longevity)
               newl = (int)(Math.random()*(o.longevity - this.longevity))+this.longevity;
          else
               newl = this.longevity;
          
          
          // Mutators
          if (stmutate == 0)
               newstr += (int)(Math.random()*5);
          if (spmutate == 0)
               newsp += (int)(Math.random()*5);
          if (lmutate == 0)
               newl += (int)(Math.random()*5);
          
          
          boolean newg;
          int r = (int)(Math.random()*2);
          if (r==0)
               newg = true;
          else
               newg = false;
          Unit child = new Unit(newstr,newsp,newl,newg);
          return child;
     }
}