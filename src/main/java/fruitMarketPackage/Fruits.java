package fruitMarketPackage;

public enum Fruits {

    STRAWBERRY (10),
    APPLE(7),
    BANANA(5),
    PEAR(5),
    PINEAPPLE(5);

    private int shelfLife;

    Fruits(int shelLife) {
        this.shelfLife = shelLife;
    }

    public int getShelfLife() {
        return shelfLife;
    }


    @Override
    public String toString() {
        return "Fruits{name: " + name()+
                ", shelfLife=" + shelfLife +
                '}';
    }
}
