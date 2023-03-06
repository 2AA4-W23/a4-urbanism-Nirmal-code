package ca.mcmaster.cas.se2aa4.a3.island;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;

public abstract class Mode {
    String inputMesh;
    String outputMesh;
    ShapeType shape;
    Mesh mesh;
    List<Polygon> polygons;
    List<Segment> segments;
    List<Vertex> vertices;
    List<Tile> tiles;
    List<TileSegment> allSegmentInfoList;
    List<TileSegment> segmentInfoList;
    List<TileSegment> neighbouringSegmentInfoList;
    List<TileVertex> verticesInfoList;
    List<TileVertex> centroidInfoList;
    double width;
    double height;

    public Mode(String inputMesh, String outputMesh, ShapeType shape){
        this.inputMesh = inputMesh;
        this.outputMesh = outputMesh;
        this.shape = shape;

        this.tiles = new ArrayList<>();
        this.allSegmentInfoList = new ArrayList<>();
        this.segmentInfoList = new ArrayList<>();
        this.neighbouringSegmentInfoList = new ArrayList<>();
        this.verticesInfoList = new ArrayList<>();
        this.centroidInfoList = new ArrayList<>();
        width = Double.MIN_VALUE;
        height = Double.MIN_VALUE;
    }

    protected void extractInformation() throws IOException{
        //Extract the information about polygons, segments and vertices from the input mesh
        mesh = new MeshFactory().read(inputMesh);
        polygons = mesh.getPolygonsList();
        segments = mesh.getSegmentsList();
        vertices = mesh.getVerticesList();

        //extract polygon information
        for (Polygon polygon : polygons) {
            tiles.add(new Tile(polygon, segments, vertices, polygons.size()));
        }

        //extract segment information
        String type;
        TileSegment tileSegment;
        for (Segment segment: segments){
            Property segmentType = segment.getProperties(2);
            type = segmentType.getValue();
            if (type.equals("Regular")){
                tileSegment = new TileSegment(segment, vertices, polygons.size());
                segmentInfoList.add(tileSegment);
                allSegmentInfoList.add(tileSegment);
            }
            else if (type.equals("Neighbouring")){
                tileSegment = new TileSegment(segment, vertices, 0);
                neighbouringSegmentInfoList.add(tileSegment);
                allSegmentInfoList.add(tileSegment);
            }
            else{
                throw new IOException("Invalid segment type");
            }
        }

        //extract vertices information
        for(Vertex vertex : vertices){
            Property vertexType = vertex.getProperties(2);
            type = vertexType.getValue();
            if (type.equals("Regular")){
                verticesInfoList.add(new TileVertex(vertex));
            }
            else if (type.equals("Centroid")){
                centroidInfoList.add(new TileVertex(vertex));
            }
            else{
                throw new IOException("Invalid vertex type");
            }
        }

        //Polygons and its centroids are stored in the same order
        for (int i = 0; i < tiles.size(); i++){
            tiles.get(i).setCentroid(centroidInfoList.get(i));
        }

        //Find the height and width of the mesh
        for (TileVertex v: verticesInfoList) {
            width = (Double.compare(width, v.getX()) < 0? v.getX(): width);
            height = (Double.compare(height, v.getY()) < 0? v.getY(): height);
        }

    }

    public Mesh getMesh(){
        //Convert from custom shapes to Struct shapes
        this.polygons = new ArrayList<>();
        this.segments = new ArrayList<>();
        this.vertices = new ArrayList<>();
        List<Vertex> centroids = new ArrayList<>();

        //Polygons
        for (Tile tile: tiles){
            polygons.add(tile.getPolygon());
        }

        //Segments
        for (TileSegment tileSegment: allSegmentInfoList){
            segments.add(tileSegment.getSegment());
        }

        //Vertices
        for (TileVertex tileVertex: centroidInfoList){
            centroids.add(tileVertex.getVertex());
            vertices.add(tileVertex.getVertex());
        }

        for (TileVertex tileVertex: verticesInfoList){
            vertices.add(tileVertex.getVertex());
        }

        
        return Mesh.newBuilder().addAllPolygons(polygons).addAllSegments(segments).addAllVertices(centroids).addAllVertices(vertices).build();
    }
}