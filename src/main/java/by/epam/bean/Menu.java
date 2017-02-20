package by.epam.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Menu implements Serializable {

    private int id;
    private String nameOfMenu;
    private List<Dish> listOfDish = new ArrayList<Dish>();

    public Menu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfMenu() {
        return nameOfMenu;
    }

    public void setNameOfMenu(String nameOfMenu) {
        this.nameOfMenu = nameOfMenu;
    }

    public List<Dish> getListOfDish() {
        return listOfDish;
    }

    public void setListOfDish(List<Dish> listOfDish) {
        this.listOfDish = listOfDish;
    }

    public void addDish (Dish dish) {
        this.listOfDish.add(dish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (!nameOfMenu.equals(menu.nameOfMenu)) return false;
        return listOfDish.equals(menu.listOfDish);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nameOfMenu.hashCode();
        result = 31 * result + listOfDish.hashCode();
        return result;
    }


}
