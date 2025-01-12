import java.util.Random;
public class Blastoise extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  private double dmgBoost;
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
    double damage= Math.random() * 1.15 * 110;
    other.applyDamage(damage*dmgBoost);
    restoreSpecial(10);
    return this+" used Hydro Pump. "+other+" took "+damage+" damage.";
  }

  //heall or buff the target adventurer
  public String support(Adventurer other){
    other.restoreSpecial(30);
    this.applyStatus("haze");
    other.applyStatus("haze");
    return this+" used Haze. All effects are cleared.";
  }

  //heall or buff self
  public String support(){
    if(getHP()+getmaxHP()*.67>getmaxHP()){
      setHP(getmaxHP());
    }else setHP(getHP()+(int)(getmaxHP()*.67));
    restoreSpecial(30);
    dmgBoost=2;
    return this+" used Shell Smash. "+this+" healed .67x hp and increased damage by 2x.";
  }

  //hurt or hinder the target adventurer, consume some special resource
  //3-8 dmg
  public String specialAttack(Adventurer other){
    double damage= Math.random() * 1.15 * 150;
    if(getSpecial()>=100){
      other.applyDamage(damage);
      setSpecial(getSpecial()-100);
    }else{
      return this+" tried to used but didnt have enough Energy. Instead, "+attack(other);
    }
    return this+" used Water Spout. "+other+" took "+damage+" damage.";
  }
}
