package by.epam.bean;

import java.io.Serializable;


public class Dish implements Serializable{
    private int id;
    private String foto;
    private String title;
    private String description;
    private String portion;
    private int price;

    public Dish() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (id != dish.id) return false;
        if (price != dish.price) return false;
        if (!foto.equals(dish.foto)) return false;
        if (!title.equals(dish.title)) return false;
        if (!description.equals(dish.description)) return false;
        return portion.equals(dish.portion);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + foto.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + portion.hashCode();
        result = 31 * result + price;
        return result;
    }
}
