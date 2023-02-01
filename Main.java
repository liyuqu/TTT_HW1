import java.util.Scanner;
public class Main {
    private board board;//our board

    private State state;//the status

    private player currentplayer;//the pawntype of the current player

    private static Scanner in = new Scanner(System.in);

    private int boards; //here the int we use is the size of our board,
    // which will be asked to the player later on

    private String pp1;//Name of p1

    private String pp2;//Name of p2

    private player player1;

    private player player2;

    private int order=1;//this defines the current player and number
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
                if (state == State.Win) {
                    System.out.println(currentplayer.getname() + " Win! Congratulations!");
                    currentplayer.WIN();
                    System.out.println("You have played " + board.NoG() + " times:");
                    System.out.println(player1.getname() + " with (" + player1.getType().geticon()+")"
                            + " has won "+ player1.getWintime() + " times");
                    System.out.println(player2.getname() + " with (" + player2.getType().geticon()+")"
                            + " has won "+ player2.getWintime() + " times");
                } else if (state == State.Draw) {
                    System.out.println("It's a Draw!");
                    System.out.println("You have played "+board.NoG()+" times:");
                    System.out.println(player1.getname() + " with (" + player1.getType().geticon()+")"
                            + " has won "+ player1.getWintime() + " times");
                    System.out.println(player2.getname() + " with (" + player2.getType().geticon()+")"
                            + " has won "+ player2.getWintime() + " times");
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
            }

        }
    }

    public void initGame() {
        board = new board(boards);
        player1=new player(1);
        player2=new player(2);
        player1.setname(pp1);
        player2.setname(pp2);
        player1.setType(pawntype.X);
        player2.setType(pawntype.O);
    } //set up a new board

    public void NewGame() {
        board.newGame();//clear all cells (specific code in board)
        state = State.Playing;//not end, but playing right now
        System.out.print("Which order do you want to play? (enter 1 or 2)");
        int input=in.nextInt();
        while (input!=1&&input !=2){
            System.out.print("Wrong order, please try again (1 or 2)");
            input=in.nextInt();
        }
        order=input;
        if (order==1) {
            currentplayer = player1;
        } else{
            currentplayer = player2;//whatever current player is, reset to player 1 or 2 based on order
        }
        //if we change it to O, then we can start with O, so we can change the order if we want
    }

    public void update() {//update the board and status of the game after every "valid" move
        boolean valid = false;
        while (!valid) {
            String icon = currentplayer.getType().geticon();//get the current player's icon(X or O)
            String player= currentplayer.getname();
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
                state= board.endgame(currentplayer.getType(),row,col);//check if the game is over,
                // if output is not playing, the result will come out and the while loop
                // will break after this move//
                board.draw();//even if we win the game, we still need to draw the board out
                valid=true;
                if (state==State.Playing){
                    if (currentplayer==player1){
                        currentplayer=player2;
                    } else{
                        currentplayer=player1;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        new Main();//run main function as we mentioned above
}
}