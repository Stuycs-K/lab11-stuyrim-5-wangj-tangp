public class Blastoise extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  public Blastoise(){
    super("Blastoise",300, 1);
    this.maxHP=300;
    this.energy=0;
    this.maxEnergy=200;
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
    int damage= (int)(((((int)(Math.random() * 15) + 85) * 110) /100) * this.getDmgBoost());
    other.applyDamage(damage);
    restoreSpecial(30);
    return "Blastoise used Hydro Pump, dealing " + damage + " damage.";
  }

  //heall or buff the target adventurer
  public String support(Adventurer other){
    int currentHP = other.getHP();
    other.setHP(getHP()+100);
    int finalHP = other.getHP();
    restoreSpecial(60);
    return "Blastoise used Aqua Ring, restoring " + Integer.toString((finalHP-currentHP)) + " HP to " + other.getName();
  }

  //heall or buff self
  public String support(){
    int currentHP = getHP();
    setHP((currentHP/2));
    int finalHP = getHP();
    restoreSpecial(30);
    this.setDmgBoost((int)this.getDmgBoost()+1);
    return "Blastoise used Shell Smash. Blastoise lost " + Integer.toString(finalHP - currentHP) +  " and increased damage by 100%.";
  }

  //hurt or hinder the target adventurer, consume some special resource
  //3-8 dmg
  public String specialAttack(Adventurer other){
    int damage= (int)(((((int)(Math.random() * 15) + 85) * 150) /100) * this.getDmgBoost());
    if(getSpecial()>=60){
      other.applyDamage(damage);
      setSpecial(getSpecial()-60);
    }else{
      return "Blastoise tried to use Water Spout but didnt have enough energy. Instead, " + attack(other);
    }
    return "Blastoise used Water Spout, dealing " + damage + " damage.";
  }
}
