package ed.lab.ed1labo04.model;

public class CreateProductRequest {
    private String name;
    private Double price;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPrice(Double price){
        this.price = price;
    }
    public Double getPrice(){
        return price;
    }


}
