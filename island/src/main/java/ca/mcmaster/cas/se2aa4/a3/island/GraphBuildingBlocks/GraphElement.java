package ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks;

public abstract class GraphElement {

    Observer observer;

    public void attatch(Observer observer){
        this.observer=observer;
    }

    public void alert(){
        this.observer.update();
    }
}
