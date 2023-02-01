public enum pawntype {
    X("X"),O("O");

    private String icon;

    private pawntype(String icon){
        this.icon=icon;
    }
    public String getIcon(){
        return icon;
    }

}
