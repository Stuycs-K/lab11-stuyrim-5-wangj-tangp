public class Boss extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  private double dmgBoost;
  public Boss(){
    this.setName("Mewtwo");
    this.HP=1000;
    this.maxHP=1000;
    this.energy=50;
    this.maxEnergy=200;
    this.dmgBoost=1;
    }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "Psystrike";
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
    int damage= (int)(Math.random() * 1.15 * 140);
    other.applyDamage(damage*dmgBoost);
    restoreSpecial(60);
    return "Mewtwo used Aura Sphere, dealing " + damage + "damage.";
  }

  public String specialAttack(Adventurer other){
    int damage = (int)(Math.random() * 1.15 * 280);
    if(getSpecial()>=200){
      other.applyDamage(damage);
      setSpecial(getSpecial()-200);
    }else{
      return "Mewtwo tried to use Psystrike but didnt have enough energy. Instead, " + attack(other);
    }
    return "Mewtwo used Psystrike, dealing " + damage + "damage.";
  }

  public String support(Adventurer other){
    other.applyStatus("toxic");
    restoreSpecial(30);
    return "Mewtwo used Toxic. " + other.getName() + "is now asleep.";
  }

  public String support(){
    int currentHP = getHP();
    setHP(getHP()*2);
    int finalHP = getHP();
    restoreSpecial(30);
    return "Mewtwo used Amnesia, restoring " + Integer.toString((finalHP-currentHP)) + "HP.";
  }

  public void setDmgBoost(int boost){
    dmgBoost = boost;
  }
}
