import java.util.Random;
public class Blastoise extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  private boolean burn,sleep,sun,leechEnemy,leechSelf,hyper;
  public Blastoise(){
    this.HP=300;
    this.maxHP=300;
    this.energy=50;
    this.maxEnergy=100;
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
    }else this.energy=n;
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
    double damageMult= (Math.random()*.15)+.85;
    int damage= (int)(110*damageMult);
    other.applyDamage(damage);
    restoreSpecial(10);
    return this+" used Hydro Pump. "+other+" took "+damage+" damage.";
  }

  //heall or buff the target adventurer
  public String support(Adventurer other){
    other.restoreSpecial(30);
    burn=false;
    sleep=false;
    sun=false;
    leechEnemy=false;
    leechSelf=false;
    hyper=false;
    return this+" used Haze. All effects are cleared.";
  }

  //heall or buff self
  public String support(){
    setHP(getHP()+1);
    restoreSpecial(30);
    return this+" used Shell Smash. ";
  }

  //hurt or hinder the target adventurer, consume some special resource
  //3-8 dmg
  public String specialAttack(Adventurer other){
    if(getSpecial()>=100){
      double damageMult= (Math.random()*.15)+.85;
      int damage= (int)(150*damageMult);
      other.applyDamage(damage);
      setSpecial(getSpecial()-100);
    }else{
      return this+" tried to used but didnt have enough Energy. Instead, "+attack(other);
    }
    return this+" used Water Spout. "+other+" took "+damage+" damage.";
  }
}
