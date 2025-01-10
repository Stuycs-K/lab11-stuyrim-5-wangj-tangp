import java.util.Random;
public class Charizard extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  private boolean burn, sleep, sun, leechEnemy, leechSelf, hyper;
  public Charizard(){
    this.HP=350;
    this.maxHP=350;
    this.energy=100;
    this.maxEnergy=100;
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
    int damage= (int)(Math.random() * 1.15 * 90);
    other.applyDamage(damage);
    restoreSpecial(damage);
    return "";
  }

  //heall or buff the target adventurer
  public String support(Adventurer other){
    other.setHP(other.getHP()+1);
    other.restoreSpecial(3);
    return "";
  }

  //heall or buff self
  public String support(){
    setHP(getHP()+1);
    restoreSpecial(3);
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
}
