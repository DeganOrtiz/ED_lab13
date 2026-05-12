package ed.lab.ed1labo04.model;

public class UpdateProductRequest {
    private Double price;
    private int quantity;

    public void setPrice(Double price){
        this.price = price;
    }
    public Double getPrice(){
        return price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getQuantity(){
        return quantity;
    }

}
