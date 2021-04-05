public class Tile{
    private Type type;

    protected Maze.Coordinate coords;
    

    private void Tile(Type typee){
        type = typee;
    }

    public enum Type {
        CORRIDOR,ENTRANCE,EXIT,WALL;
    }

    protected static Tile fromChar(char letter){
        Tile a = new Tile();

        if(letter == '.'){
            a.type = Type.CORRIDOR;
            return a;
        }else if(letter == '#'){
            a.type = Type.WALL;
            return a;
        }else if(letter == 'e'){
            a.type = Type.ENTRANCE;
            return a;
        }else if(letter == 'x'){
            a.type = Type.EXIT;
            return a;
        }else{
            return null;
        }

    }

    public Type getType(){
        return type;
    }

    public boolean isNavigable(){
        if(getType()==Type.WALL){
            return false;
        }else{
            return true;
        }

    }

    public String toString(){
        if(getType()==Type.WALL){
            return "#";
        }else if(getType()==Type.ENTRANCE){
            return "e";
        }else if(getType()==Type.EXIT){
            return "x";
        }else if(getType()==Type.CORRIDOR){
            return ".";
        }else{
            return null;
        }
    }




}