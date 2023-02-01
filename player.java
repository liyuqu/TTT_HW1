public class player {

    private int number;
    private String name;
    private pawntype type;

    private int wintime=0;

    public player(int number){
        this.number=number;
    }

    public pawntype getType(){
        return type;
    }
    public void setname(String name){
        this.name=name;
    }
    public String getname(){
        return name;
    }
    public void setType(pawntype type){
        this.type=type;
    }

    public void WIN(){
        this.wintime+=1;
    }
    public int getWintime(){
        return wintime;
    }
}
