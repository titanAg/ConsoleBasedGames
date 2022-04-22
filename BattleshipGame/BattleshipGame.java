//Kyle Orcutt
import java.util.Scanner;
public class BattleshipGame
{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int[][] battleship1 = {{2,0,0,0,0,0},{2,0,0,0,3,0},{0,0,0,0,3,0},{0,0,0,0,3,0},{0,0,0,0,0,0},{0,0,4,4,4,4}};
        String[][] battleship2 = new String[6][6];

        int hit = 0;
        int patrolBoat = 0; 
        int destroyer = 0;
        int battleship = 0;

        while (hit < 9){
            System.out.println("Please enter a guess in the form 'B5': ");
            String guess = input.next().toUpperCase();

            int row2 = guess.charAt(0) - 'A';
            int col2 = guess.charAt(1) - 49;

            if ("X".equals(battleship2[row2][col2]) || "O".equals(battleship2[row2][col2])){
                System.out.println("You have already guessed this position, try again.\n");
            }else {
                System.out.print(" ");
                for (int i = 1; i < battleship2[0].length + 1; i++){
                    System.out.print(" " + i);
                }
                System.out.println();
                for(int row = 0; row < battleship2.length; row++){
                    char rowLetter = (char)(row + 65);
                    System.out.print(rowLetter + " ");
                    for(int col = 0; col < battleship2[row].length; col++){
                        if (!"X".equals(battleship2[row][col]) && !"O".equals(battleship2[row][col])){
                            battleship2[row][col] = ".";
                        }
                        if (battleship1[row2][col2] > 0){
                            battleship2[row2][col2] = "X";
                        }else if(battleship1[row2][col2] == 0){
                            battleship2[row2][col2] = "O";
                        }
                        System.out.print(battleship2[row][col] + " ");
                    }            
                    System.out.println();
                }

                if (battleship2[row2][col2] == "X"){
                    System.out.println("hit");
                    hit++;
                    if (battleship1[row2][col2] == 2){
                        patrolBoat++;
                    }else if (battleship1[row2][col2] == 3){
                        destroyer++;
                    }else {
                        battleship++;
                    }
                }
                if (battleship2[row2][col2] == "O"){
                    System.out.println("miss");
                }
                if (patrolBoat == 2){
                    System.out.println("You sunk the PatrolBoat");
                    patrolBoat = 0;
                }            
                if (destroyer == 3){
                    System.out.println("You sunk the Destroyer");
                    destroyer = 0;
                }            
                if (battleship == 4){
                    System.out.println("You sunk the Battleship");
                    battleship = 0;
                }
                System.out.println();
            }
            if (hit == 9){
                System.out.println("Game over, You Win!");
            }
        }
    }
}
