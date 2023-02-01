public class pawn {
    pawntype cell; //x or o//
    int row;
    int col;
    Boolean isOccupied;

    private String icon;

    public pawn(int row, int col) {
        this.row = row;
        this.col = col;
        this.isOccupied=false;
    }//use for further board or game use


    public void newGame() {
        this.isOccupied = false;
    }//here we start a new game//

    public void draw() {
        if (this.isOccupied) {
            icon = this.cell.geticon();
            System.out.print(icon);//we can use it in board print
        } else {
            System.out.print(" ");
        }
    }
}

