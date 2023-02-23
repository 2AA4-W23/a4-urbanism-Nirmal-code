package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeoStruct {
    //Will store the vertices of polygon in customvertex form.
    protected List<CustomVertex> new_poly_vertex;

    //Stores the Polygon obtained from voronoi algorithm.
    protected Polygon geoPolygon;

    //Stores the CustomPolygon equivalent
    protected CustomPolygon cusPolygon;

    private boolean isValid;

    private List<CustomVertex> vertices=MeshADT.getAllCustomVertices();
    private int precision=MeshADT.getPrecision();



    public GeoStruct(Polygon init_poly, int newIndex, List<Integer> indexNeighbourCentroids){

        this.geoPolygon=init_poly;

        this.new_poly_vertex=getCustomVertices();



        if (new_poly_vertex.size() >= 3) //polygon is valid if there are atleast 3 vertex
        {
            isValid = true;

            this.cusPolygon=new CustomPolygon(new_poly_vertex,newIndex,indexNeighbourCentroids);
        }
        else{
            isValid = false;
        }

    }

    public List<CustomVertex> getCurrVertices(){
        return this.new_poly_vertex;
    }

    public boolean isPolygon(){
        return isValid;
    }

    //returns the custom Polygon that has been fully converted from the geo.Polygon.
    public CustomPolygon getCusPolygon(){
        return this.cusPolygon;
    }



    public List<CustomVertex> getCustomVertices(){
        //List that will store the vertices of this polygon.
        List<CustomVertex> curr_vertices=new ArrayList<>();

        //Iterates through all vertices for that polygon.
        for (Coordinate i: geoPolygon.getCoordinates()){
            //Temporary fix until I can figure out why width won't work
            double x=i.getX();
            double y=i.getY();

            CustomVertex new_vertex=checkVertex(new CustomVertex(x,y,precision));

            curr_vertices.add(new_vertex);
        }



        return curr_vertices;

    }

    //Used to check whether vertex has been added before.
    //Won't work if we try to eliminate duplicates in CustomPolygon for some reason. Index out of bound error
    //Need to look into.
    public CustomVertex checkVertex(CustomVertex v){
        for (CustomVertex vertex:vertices){
            if (vertex.x==v.x & vertex.y==v.y){
                return vertex;
            }
        }
        vertices.add(v);
        return v;
    }
}
