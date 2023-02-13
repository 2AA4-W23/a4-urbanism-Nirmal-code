package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public abstract class MeshADT {

    private List<Polygon> mesh;
    protected int precision;
    protected int height;
    protected int width;
    protected static List<CustomVertex> vertices;


    protected static List<CustomVertex> centroids;
    public static List<CustomSegments> segments;

    public MeshADT(){
        this.mesh=new ArrayList<>();

    }

    public MeshADT(int width, int height, int precision){
        this.mesh = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.precision = precision;
    }

    public List<Polygon> getMesh(){
        return this.mesh;
    }


    public void addPolygon(Polygon polygon){
        this.mesh.add(polygon);
    }
    
    public void addVertex(CustomVertex vertex)
    {
        vertices.add(vertex);
    }
    
    public List<Vertex> getVertices(){
        List<Vertex> reg_vertices=new ArrayList<>();

        for (CustomVertex v: vertices){
            reg_vertices.add(v.getVertex());
        }
        return reg_vertices;
    }

    public List<Segment> getSegments(){
        List<Segment> reg_segments=new ArrayList<>();

        for (CustomSegments s: segments){
            reg_segments.add(s.getSegment());
        }
        return reg_segments;
    }

    public List<Vertex> getCentroids(){
        List<Vertex> reg_centroids=new ArrayList<>();

        for (CustomVertex v:centroids){
            reg_centroids.add(v.getVertex());
        }

        return reg_centroids;
    }

    public int getPrecision(){
        return precision;
    }

    public int getWidth()
    {
        return width;
    }
    
    public int getHeight(){
        return height;
    }



}
