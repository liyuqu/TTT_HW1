import java.util.Scanner;
public class Main {
    private board board;//our board

    private State state;//the status

    private pawntype currentplayer;//the pawntype of the current player

    private static Scanner in = new Scanner(System.in);

    private int boards; //here the int we use is the size of our board,
    // which will be asked to the player later on

    private String pp1;//Name of p1

    private String pp2;//Name of p2

    private int p1w=0;//times of p1w

    private int p2w=0;//times of p2w

    private int nog=0;//number of games played

    public Main(){//Main part, will be called at the bottom
        System.out.println("Welcome to Tic Tac Toe!");
        boolean good=false;
        while (!good) {
            System.out.print("How many lines do you want to play? (" +
                    "like a x*x board, and the number you enter is x where x>2)");
            //get the size of our board game//
            int s = in.nextInt();
            if (s >= 3) {
                boards = s;//set size

                good = true;
            }
            else{
                System.out.println("Sorry, the input is not valid, please try again!");
            }
        }
        System.out.print("What's your name, player 1?");
        String p1 = in.next();
        pp1 = p1;
        System.out.println("Thanks! "+pp1+", you will play with Pawn X.");
        System.out.print("What's your name, player 2?");
        String p2 = in.next();
        pp2 = p2;
        System.out.println("Thanks! "+pp2+", you will play with Pawn O.");
        initGame();//init the game
        NewGame();//start a new game
        board.draw();
        while (state == State.Playing) {//still playing
            update();//see below, ask a valid move and update the move and state of play
            if (state!=State.Playing) {
                if (state == State.Xwin) {
                    System.out.println(p1 + " Win! Congratulations!");
                    p1w+=1;
                    nog+=1;
                    System.out.println("You have played "+nog+" times:");
                    System.out.println(p1 + " has won "+ p1w + " times");
                    System.out.println(p2 + " has won "+ p2w + " times");
                } else if (state == State.OWin) {
                    System.out.println(p2 + " Win! Congratulations!");
                    p2w+=1;
                    nog+=1;
                    System.out.println("You have played "+nog+" times:");
                    System.out.println(p1 + " has won "+ p1w + " times");
                    System.out.println(p2 + " has won "+ p2w + " times");
                } else if (state == State.Draw) {
                    System.out.println("It's a Draw!");
                    nog+=1;
                    System.out.println("You have played "+nog+" times:");
                    System.out.println(p1 + " has won "+ p1w + " times");
                    System.out.println(p2 + " has won "+ p2w + " times");
                }
                System.out.println("Do you wanna play again?(y/Y for yes)");
                String reply=in.next();
                char answer=reply.charAt(0);//get the first char of reply
                if (answer=='Y' || answer=='y'){//wanna play again, reset board cells
                    NewGame();//here we reset the status back to playing so the loop will continue
                    board.draw();
                } else {
                    System.out.println("Bye!");//after this, our while loop will end, //
                    // so the system will exist, which means end the game//
                }
            }else {
                if (currentplayer==pawntype.X){//if current player is p1, we will change to p2
                    // ; vice versa//
                    currentplayer=pawntype.O;
                }else{
                    currentplayer=pawntype.X;
                }
            }

        }
    }

    public void initGame() {
        board = new board(boards);
    } //set up a new board

    public void NewGame() {
        board.newGame();//clear all cells (specific code in board)
        currentplayer = pawntype.X;//whatever current player is, reset to X
        //if we change it to O, then we can start with O, so we can change the order if we want
        state = State.Playing;//not end, but playing right now
    }

    public void update() {//update the board and status of the game after every "valid" move
        boolean valid = false;
        while (!valid) {
            String icon = currentplayer.getIcon();//get the current player's icon(X or O)
            String player ;
            if (icon == "X") {//get player's name for further use
                player = pp1;
            } else {
                player = pp2;
            }
            System.out.print(player + " with " + icon + ", which location do you want to place?" +
                    " (enter a number from (row[0 to " + (boards - 1) + "]," +
                    " col [0 to " + (boards - 1) + "])");
            int row = in.nextInt();
            int col = in.nextInt();
            if (row > boards - 1 || col > boards - 1 || board.realboard[row][col].isOccupied
                    ||row<0||col<0) {//to avoid that the enter
                // is invalid like (-1,0), or the cell entered is already occupied//
                System.out.println("Not Valid, Please enter another location!");
            } else {
                state= board.endgame(currentplayer,row,col);//check if the game is over,
                // if output is not playing, the result will come out and the while loop
                // will break after this move//
                board.draw();//even if we win the game, we still need to draw the board out
                valid=true;
            }
        }
    }
    public static void main(String[] args) {
        new Main();//run main function as we mentioned above
}
}