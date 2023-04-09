package ca.mcmaster.cas.se2aa4.a3.island.CityTesting;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandEdge;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.IslandNode;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.SegmentObserver;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.VertexObserver;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.SegmentElement;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObserverTest {

    static TileVertex v;
    static TileVertex v2;
    static IslandNode n;
    static IslandNode n2;

    static IslandEdge e;

    static TileSegment s;



    @BeforeAll
    public static void setup(){
        Structs.Vertex new_v= Structs.Vertex.newBuilder().setX(5.0).setY(2.0).build();
        Structs.Vertex new_v2= Structs.Vertex.newBuilder().setX(3.0).setY(2.0).build();

        v=new TileVertex(new_v);
        v2=new TileVertex(new_v2);
        n=new IslandNode("A",5.0, v.getVertexElement());
        n2=new IslandNode("B",1.0, v2.getVertexElement());

        Structs.Segment seg=  Structs.Segment.newBuilder().setV1Idx(0).setV2Idx(1).build();

        s=new TileSegment(seg,Arrays.asList(new_v,new_v2),0);

        e=new IslandEdge(n,n2,10.0,s.getElement());


    }

    @Test
    public void VertexObserver(){
        VertexObserver observer=new VertexObserver(n);
        n.attatch(observer);
        v.setObserver(observer);

        n.setTerrain(VertexElement.SMALL_CITY);
        n.alert();

        try{
            Method t= TileVertex.class.getDeclaredMethod("updateValues");
            t.setAccessible(true);
            try{
                t.invoke(v);
                assertTrue(v.getVertexElement().equals(n.getTerrain()));
            }catch (IllegalAccessException e){
                throw new RuntimeException();
            }catch (InvocationTargetException c){
                throw new RuntimeException(c);
            }
        }catch (NoSuchMethodException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void SegmentObserver(){

        VertexObserver observer=new VertexObserver(n);
        n.attatch(observer);
        v.setObserver(observer);

        VertexObserver observer2=new VertexObserver(n);
        n2.attatch(observer2);
        v2.setObserver(observer2);

        SegmentObserver observer3=new SegmentObserver(e);
        e.attatch(observer3);
        s.setObserver(observer3);

        e.setTerrain(SegmentElement.ROAD);
        e.alert();

        assertTrue(e.getTerrain().equals(SegmentElement.ROAD));
    }

}
