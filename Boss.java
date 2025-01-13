public class Boss extends Adventurer{
  private int HP,maxHP,energy,maxEnergy;
  private double dmgBoost;
  public Boss(){
    double decider = Math.random();
    if (decider < 0.33){
      Adventurer Boss = new Blastoise();
      Boss.setDmgBoost(1.2);
    }
    else if (decider < 0.66){
      Adventurer Boss = new Charizard();
      Boss.setDmgBoost(1.2);
    }
    else{
      Adventurer Boss = new Venusaur();
      Boss.setDmgBoost(1.2);
    }

  }
  public String getSpecialName(){
    return super.getSpecialName();
  }
  public int getSpecial(){
    return super.getSpecial();
  }
  public void setSpecial(int n){
    return super.setSpecial(n);
  }

  public int getSpecialMax(){
    return super.getSpecialMax();
  }
  /*
    all adventurers must have a way to attack enemies and
    support their allys
  */
  //hurt or hinder the target adventurer
  //3-5 dmg
  public String attack(Adventurer other){
    return super.attack(other);
  }

  //heall or buff the target adventurer
  public String support(Adventurer other){
    return super.support(other);
  }

  //heall or buff self
  public String support(){
    return super.support();
  }

  //hurt or hinder the target adventurer, consume some special resource
  public String specialAttack(Adventurer other){
    return super.specialAttack(other);
  }
}
