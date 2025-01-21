public class Venusaur extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  private double dmgBoost;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Venusaur(){
    super("Venusaur",350);
    this.maxHP=350;
    this.energy=0;
    this.maxEnergy=200;
    this.dmgBoost=1;
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
    int damage= (((int)(Math.random() * 15) + 85) * 70) /100;
    other.applyDamage(damage*dmgBoost);
    restoreSpecial(30);
    int currentHP = getHP();
    setHP(getHP() + damage);
    int finalHP = getHP();
    return "Venusaur used Giga Drain, dealing " + damage + " damage." + " Venusuar also restored " + Integer.toString(finalHP - currentHP)+ " HP";
  }

  public String specialAttack(Adventurer other){
    int damage = (((int)(Math.random() * 15) + 85) * 140) /100;
    if(getSpecial()>=60){
      other.applyDamage(damage);
      setSpecial(getSpecial()-60);
    }else{
      return "Venusaur tried to use Solar Beam but didnt have enough energy. Instead, " + attack(other);
    }
    return "Venusaur used Solar Beam, dealing " + damage + " damage.";
  }

  public String support(Adventurer other){
    other.applyStatus("burn");
    restoreSpecial(30);
    return "Venusaur used Sludge Bomb. " + other.getName() + " is now burned.";
  }

  public String support(){
    restoreSpecial(30);
    dmgBoost = 1.5;
    return "Venusaur used Sunny Day. It's damage is now multiplied by 1.5x.";
  }

  public void setDmgBoost(int boost){
    dmgBoost = boost;
  }
}
