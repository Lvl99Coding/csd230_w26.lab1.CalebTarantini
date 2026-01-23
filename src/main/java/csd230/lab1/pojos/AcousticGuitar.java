package csd230.lab1.pojos;

import csd230.lab1.entities.AcousticGuitarEntity;

/**
 * DTO for {@link AcousticGuitarEntity}
 */
public class AcousticGuitar extends Guitar {
    private boolean hasCutaway;

    public AcousticGuitar(){}

    public AcousticGuitar(String brand, String model, int numberOfStrings, boolean hasCutaway, double price) {
        super(brand, model, numberOfStrings, price);
        this.hasCutaway = hasCutaway;
    }

    public boolean getHasCutaway() {
        return hasCutaway;
    }

    public void setHasCutaway(boolean hasCutaway) {
        this.hasCutaway = hasCutaway;
    }


    @Override
    public void edit(){
        super.edit();
        System.out.print("Does it have a cutaway (true/false): ");
        this.hasCutaway = getInput(false);
    }

    @Override
    public void initialize(){
        super.initialize();
        System.out.print("Does it have a cutaway (true/false): ");
        this.hasCutaway = getInput(false);
    }

    @Override
    public void sellItem(){
        System.out.println("Seld Acoustic Guitar: '" + getBrand() + " " + getModel() + "'");
    }

    @Override
    public String toString() {
        return "AcousticGuitar{" +
                super.toString() +
                "hasCutaway=" + hasCutaway +
                '}';
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), hasCutaway);
    }
}
