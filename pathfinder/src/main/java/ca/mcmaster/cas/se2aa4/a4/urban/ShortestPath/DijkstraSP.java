package ca.mcmaster.cas.se2aa4.a4.urban.ShortestPath;

import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Edge;
import ca.mcmaster.cas.se2aa4.a4.urban.BuildingBlocks.Node;
import ca.mcmaster.cas.se2aa4.a4.urban.GraphADT.GraphADT;

import java.util.*;

public class DijkstraSP implements ShortestPath {

    HashMap<Node, Node> all_sp;
    HashMap<Node, Double> cost;
    PriorityQueue<Node> queue;
    List<Edge> node_sp;
    Node start;
    Node end;

    Comparator<Node> new_comparator = new Comparator<Node>() {
        @Override
        public int compare(Node n1, Node n2) {
            return (int)(cost.get(n1)-cost.get(n2));
        }
    };

    public DijkstraSP(){
        this.all_sp =new HashMap<>();
        this.node_sp =new ArrayList<>();
        this.cost=new HashMap<>();
        this.queue=new PriorityQueue<>(new_comparator);
    }

    public void generate(GraphADT graph, Node s, Node e) {

        if (graph.getNodes().contains(s) & graph.getNodes().contains(e)){
            for (Node n: graph.getNodes()){
                if (s.equals(n)){
                    this.start=n;
                }else if (n.equals(e)){
                    this.end=n;
                }
            }
        }else{
            return;
        }

        for (Node n: graph.getNodes()){
            all_sp.put(n, null);
        }
        for (Node n: graph.getNodes()){
            cost.put(n, Double.MAX_VALUE);
        }

        all_sp.put(start,start);
        cost.put(start,0.0);
        queue.add(start);

        while (!queue.isEmpty()){
            Node temp=queue.remove();
            for (Edge edge: temp.getEdges()){
                Node next=edge.getN2();
                if (cost.get(temp)+edge.getWeight()<cost.get(next)){
                    cost.put(next, cost.get(temp)+edge.getWeight());
                    all_sp.put(next,temp);
                    queue.add(next);
                }
            }
        }
        this.node_sp=findPathEdges(this.start, this.end, all_sp);
    }


    private List<Edge> findPathEdges(Node start, Node end, HashMap<Node,Node> all_path){

        List<Edge> node_path=new ArrayList<>();

        //Since each node references previous node, we must start at the last node.
        Node curr_node=end;
        //Will be used to reference the next (previous) node.
        Node next_node;


        while (curr_node!=start & curr_node!=null) {

            //Finds the next node in the path.
            next_node=all_path.get(curr_node);

            if (next_node!=null){
                for (Edge e : next_node.getEdges()) {
                    if (e.getN2().equals(curr_node)) {
                        node_path.add(0,e);
                    }
                }
            }else{
                node_path.clear();
                return node_path;
            }
            curr_node = next_node;
        }
        return node_path;

    }

    public List<Edge> getPath() {
        return this.node_sp;
    }

    public Double getCost(){
        return cost.get(end);
    }
}
