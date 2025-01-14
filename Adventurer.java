public abstract class Adventurer{
  private String name;
  private int HP,maxHP;
  private boolean burn, sleep, leechEnemy, leechSelf, hyper, toxic;
  private double dmgBoost;


  //Abstract methods are meant to be implemented in child classes.
  /*
  all adventurers must have a custom special
  consumable resource (mana/rage/money/witts etc)
  */

  //give it a short name (fewer than 13 characters)
  public abstract String getSpecialName();
  //accessor methods
  public abstract int getSpecial();
  public abstract int getSpecialMax();
  public abstract void setSpecial(int n);

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
    if( n > getSpecialMax() - getSpecial()){
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial()+n);
    return n;
  }

  /*
  all adventurers must have a way to attack enemies and
  support their allys
  */
  //hurt or hinder the target adventurer
  public abstract String attack(Adventurer other);

  /*This is an example of an improvement that you can make to allow
   * for more flexible targetting.
   */
  //heal or buff the party
  //public abstract String support(ArrayList<Adventurer> others);

  //heal or buff the target adventurer
  public abstract String support(Adventurer other);

  //heal or buff self
  public abstract String support();

  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(Adventurer other);

  /*
  standard methods
  */

  public void applyDamage(double amount){
    this.HP -= amount;
  }

  //You did it wrong if this happens.
  public Adventurer(){
    this("Lester-the-noArg-constructor-string");
  }

  public Adventurer(String name){
    this(name, 10);
  }

  public Adventurer(String name, int hp){
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
  }

  //toString method
  public String toString(){
    return this.getName();
  }

  //Get Methods
  public String getName(){
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getmaxHP(){
    return maxHP;
  }

  public boolean getStatusCondition(String condition){
    if (condition.equals("burn")){
      return burn;
    }
    else if (condition.equals("sleep")){
      return sleep;
    }
    else if (condition.equals("leechSelf")){
      return leechSelf;
    }
    else if (condition.equals("leechEnemy")){
      return leechEnemy;
    }
    else if (condition.equals("hyper")){
      return hyper;
    }
    return false;
  }


  //Set Methods
  public void setHP(int health){
    this.HP = health;
  }

  public void setName(String s){
    this.name = s;
  }

  public void setmaxHP(int newMax){
    maxHP = newMax;
  }

  public void applyStatus(String condition){
    if (condition.equals("haze")){
      burn = false;
      sleep = false;
      leechEnemy = false;
      leechSelf = false;
      hyper = false;
    }
    else if (condition.equals("burn")){
      burn = true;
    }
    else if (condition.equals("sleep")){
      sleep = true;
    }
    else if (condition.equals("leechSelf")){
      leechSelf = true;
    }
    else if (condition.equals("leechEnemy")){
      leechEnemy = true;
    }
    else if (condition.equals("hyper")){
      hyper = true;
    }
    else if (condition.equals("toxic")){
      toxic = true;
    }
  }
  public void setDmgBoost(int boost){
    dmgBoost = boost;
  }
}
