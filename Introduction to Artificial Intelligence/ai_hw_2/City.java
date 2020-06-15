public class City {
    int UID;
    int x;
    int y;

    public City(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
        this.UID = 0;
    }

    public City(int x, int y){
        this.x = x;
        this.y = y;
        this.UID = 0;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getUID(){
        return this.UID;
    }

    public void setUID(int uid){
        this.UID = uid;
    }

    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());

        return Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
    }

    @Override
    public String toString(){
        return "City_" + getUID() + "(" + getX()+", "+getY() + ")";
    }
}