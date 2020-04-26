import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FighterGame {
    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        Random rnd = new Random();

        ArrayList<Fighter> playerFighters = new ArrayList<Fighter>();
        playerFighters.add(new Fighter("Richo", 4, 5, Elements.FIRE));
        playerFighters.add(new Fighter("Bobo", 3, 6, Elements.GRASS));
        playerFighters.add(new Fighter("Squirtle", 5, 5, Elements.WATER));
        ArrayList<Fighter> computerFighters = new ArrayList<Fighter>();
        computerFighters.add(new Fighter("Quipper", 4, 3, Elements.FIRE));
        computerFighters.add(new Fighter("Slimbo", 3, 6, Elements.GRASS));
        computerFighters.add(new Fighter("Brambo", 5, 5, Elements.WATER));
        computerFighters.add(new Fighter("Scooby", 3, 3, Elements.WATER));

        ArrayList<Arena> arenas = new ArrayList<Arena>();
        arenas.add(new Arena(Elements.GRASS));
        
        int playerWins = 0;
        int compWins = 0;

        System.out.println("*Introduction*");
        System.out.println("*Fighting mechanics*");
        while(playerWins < 3 & compWins < 3) {
            System.out.println("Player Points: " + playerWins);
            System.out.println("Computer Points: " + compWins);
            listFighers(playerFighters, computerFighters);
            
            System.out.print("Please select your fighter: ");
            int playerChoice = scnr.nextInt();
            scnr.nextLine(); //Flush buffer;
            System.out.println(); //Move the cursor to new line

            if(playerChoice < playerFighters.size() & playerChoice >= 0){ //confirm we are in the bounds of the array
                //Make the computer's choice
                int compChoice = rnd.nextInt(computerFighters.size());
                System.out.println("The computer has chosen " + computerFighters.get(compChoice).getName());

                //and Fight!
                BattleResult result = arenas.get(0).Battle(playerFighters.get(playerChoice), computerFighters.get(compChoice));
                switch(result){
                    case WIN:
                        System.out.println("Congratulations! You wont the fight!");
                        computerFighters.remove(compChoice);
                        playerWins++;
                        break;
                    case LOSS:
                        System.out.println("You lost that match");
                        playerFighters.remove(playerChoice);
                        compWins++;
                        break;
                    case WIPEOUT:
                        System.out.println("Both fighters got eachother in the end. This round is a tie!");
                        playerFighters.remove(playerChoice);
                        computerFighters.remove(compChoice);
                        break;
                    case TIE:
                        System.out.println("Neither fighter could out do the other. The round is a tie");
                        break;
                }
            } 
            
        }
        scnr.close();
    }

    private static void listFighers(ArrayList<Fighter> playerFighters, ArrayList<Fighter> compFighters) {
        System.out.printf("Player Fighters:                     Computer Fighters:\n");
        for(int i = 0; i < Math.max(playerFighters.size(), compFighters.size()); i++){ //Loop the number of times as the size of the largest array
            //one array may be smaller than the other so we must protect us from trying to access an index outside of the array size
            String playerFighterInfo = "";
            String compFighterInfo = "";
            if(i < playerFighters.size()){
                playerFighterInfo = String.format("%-10s +%4d/%-4d %-7s", playerFighters.get(i).getName(), playerFighters.get(i).getAttack(), playerFighters.get(i).getDefence(), playerFighters.get(i).getElement());
            }
            if(i < compFighters.size()){
                compFighterInfo = String.format("%-10s +%4d/%-4d %-7s", compFighters.get(i).getName(), compFighters.get(i).getAttack(), compFighters.get(i).getDefence(), compFighters.get(i).getElement());
            }

            System.out.printf("%30s      %30s\n", playerFighterInfo, compFighterInfo);
        }
    }

}