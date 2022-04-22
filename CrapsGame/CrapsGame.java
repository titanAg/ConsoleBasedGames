// Kyle Orcutt 300277486
// COSC 111 - Programming I

public class CrapsGame {
    public static void main(String[] args) {
        int totalRoll = (((int) (Math.random() * 6) + 1) + ((int) (Math.random() * 6) + 1));
        int point = 0;
        int point2 = 0;
        System.out.println("Your first roll is " + totalRoll);

        if (totalRoll == 7 || totalRoll == 11) {
            System.out.println("You win!");
        } else if (totalRoll == 2 || totalRoll == 3 || totalRoll == 12) {
            System.out.println("You lose!");
        } else {
            point = totalRoll;
            System.out.println("Point is " + point);
            while (point2 != point) {
                point2 = (((int) (Math.random() * 6) + 1) + ((int) (Math.random() * 6) + 1));
                System.out.println("Next roll is " + point2);
                if (point2 == 7) {
                    System.out.println("You Lose!");
                    break;
                } else if (point2 == point) {
                    System.out.println("You Win!");
                    break;
                }
            }
        }


    }
}
