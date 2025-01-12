public class Venusaur extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  private double dmgBoost;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Venusaur(){
    this.HP=350;
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
    }else this.energy=n;
  }

  public int getSpecialMax(){
    return maxEnergy;
  }

  public String attack(Adventurer other){
    int damage= (int)(Math.random() * 1.15 * 70);
    other.applyDamage(damage*dmgBoost);
    restoreSpecial(30);
    int currentHP = getHP();
    setHP(getHP() + damage);
    int finalHP = getHP();
    return "Venusaur used Giga Drain, dealing " + damage + "damage." + "Venusuar also restored " + Integer.toString(finalHP - currentHP);
  }

  public String specialAttack(Adventurer other){
    int damage = (int)(Math.random() * 1.15 * 140);
    if(getSpecial()>=60){
      other.applyDamage(damage);
      setSpecial(getSpecial()-60);
    }else{
      return "Venusaur tried to use Solar Beam but didnt have enough energy. Instead, " + attack(other);
    }
    return "Venusaur used Solar Beam, dealing " + damage + "damage.";
  }

  public String support(Adventurer other){
    other.applyStatus("sleep");
    restoreSpecial(30);
    return "Venusaur used Sleep Powder. " + other.getName() + "is now asleep.";
  }

  public String support(){
    restoreSpecial(30);
    dmgBoost = 1.5;
    return "Venusaur used Sunny Day. It's damage is now multiplied by 1.5x.";
  }
}
