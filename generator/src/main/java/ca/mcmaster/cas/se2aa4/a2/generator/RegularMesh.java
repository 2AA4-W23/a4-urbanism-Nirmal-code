package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class RegularMesh extends MeshADT{
    int squareSize;
    CustomVertex[][] centroidVertice;
    CustomVertex[][] connectingVertice;

    public RegularMesh(int width, int height, int precision, int squareSize)
    {
        super(width, height, precision, width*height/squareSize^2);
        this.squareSize = squareSize;
        centroids=new ArrayList<>();
        vertices = new ArrayList<>();
        segments=new ArrayList<>();
        createCentroidVertices();

        //Create a polygon for each vertex
        for (int i=0; i<centroids.size(); i++){
            CustomPolygon polygon=new CustomPolygon(i, precision);
            addPolygon(polygon.getPolygon());
        }
    }

    public void createCentroidVertices(){
        //Create the centroid vertices for grid mesh: equally spaced out
        centroidVertice = new CustomVertex[width/20][height/ 20];
        
        for(int x = 0 ; x*squareSize + squareSize/2 < width; x++) {
            for(int y = 0 ; y*squareSize + squareSize/2 < height; y ++) {
                double xPos = x*squareSize + squareSize/2;
                double yPos = y*squareSize + squareSize/2;
                CustomVertex new_v= new CustomVertex(xPos, yPos ,new Color(254,0,0,254), "2.0", precision);
                centroidVertice[x][y]=new_v;
                centroids.add(new_v);
            }
        }
    }

    public CustomVertex[][] getCentroidVertices(){
        return centroidVertice;
    }

    public CustomVertex getConnectingVertices(int x, int y){
        return connectingVertice[x][y];
    } 
}
