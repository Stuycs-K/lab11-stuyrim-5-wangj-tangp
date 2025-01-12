import java.util.Random;
public class Charizard extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  private double dmgBoost;
  public Charizard(){
    this.HP=250;
    this.maxHP=250;
    this.energy=100;
    this.maxEnergy=100;
    this.dmgBoost=1;
  }
  public String getSpecialName(){
    return "Blast Burn";
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
    double damage= Math.random() * 1.15 * 90;
    other.applyDamage(damage*dmgBoost);
    restoreSpecial(30);
    return "";
  }

  //heall or buff the target adventurer
  public String support(Adventurer other){
    other.applyStatus(burn);
    restoreSpecial(3);
    return "";
  }

  //heall or buff self
  public String support(){
    setHP(getHP()+125);
    restoreSpecial(30);
    return "";
  }

  //hurt or hinder the target adventurer, consume some special resource
  //3-8 dmg
  public String specialAttack(Adventurer other){
    if(getSpecial()>=6){
      int damage= (int)(Math.random()*3+Math.random()*3)+3;
      other.applyDamage(damage);
      setSpecial(getSpecial()-6);
    }else{
      return ""+attack(other);
    }
    return "";
  }

  public String mega(){
    dmgBoost=1.5;
    return this+" used Dragon Dance. "+this+ "'s damage is increased by 1.5x.";
  }
}
