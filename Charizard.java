public class Charizard extends Adventurer{

  public static void main(String[] args) {
    Charizard test = new Charizard();
  }


  private int HP,maxHP,energy,maxEnergy;
  private double dmgBoost;
  public Charizard(){
    this.setName("Charizard");
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
    int damage= (int)(Math.random() * 1.15 * 90);
    other.applyDamage(damage*dmgBoost);
    restoreSpecial(30);
    return "Charizard used Flamethrower, dealing " + damage + "damage.";
  }

  //heall or buff the target adventurer
  public String support(Adventurer other){
    other.applyStatus("burn");
    restoreSpecial(30);
    return "Charizard used Will-o-Wisp. " + other.getName() + "is now burned.";
  }

  //heall or buff self
  public String support(){
    int currentHP = getHP();
    setHP(getHP()+175);
    int finalHP = getHP();
    restoreSpecial(30);
    return "Charizard used roost, restoring " + Integer.toString((finalHP-currentHP)) + "HP.";
  }

  //hurt or hinder the target adventurer, consume some special resource
  public String specialAttack(Adventurer other){
    int damage = (int)(Math.random() * 1.15 * 150);
    if(getSpecial()>=60){
      other.applyDamage(damage);
      setSpecial(getSpecial()-60);
    }else{
      return "Charizard tried to use Blast Burn but didnt have enough energy. Instead, " + attack(other);
    }
    return "Charizard used Blast Burn, dealing " + damage + "damage.";
  }

  public String mega(){
    dmgBoost=1.5;
    return this+" used Dragon Dance. "+this+ "'s damage is increased by 1.5x.";
  }

  public void setDmgBoost(int boost){
    dmgBoost = boost;
  }
}
