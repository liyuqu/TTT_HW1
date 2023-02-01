public class board {
    private int boardsize;//the size will enter

    private int gametimes=0;

    pawn[][] realboard;//we set every cell a single one to easily reset them or change them

    public board(int boardsize) {
        this.boardsize = boardsize;
        initGame();
    }

    public void initGame() {
        realboard = new pawn[boardsize][boardsize];//set a new char matrix
        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                realboard[row][col] = new pawn(row, col);//relate to public pawn--the cell inside it should
                //be not occupied, which means every cell's boolean set to false
            }
        }
    }

    public void newGame() {
        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                realboard[row][col].newGame(); //reset the cell(see function in pawn)
            }
        }
        gametimes+=1;
    }

    //then, for everytime we will need to compute if the game is over
    public State endgame(pawntype pp, int enterrow, int entercol) {
        realboard[enterrow][entercol].cell = pp;
        realboard[enterrow][entercol].isOccupied=true;
        //so we only need to think about if the new pawn can take us to the win.
        //first check for col
        if (realboard[enterrow][0].isOccupied&&realboard[enterrow][0].cell == pp) {
            boolean ROW = true;
            for (int i = 0; i < boardsize; i++) {
                if (!realboard[enterrow][i].isOccupied || realboard[enterrow][i].cell != pp) {
                    ROW = false;
                }
            }
            if (ROW) {
                return State.Win;
            }
        }
        // for col
        if (realboard[0][entercol].isOccupied&&realboard[0][entercol].cell == pp) {
            boolean COL = true;
            for (int i = 0; i < boardsize; i++) {
                if (!realboard[i][entercol].isOccupied||realboard[i][entercol].cell != pp) {
                    COL = false;
                }
            }
            if (COL) {
                return State.Win;
            }
        }
        //then for diag//
        if (realboard[0][0].isOccupied&&realboard[0][0].cell == pp) {
            if (enterrow == entercol) {
                boolean DIG1 = true;
                for (int i = 0; i < boardsize; i++) {
                    if (!realboard[i][i].isOccupied || realboard[i][i].cell != pp) {
                        DIG1 = false;
                    }
                }
                if (DIG1) {
                    return State.Win;
                }
            }
        }
        if (realboard[0][boardsize - 1].isOccupied&&realboard[0][boardsize - 1].cell == pp) {
            if (enterrow + entercol == boardsize - 1) {
                boolean DIG2 = true;
                for (int i = 0; i < boardsize; i++) {
                    if (!realboard[i][boardsize - 1 - i].isOccupied || realboard[i][boardsize - 1 - i].cell != pp) {
                        DIG2 = false;
                    }
                }
                if (DIG2) {
                    return State.Win;
                }
            }
        }
        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                if (!realboard[i][j].isOccupied) {
                    return State.Playing;
                }
            }
        }
        return State.Draw;
    }

    // then we need to print the board out//
    public void draw() {
        for (int i = 0; i < boardsize; i++) {
            if (i <= boardsize - 1) {
                int bbs = boardsize;
                while (bbs > 1) {
                    System.out.print("+-+-");
                    bbs -= 1;
                }
                if (bbs == 1) {
                    System.out.println("+-+-+");
                }
            }
                for (int j = 0; j < boardsize; j++) {
                    if (j==0){
                        System.out.print("|");
                    }
                    System.out.print(" ");
                    realboard[i][j].draw();
                    System.out.print(" ");
                    if (j < boardsize - 1) {
                        System.out.print("|");
                    } else if (j == boardsize - 1) {
                        System.out.println("|");
                    }
                }
                if(i==boardsize-1){
                    int bbss = boardsize;
                    while (bbss > 1) {
                        System.out.print("+-+-");
                        bbss -= 1;
                    }
                    if (bbss == 1) {
                        System.out.println("+-+-+");
                    }
                }

                }
            System.out.println();

        }
        public int NoG(){
            return gametimes;
    }
    }
