public class Charizard extends Adventurer{

  public static void main(String[] args) {
    Charizard test = new Charizard();
  }


  private int HP,maxHP,energy,maxEnergy;
  public Charizard(){
    super("Charizard",250,1);
    this.maxHP=250;
    this.energy=0;
    this.maxEnergy=200;
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
    int damage= (int)(((((int)(Math.random() * 15) + 85) * 90) /100) * this.getDmgBoost());
    other.applyDamage(damage);
    restoreSpecial(30);
    return "Charizard used Flamethrower, dealing " + damage + " damage.";
  }

  //heall or buff the target adventurer
  public String support(Adventurer other){
    other.setDmgBoost((int)other.getDmgBoost() + 1);
    restoreSpecial(30);
    return "Charizard used Helping Hand. " + other.getName() + " now does +100% damage.";
  }

  //heall or buff self
  public String support(){
    int currentHP = getHP();
    setHP(getHP()+175);
    int finalHP = getHP();
    restoreSpecial(30);
    return "Charizard used Roost, restoring " + Integer.toString((finalHP-currentHP)) + " HP.";
  }

  //hurt or hinder the target adventurer, consume some special resource
  public String specialAttack(Adventurer other){
    int damage= (int)(((((int)(Math.random() * 15) + 85) * 150) /100) * this.getDmgBoost());    
    if(getSpecial()>=60){
      other.applyDamage(damage);
      setSpecial(getSpecial()-60);
    }else{
      return "Charizard tried to use Blast Burn but didnt have enough energy. Instead, " + attack(other);
    }
    return "Charizard used Blast Burn, dealing " + damage + " damage.";
  }
}
