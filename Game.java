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
    for (int row= 1;row<=30;row++) {
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
            }else line+= " ";
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
      int nameLength = 18;
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
        HPLength += Integer.toString(party.get(i).getHP()).length() + Integer.toString(party.get(i).getmaxHP()).length() + 1;
      }
      Text.go(startRow+1,2);
      for (int i = 0; i < party.size(); i++){
        System.out.print("HP:" + party.get(i).getHP() + "/" + party.get(i).getmaxHP());
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
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){
    drawBackground();
    //draw player party
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    drawParty(party,26);
    //draw enemy party
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
    int turnRow = 7;
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party,enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input


      //example debug statment
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){
        if(whichPlayer>=party.size()){
          partyTurn=false;
          whichOpponent=0;
          whichPlayer=0;
          continue;
        }
        Text.go(31,2);
        String prompt = "Enter command for " + party.get(whichPlayer) + ": attack/special/support/quit";
        System.out.print(prompt + "                    ");
        input = userInput(in);
        Adventurer currentPlayer = party.get(whichPlayer);
        String result = "";
        boolean correctInput = false;
        while (correctInput == false){
          if (input.equals("attack") || input.equals("a")){
            correctInput = true;
            break;
          } else if(input.equals("special") || input.equals("sp")){
            correctInput = true;
            break;
          } else if(input.startsWith("su") || input.startsWith("support")){
            correctInput = true;
            break;
          } else if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")){
            correctInput = true;
            break;
          }
          Text.go(31,2);
          System.out.print("Invalid command! Try: attack/special/support/quit" + "                    ");
          Text.go(32,2); 
          System.out.print("                      ");
          input = userInput(in);
        }
        Text.go(32,2); 
        System.out.print("                      ");
        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if(whichOpponent<enemies.size()){
            result=currentPlayer.attack(enemies.get(whichOpponent));
          }else{
            result= "No enemy to attack!";
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }else if(input.equals("special") || input.equals("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if(whichOpponent<enemies.size()){
            result=currentPlayer.specialAttack(enemies.get(whichOpponent));
          }else{
            result= "No enemy to attack!";
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su") || input.startsWith("support")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if(input.contains(" ")) {
              int targetIndex=Integer.parseInt(input.split(" ")[1]);
              if (targetIndex < party.size()) {
                result=currentPlayer.support(party.get(targetIndex));
              }else{
                result= "Invalid target for support!";
              }
          }else{
              result=currentPlayer.support();
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        } else if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
          break;
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        Text.go(turnRow, 2);
        System.out.println(result);
        turnRow++;
        whichPlayer++;


        if (whichPlayer >= party.size()) {
          partyTurn = false;
          whichOpponent = 0;
          whichPlayer = 0;
          Text.go(31,2);
          System.out.println("Enemy's turn. Press Enter to continue." + "                    " );
          input = userInput(in); // Wait for Enter
        }
        //done with one party member
      }else{
        //not the party turn!
        if(whichOpponent>=enemies.size()){
          partyTurn=true;
          whichPlayer=0;
          turn++;
          continue;
        }
        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        Adventurer currentEnemy = enemies.get(whichOpponent);
        String result="";
        int targetIndex = (int)(Math.random() * party.size());
        if(currentEnemy.getSpecial()>=currentEnemy.getSpecialMax()){
          result=currentEnemy.specialAttack(party.get(targetIndex));
        } else if (currentEnemy.getHP() < 0.5 * currentEnemy.getmaxHP()) {
            if (Math.random() < 0.5) {
              result=currentEnemy.support();
            } else if (enemies.size() > 1) {
              result=currentEnemy.support(enemies.get((int) (Math.random() * enemies.size())));
        } else {
          result=currentEnemy.attack(party.get(targetIndex));
        }
           } else {
              result = currentEnemy.attack(party.get(targetIndex));
           }

        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        if (turnRow >= 24){
          Text.clear();
          drawScreen(party,enemies);
          turnRow = 7;
          Text.go(turnRow, 2);
          System.out.println(result);
          turnRow++;
        }
        else{
          Text.go(turnRow, 2);
          System.out.println(result);
          turnRow++;
        }

        //Decide where to draw the following prompt:
        whichOpponent++;
      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        whichOpponent = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support/quit" + "                    " ;
        Text.go(31,2);
        System.out.print(prompt);
      }
      party.removeIf(adventurer -> adventurer.getHP() <= 0);
      enemies.removeIf(adventurer -> adventurer.getHP() <= 0);
      if(party.isEmpty()){
        drawScreen(party,enemies);
        Text.go(turnRow, 2);
        System.out.println("Game Over! Your party has been defeated.");
        break;
      }else if(enemies.isEmpty()){
        drawScreen(party,enemies);
        Text.go(turnRow, 2);
        System.out.println("Congratulations! You've defeated all enemies!");
        break;
      }
      //display the updated screen after input has been processed.
      drawScreen(party,enemies);
      
      

    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
