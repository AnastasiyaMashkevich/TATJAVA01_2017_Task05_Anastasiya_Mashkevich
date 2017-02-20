package by.epam.parser.sax;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.*;
import by.epam.bean.Dish;
import by.epam.bean.Menu;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static by.epam.parser.sax.MenuTagName.DISH;
import static by.epam.parser.sax.MenuTagName.MENU;


public class MenuSaxHandler extends DefaultHandler{
    private static final Logger log = LogManager.getLogger(Runner.class);

    private Menu menu;
    private Dish dish;
    private ArrayList<Menu> listOfMenu = new ArrayList<Menu>();
    private StringBuilder text;

    public List<Menu> getMenuList () {
        return listOfMenu;
    }
    @Override
    public void startDocument () throws SAXException {
        log.info("Parsing started. ");
    }
    @Override
    public void endDocument () throws SAXException {
        log.info("Parsing ended. ");
    }
    @Override
    public void characters (char [] buffer, int start, int length) throws SAXException {
        text.append(buffer, start, length);
    }
    @Override
    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-","_").substring(3));

        if (tagName.equals(MENU)){
            menu = new Menu();
            menu.setId(Integer.parseInt(attributes.getValue("ID")));
        }
        if (tagName.equals(DISH)){
            dish = new Dish();
            menu.setId(Integer.parseInt(attributes.getValue("ID")));
        }
    }
    @Override
    public void endElement (String uri, String localName, String qName) throws SAXException {
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-","_").substring(3));
        switch (tagName) {
            case NAMEOFMENU: menu.setNameOfMenu(text.toString()); break;
            case MENU: listOfMenu.add(menu); menu = null;  break;
            case DISH: menu.addDish(dish); menu = null;  break;
            case FOTO: dish.setFoto(text.toString()); break;
            case TITLE: dish.setTitle(text.toString()); break;
            case DESCRIPTION: dish.setDescription(text.toString()); break;
            case PORTION: dish.setPortion(text.toString()); break;
            case PRICE:
                if(text.length()>0) {
                    dish.setPrice(Integer.parseInt(text.toString()));
                }break;
        }
    }
    @Override
    public void warning(SAXParseException exception){
        log.warn("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }
    @Override
    public void error(SAXParseException exception){
        log.error("ERROR: line: " + exception.getLineNumber() + ": " + exception.getMessage());
    }
    @Override
    public void fatalError(SAXParseException exception) throws SAXParseException {
        log.fatal("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
        throw (exception);
    }


}
