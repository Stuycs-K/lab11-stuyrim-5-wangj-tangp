public class Blastoise extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  private double dmgBoost;
  public Blastoise(){
    this.setName("Blastoise");
    this.HP=300;
    this.maxHP=300;
    this.energy=50;
    this.maxEnergy=100;
    this.dmgBoost = 1;
  }
  public String getSpecialName(){
    return "Water Spout";
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
  /*
    all adventurers must have a way to attack enemies and
    support their allys
  */
  //hurt or hinder the target adventurer
  //3-5 dmg
  public String attack(Adventurer other){
    int damage= (int)(Math.random() * 1.15 * 110);
    other.applyDamage(damage*dmgBoost);
    restoreSpecial(10);
    return "Blastoise used Hydro Pump, dealing " + damage + "damage.";
  }

  //heall or buff the target adventurer
  public String support(Adventurer other){
    other.restoreSpecial(30);
    this.applyStatus("haze");
    other.applyStatus("haze");
    return "Blastoise used Haze. All effects are cleared.";
  }

  //heall or buff self
  public String support(){
    int currentHP = getHP();
    setHP((currentHP/2));
    int finalHP = getHP();
    restoreSpecial(30);
    dmgBoost=2;
    return "Blastoise used Shell Smash. Blastoise lost " + Integer.toString(finalHP - currentHP) +  "and increased damage by 2x.";
  }

  //hurt or hinder the target adventurer, consume some special resource
  //3-8 dmg
  public String specialAttack(Adventurer other){
    int damage= (int)(Math.random() * 1.15 * 150);
    if(getSpecial()>=100){
      other.applyDamage(damage);
      setSpecial(getSpecial()-100);
    }else{
      return "Blastoise tried to use Water Spout but didnt have enough energy. Instead, " + attack(other);
    }
    return "Blastoise used Water Spout, dealing " + damage + "damage.";
  }

  public void setDmgBoost(int boost){
    dmgBoost = boost;
  }
}
