package omnia.adu.ac.ae.candystore;

import androidx.annotation.NonNull;

public class Candy {
    private int id;
    private String name;
    private float price;

    public Candy(int id, String name, float price) {
        setId(id);
        setName(name);
        setPrice(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id > 0) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isEmpty()) {
            this.name = name;
        }
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if(price > 0) {
            this.price = price;
        }
    }

    @NonNull
    @Override
    public String toString()
    {
        return " " + id + ", " + name + ", " + price;
    }
}
