package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.GraphBuildingBlocks.VertexObserver;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.VertexElement;
import ca.mcmaster.cas.se2aa4.a3.tools.ExtractVertexInfo;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class TileVertex extends ExtractVertexInfo implements TileProperties{
    List<Color> colorList;
    Color averageColor = new Color(0, 0, 0, 0);
    String vertexType;

    VertexObserver observer;

    Double temperature;


    VertexElement vertexElement;
    Double elevation;
    Double thicknessDouble;
    Boolean isRiver;


    public TileVertex(Vertex vertex)
    {
        super(vertex);
        this.elevation=0.0;
        this.temperature=1.0;
        this.thicknessDouble = thickness;
        this.colorList = new ArrayList<>();
        this.vertexElement = VertexElement.LAND;
        this.isRiver = false;
        this.vertexType = extractVertexType(vertex.getPropertiesList());
        setAverageColor();
    }

    public void setColor(Color color){
        this.averageColor = color;
    }

    public void setTileElement(VertexElement v){
        this.vertexElement=v;

    }

    public String getVertexType(){
        return this.vertexType;
    }

    public void setObserver(VertexObserver observer){
        this.observer=observer;
    }

    public VertexObserver getObserver(){
        return this.observer;
    }


    public void setElevation(Double elevation){
        this.elevation=elevation;
    }

    public void setTemperature(Double temperature){
        this.temperature=temperature;
    }

    public Double getTemperature(){
        return this.temperature;
    }

    public Double getElevation(){
        return this.elevation;
    }

    public VertexElement getVertexElement(){
        return this.vertexElement;
    }

    public void setThickness(Double new_thickness){
        this.thicknessDouble=new_thickness;
    }

    public void setRiver(){
        isRiver = true;
    }

    public void setThickness(double thickness){
        this.thicknessDouble = thickness;
    }

    public void setVertexWater(){
        vertexElement = VertexElement.WATER;
    }

    public Boolean isVertexWater(){
        if (vertexElement.equals(VertexElement.WATER)){
            return true;
        }
        return false;
    }

    public Boolean isVertexLand(){
        if (vertexElement.equals(VertexElement.LAND)){
            return true;
        }
        return false;
    }

    private void updateValues(){
        vertexElement=observer.getTerrain();
        if (!isVertexLand() & !isVertexWater()){
            averageColor=vertexElement.getColor();
            thicknessDouble=vertexElement.getSize();
        }else if (isRiver){
            averageColor=new Color(15,94,196,254);
        }else{
            setAverageColor();
        }
    }

    public Vertex getVertex(){
        if (isRiver){
            averageColor=new Color(15,94,196,254);
        }else if (observer!=null){
            updateValues();
        }else{
            setAverageColor();
        }

        String colorCode = averageColor.getRed() + "," + averageColor.getGreen() + "," + averageColor.getBlue() + "," + averageColor.getAlpha();
        Property colorProperty = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        Property thicknessProp = Property.newBuilder().setKey("thickness").setValue(thicknessDouble.toString()).build();
        Property vertexTypeProp = Property.newBuilder().setKey("vertexType").setValue(vertexType).build();
        Property elevationTypeProp = Property.newBuilder().setKey("vertexType").setValue(elevation.toString()).build();
        return Vertex.newBuilder().setX(X).setY(Y).addProperties(0,colorProperty).addProperties(1,thicknessProp).addProperties(2,vertexTypeProp).addProperties(3,elevationTypeProp).build();
    }


    public String extractVertexType(List<Property> properties){
        //Get the "thickness" of the vertex (size)
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("vertexType")) {
                val =  p.getValue();
                break;
            }
        }
        return val;
    }

    public void setAverageColor(){
        //set the average color for the object
        if (colorList.size()!=0 && !averageColor.equals(new Color(0, 0, 0, 0))){
            int red = 0;
            int green = 0;
            int blue = 0;
            int alpha = 0;

            for (Color color: colorList){
                red += color.getRed();
                green += color.getGreen();
                blue += color.getBlue();
                alpha += color.getAlpha();
            }
            
            red /= colorList.size();
            green /= colorList.size();
            blue /= colorList.size();
            alpha /= colorList.size();
            averageColor = new Color(red, green, blue, alpha);
        }
    }    
}
