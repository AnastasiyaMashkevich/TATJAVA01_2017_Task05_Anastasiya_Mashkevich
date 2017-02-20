package by.epam.parser.StAX;


public enum  MenuTagName {
    ALL_MENU, MENU, NAMEOFMENU, DISH, FOTO, TITLE, DESCRIPTION, PORTION, PRICE;

    public static MenuTagName getElementTagName(String element) {
        switch (element) {
            case "All-Menu":
                return ALL_MENU;
            case "Menu":
                return MENU;
            case "NameOfMenu":
                return NAMEOFMENU;
            case "Dish":
                return DISH;
            case "Foto":
                return FOTO;
            case "Title":
                return TITLE;
            case "Description":
                return DESCRIPTION;
            case "Portion":
                return PORTION;
            case "Price":
                return PRICE;
            default:throw new EnumConstantNotPresentException(MenuTagName.class, element);
        }
    }
}


