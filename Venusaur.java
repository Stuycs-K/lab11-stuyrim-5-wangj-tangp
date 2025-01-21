public class Venusaur extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Venusaur(){
    super("Venusaur",350, 1);
    this.maxHP=350;
    this.energy=0;
    this.maxEnergy=200;
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "Solar Beam";
  }

  public int getSpecial(){
    return energy;
  }

  public void setSpecial(int n){
    if(n>this.getSpecialMax()){
      this.energy=getSpecialMax();
    }
    else {
      this.energy=n;
    }
  }

  public int getSpecialMax(){
    return maxEnergy;
  }

  public String attack(Adventurer other){
    int damage= (int)(((((int)(Math.random() * 15) + 85) * 70) /100) * this.getDmgBoost());
    other.applyDamage(damage);
    restoreSpecial(30);
    int currentHP = getHP();
    setHP(getHP() + damage);
    int finalHP = getHP();
    return "Venusaur used Giga Drain, dealing " + damage + " damage." + " Venusuar also restored " + Integer.toString(finalHP - currentHP)+ " HP";
  }

  public String specialAttack(Adventurer other){
    int damage = (int)(((((int)(Math.random() * 15) + 85) * 140) /100) * this.getDmgBoost());
    if(getSpecial()>=60){
      other.applyDamage(damage);
      setSpecial(getSpecial()-60);
    }else{
      return "Venusaur tried to use Solar Beam but didnt have enough energy. Instead, " + attack(other);
    }
    return "Venusaur used Solar Beam, dealing " + damage + " damage.";
  }

  public String support(Adventurer other){
    int currentHP = other.getHP();
    other.setHP(getHP()+175);
    int finalHP = other.getHP();
    restoreSpecial(30);
    return "Venusaur used Synthesis, restoring " + Integer.toString((finalHP-currentHP)) + " HP to " + other.getName();
  }

  public String support(){
    restoreSpecial(30);
    this.setDmgBoost(2);
    return "Venusaur used Sunny Day. It's damage is now increased by 100%.";
  }
}
