package ca.mcmaster.cas.se2aa4.a2.generator;

import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class GeneratePolygon {

    protected List<CustomVertex> centroids;
    protected List<CustomVertex> vertices;
    protected List<CustomSegments> segments;
    private int precision;


    protected CustomVertex centroid;

    protected Integer centroid_idx;

    protected List<CustomVertex> poly_vertices;
    protected List<CustomSegments> poly_segment;
    protected List<Integer> segment_index;
    protected List<Integer> neighbours;


    public GeneratePolygon(){
        this.precision=MeshADT.getPrecision();
        this.centroids=MeshADT.getCustomCentroids();
        this.vertices=MeshADT.getAllCustomVertices();
        this.segments=MeshADT.getCustomSegments();
    }

    abstract CustomSegments makeSegment(int v1, int v2);


    protected List<CustomSegments> makeSegments(List<CustomVertex> all_vertices){
        List<CustomSegments> curr_segments=new ArrayList<>();
        for (int i=1; i<all_vertices.size(); i++){
            CustomSegments s=makeSegment(vertices.indexOf(all_vertices.get(i-1)),vertices.indexOf(all_vertices.get(i)));
            curr_segments.add(s);
        }
        CustomSegments s=makeSegment(vertices.indexOf(all_vertices.get(0)),vertices.indexOf(all_vertices.get(all_vertices.size()-1)));
        curr_segments.add(s);
        return curr_segments;
    }

    public List<Integer> getSegmentIndex(List<CustomSegments> partSegments){
        List<Integer> indexes=new ArrayList<>();
        for (CustomSegments s: partSegments){
            indexes.add(segments.indexOf(s));
        }
        return indexes;
    }

    protected CustomVertex makeVertex(double x, double y){
        CustomVertex v=new CustomVertex(x,y,precision);

        for (CustomVertex c: vertices){
            if (c.getX()==v.getX() & c.getY()==v.getY()){
                return c;
            }
        }
        vertices.add(v);
        return v;
    }


    protected void convexHull(){
        //orders the vertices in order so that the creation of segments will be in order
        List<CustomVertex> orderedVertices = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();

        //Create a Coordinate array
        Coordinate coordinate [] = new Coordinate[this.poly_vertices.size()];

        for (int i = 0; i < coordinate.length; i++){
            CustomVertex vertex = poly_vertices.get(i);
            coordinate[i] = new Coordinate(vertex.getX(), vertex.getY());
        }

        //Compute the Convex Hull
        ConvexHull convexHull = new ConvexHull(coordinate, geometryFactory);
        Geometry polygon = convexHull.getConvexHull();
        coordinate = polygon.getCoordinates();

        //refer the coordinates back to CustomVertex
        for (Coordinate coord: coordinate){
            double x = coord.getX();
            double y = coord.getY();

            //Find the corresponding CustomVertex
            for (CustomVertex point: vertices){
                double pointX = point.getX();
                double pointY = point.getY();
                if (x == pointX && y == pointY && !orderedVertices.contains(point)){
                    point.inMesh();
                    orderedVertices.add(point);
                    break;
                }
            }
        }

        //update the polygon vertex (vertices are now in order)
        this.poly_vertices = orderedVertices;
    }

}
