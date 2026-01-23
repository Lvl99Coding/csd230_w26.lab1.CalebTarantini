package csd230.lab1.pojos;

import csd230.lab1.entities.ElectricGuitarEntity;

/**
 * DTO for {@link ElectricGuitarEntity}
 */
public class ElectricGuitar extends Guitar{
    private int numberOfPickups;

    public ElectricGuitar(){

    }

    public ElectricGuitar(String brand, String model, int numberOfStrings, int numberOfPickups, double price) {
        super(brand, model, numberOfStrings, price);
        this.numberOfPickups = numberOfPickups;
    }

    @Override
    public void edit(){
        super.edit();
        System.out.println("Edit number of pickups [enter for no changes]:");
        this.numberOfPickups = getInput(this.numberOfPickups);
    }

    @Override
    public void initialize(){
        super.initialize();
        System.out.println("Enter number of pickups:");
        this.numberOfPickups = getInput(0);
    }

    @Override
    public void sellItem(){
        System.out.println("Selling Electric Guitar: '" + this.getBrand() + " " + this.getModel() + "'");
    }

    @Override
    public String toString() {
        return "ElectricGuitar{" +
                super.toString() +
                "numberOfPickups=" + numberOfPickups +
                '}';
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), numberOfPickups);
    }
}
