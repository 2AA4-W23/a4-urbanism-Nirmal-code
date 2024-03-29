package ca.mcmaster.cas.se2aa4.a4.pathfinder.EdgeTest;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EdgeTestCases {

    static Edge E;

    @BeforeAll
    public static void setUp(){
        Node N1=new Node("0",4.0);
        Node N2=new Node("1",10.0);

        System.out.println(N1.hashCode());
        System.out.println(N2.hashCode());


        E=new Edge(N1,N2,5.0);
    }

    @Test
    public void testWeight(){
        double real_weight=4.0+10.0+5.0;

        assertTrue(Double.compare(E.getWeight(),real_weight)==0);
    }

    @Test
    public void testFlip(){
        Edge flipped=E.flipEdge();

        assertTrue(flipped.getN1()==E.getN2() & flipped.getN2()==E.getN1());
    }
}
