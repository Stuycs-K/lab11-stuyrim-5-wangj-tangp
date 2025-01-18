import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    for(int col= 1;col<=80;col++) {
        Text.go(1,col);
        System.out.print("-");
        Text.go(6,col);
        System.out.print("-");
        Text.go(25,col);
        System.out.print("-");
        Text.go(30,col);
        System.out.print("-");
    }
    for (int row= 1;row<=80;row++) {
        Text.go(row,1);
        System.out.print("|");
        Text.go(row,80);
        System.out.print("|");
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Text.go(startRow,startCol);
    System.out.print(s);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    int currentRow=row;
    int index=0;
    for(int i=0;i<height;i++){
        String line= "";
        for(int j=0;j<width;j++){
            if(index<text.length()){
                line+= text.charAt(index);
                index++;
            }else line+= "";
        }
        Text.go(currentRow, col);
        System.out.print(line);
        currentRow++;
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int rand=(int)(Math.random()*3);
      if(rand==0){
        return new Charizard();
      }else if(rand==1){
        return new Blastoise();
      }else return new Venusaur();
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){

      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      int nameLength = 15;
      for (int i = 0; i < party.size(); i++){
        nameLength += party.get(i).getName().length();
      }
      Text.go(startRow,2);
      for (int i = 0; i < party.size(); i++){
        System.out.print("Name:" + party.get(i).getName());
        for (int j = 0; j < (80-nameLength)/party.size()-2; j++){
          System.out.print(" ");
        }
      }
      System.out.println("");
      int HPLength = 9;
      for (int i = 0; i < party.size(); i++){
        HPLength += Integer.toString(party.get(i).getHP()).length();
      }
      Text.go(startRow+1,2);
      for (int i = 0; i < party.size(); i++){
        System.out.print("HP:" + party.get(i).getHP());
        for (int j = 0; j < (80-HPLength)/party.size()-2; j++){
          System.out.print(" ");
        }
      }
      System.out.println("");
      int specialLength = 9;
      for (int i = 0; i < party.size(); i++){
        specialLength += Integer.toString(party.get(i).getSpecial()).length();
      }
      Text.go(startRow+2,2);
      for (int i = 0; i < party.size(); i++){
        System.out.print("SP:" + party.get(i).getSpecial());
        for (int j = 0; j < (80-specialLength)/party.size()-2; j++){
          System.out.print(" ");
        }
      }
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    double percent = (double)hp/maxHP;
    if(percent<0.25){
        return Text.colorize(output,Text.RED);
    }else if(percent<0.75){
        return Text.colorize(output,Text.YELLOW);
    }else return Text.colorize(output,Text.WHITE);
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(){

    drawBackground();
    //draw player party
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    party.add(new Charizard());
    party.add(new Venusaur());
    party.add(new Blastoise());
    drawParty(party,26);
    //draw enemy party
    ArrayList<Adventurer> enemies = new ArrayList<>();
    enemies.add(new Charizard());
    enemies.add(new Venusaur());
    enemies.add(new Blastoise());
    drawParty(enemies,2);

  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(32,2);
      //show cursor
      Text.showCursor();
      String input = in.nextLine();

      //clear the text that was written
      Text.hideCursor();
      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer> enemies = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    int numEnemy=(int) (Math.random() * 3) + 1;
    if (numEnemy==1){
      enemies.add(new Boss());
    } else{
      while(numEnemy > 0){
        enemies.add(createRandomAdventurer());
        numEnemy--;
      }
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    party.add(new Charizard());
    party.add(new Venusaur());
    party.add(new Blastoise());
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen();//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for " + party.get(whichPlayer) + ": attack/special/quit";
    Text.go(31,2);
    System.out.print(preprompt);

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);

      //example debug statment
      TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){
        Adventurer currentPlayer = party.get(whichPlayer);
        
        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if (enemies.size() > 0) {
            currentPlayer.attack(enemies.get(whichOpponent));
            System.out.println(currentPlayer + " attacked " + enemies.get(whichOpponent));
          } else {
            System.out.println("No enemies left to attack!");
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.equals("special") || input.equals("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if (enemies.size() > 0) {
            currentPlayer.specialAttack(enemies.get(whichOpponent));
            System.out.println(currentPlayer + " used a special attack on " + enemies.get(whichOpponent));
        } else {
            System.out.println("No enemies left to attack!");
        }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          int targetIndex;
          try {
            targetIndex = Integer.parseInt(input.split(" ")[1]);
            if (targetIndex >= 0 && targetIndex < enemies.size()) {
              currentPlayer.support(enemies.get(targetIndex));
              System.out.println(currentPlayer + " supported " + enemies.get(targetIndex));
            } else {
              System.out.println("Invalid target index for support!");
            }
          } catch (Exception e) {
            System.out.println("Error parsing support target!");
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        } else {
          System.out.println("Invalid command! Try: attack/special/support/quit");
          
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;

        
        if (whichPlayer >= party.size()) {
          partyTurn = false;
          whichOpponent = 0;
          whichPlayer = 0;
          Text.go(31,2);
          System.out.println("Enemy's turn. Press Enter to continue.");
          input = userInput(in); // Wait for Enter
        } else {
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          Text.go(31,2);
          System.out.print(prompt);
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        Adventurer currentEnemy = enemies.get(whichOpponent);

        if (currentEnemy.getSpecial() >= currentEnemy.getSpecialMax()) {
            currentEnemy.specialAttack(party.get((int) (Math.random() * party.size())));
            System.out.println(currentEnemy + " used a special attack!");
        } else if (currentEnemy.getHP() < 0.5 * currentEnemy.getmaxHP()) {
            if (Math.random() < 0.5) {
                currentEnemy.support();
                System.out.println(currentEnemy + " supported itself!");
            } else if (enemies.size() > 1) {
                currentEnemy.support(enemies.get((int) (Math.random() * enemies.size())));
                System.out.println(currentEnemy + " supported an ally!");
            }
        } else {
            currentEnemy.attack(party.get((int) (Math.random() * party.size())));
            System.out.println(currentEnemy + " attacked a party member!");
        }

        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";
        Text.go(31,2);
        System.out.print(prompt);
        whichOpponent++;
        if (whichOpponent >= enemies.size()) {
          partyTurn = true;
          whichOpponent = 0;
          System.out.println("Party's turn. Press Enter to continue.");
          input = userInput(in); // Wait for Enter
        }
      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
        Text.go(31,2);
        System.out.print(prompt);
      }
      //display the updated screen after input has been processed.
      drawScreen();


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
