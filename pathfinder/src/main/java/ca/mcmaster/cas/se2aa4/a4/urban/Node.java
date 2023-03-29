package ca.mcmaster.cas.se2aa4.a4.urban;

public class Node {

    private Double x;
    private Double y;
    private String city_name;


    public Node(Double x, Double y, String name){
        this.x=x;
        this.y=y;
        this.city_name=name;
    }

    public Double getX(){
        return this.x;
    }

    public Double getY(){
        return this.y;
    }


}
