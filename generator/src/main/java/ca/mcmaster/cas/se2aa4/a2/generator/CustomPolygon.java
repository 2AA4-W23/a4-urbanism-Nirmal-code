package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CustomPolygon{

    private List<CustomVertex> poly_vertices;
    private List<CustomSegments> poly_segment;
    private List<Integer> segment_index;
    //Stores the indexes of segments that represent neighbour relation.
    private List<Integer> neighbours;
    private CustomVertex centroid;
    private Integer centroid_idx;
    //Determines whether polygon is regular or not.
    private Boolean isRegular;
    private Polygon polygon;
    //Gets the list of all current centroids, vertices and segments from MeshADT.
    private List<CustomVertex> centroids=MeshADT.getCustomCentroids();
    private List<CustomVertex> vertices=MeshADT.getAllCustomVertices();
    private List<CustomSegments> segments=MeshADT.getCustomSegments();
    private int precision=MeshADT.getPrecision();



    //Constructor for regular polygons.
    public CustomPolygon(int centroid, int squareSize){
        this.isRegular=true;

        this.neighbours=new ArrayList<>();
        this.centroid=MeshADT.getCustomCentroids().get(centroid);
        this.centroid_idx=centroid;

        this.poly_vertices= makeRegVertices(squareSize);
        this.poly_segment=makeSegments(poly_vertices);

        this.segment_index=getSegmentIndex(this.poly_segment);
        this.polygon=makePolygon();
    }


    //Constructor for irregular polyons.
    public CustomPolygon(List<CustomVertex> cusVertices, int newCentroidIndex, List<Integer> indexNeighbourCentroids){
        this.isRegular=false;

        this.neighbours=new ArrayList<>();

        //sets the centroid values for this polygon.
        this.centroid=centroids.get(newCentroidIndex);
        this.centroid_idx=newCentroidIndex;

        //Sets all vertex values which were generated by Voronoi.
        this.poly_vertices=cusVertices;

        //Creates segments between vertices. Directly checks for overlap.
        this.poly_segment=makeSegments(poly_vertices);

        //Indexes segment locations in segments list.
        this.segment_index=getSegmentIndex(this.poly_segment);

        //Creates the neighbour segments
        this.neighbours= makeIregNeighbour(indexNeighbourCentroids);

        //struct.polygon converter
        this.polygon=makePolygon();
    }


    //Generates all vertices for regular polygon.
    private List<CustomVertex> makeRegVertices(int squareSize){
        double offset = squareSize / 2;

        CustomVertex v1=makeVertex(centroid.getX()-offset, centroid.getY()-offset);
        CustomVertex v2=makeVertex(centroid.getX()+offset, centroid.getY()-offset);
        CustomVertex v3=makeVertex(centroid.getX()+offset, centroid.getY()+offset);
        CustomVertex v4=makeVertex(centroid.getX()-offset, centroid.getY()+offset);


        return Arrays.asList(v1,v2,v3,v4);
    }


    //Returns the indexes of for polygon segments from general segment list.
    public List<Integer> getSegmentIndex(List<CustomSegments> partSegments){
        List<Integer> indexes=new ArrayList<>();
        for (CustomSegments s: partSegments){
            indexes.add(segments.indexOf(s));
        }
        return indexes;
    }

    //Generates all segments in a consecutive order. Convex Haul already implemented using this.
    private List<CustomSegments> makeSegments(List<CustomVertex> all_vertices){
        List<CustomSegments> curr_segments=new ArrayList<>();
        for (int i=1; i<all_vertices.size(); i++){
            CustomSegments s=makeSegment(vertices.indexOf(all_vertices.get(i-1)),vertices.indexOf(all_vertices.get(i)));
            curr_segments.add(s);
        }
        CustomSegments s=makeSegment(vertices.indexOf(all_vertices.get(0)),vertices.indexOf(all_vertices.get(all_vertices.size()-1)));
        curr_segments.add(s);
        return curr_segments;
    }


    //Creates all neighbouring segments for IrregularMesh.
    private List<Integer> makeIregNeighbour(List<Integer> indexNeighbourCentroids){
        List<Integer> all_neighbours=new ArrayList<>();
        for (Integer i: indexNeighbourCentroids){
            CustomSegments new_s=new CustomSegments(this.centroid_idx,i,this.centroid, centroids.get(i), "0.5f");
            segments.add(new_s);
            all_neighbours.add(segments.indexOf(new_s));
        }
        return all_neighbours;
    }


    //Creates and returns copy of polygon in type struct.Polygon.
    private Polygon makePolygon(){
        return Polygon.newBuilder().addAllSegmentIdxs(this.segment_index).addAllNeighborIdxs(this.neighbours).build();
    }


    //Creates vertex for Polygon if not already made in vertices. If already made, it returns the one already made.
    private CustomVertex makeVertex(double x, double y){
        CustomVertex v=new CustomVertex(x,y,precision);

        for (CustomVertex c: vertices){
            if (c.getX()==v.getX() & c.getY()==v.getY()){
                return c;
            }
        }

        vertices.add(v);
        return v;
    }

    //Creates segment is not already made. If already made, returns the one that was made.
    private CustomSegments makeSegment(int v1, int v2){
        CustomSegments s=new CustomSegments(v1,v2,vertices.get(v1),vertices.get(v2), "0.5f", this.centroid_idx);
        for (CustomSegments c: segments){
            //If segment already made, then this polygon is neighbour to the one whose segment is already made. For Regular Polygon only.
            if ((c.getV1() ==v1 & c.getV2() ==v2 | c.getV2() ==v1 & c.getV1() ==v2)){
                if (isRegular){
                    CustomSegments new_s=new CustomSegments(this.centroid_idx,c.getCentroid(),this.centroid, centroids.get(c.getCentroid()), "0.5f");
                    segments.add(new_s);
                    this.neighbours.add(segments.indexOf(new_s));
                }
                return c;
            }
        }
        segments.add(s);
        return s;
    }

    public CustomVertex getCentroid(){
        return this.centroid;
    }

    public List<CustomSegments> getPolySegments(){
        return this.poly_segment;
    }

    public List<CustomVertex> getPolyVertices(){
        return this.poly_vertices;
    }

    public Polygon getPolygon(){
        return polygon;
    }

}

