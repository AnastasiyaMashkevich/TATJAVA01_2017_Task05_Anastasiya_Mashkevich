package by.epam.parser.StAX;

import by.epam.bean.Dish;
import by.epam.bean.Menu;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class StAXMenuParser {
    private static final Logger log = LogManager.getLogger(StAXMenuParser.class);

    public static void main(String[] args) throws FileNotFoundException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream input = new FileInputStream("bean.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            List<Menu> listOfMenu = process(reader);
            //System.out.println(listOfMenu.size());
        } catch (XMLStreamException e) {
            log.error("XMLStreamException");
        }
    }

    private static List<Menu> process(XMLStreamReader reader) throws XMLStreamException {
        List<Menu> listOfMenu = new ArrayList<Menu>();
        Menu menu = null;
        Dish dish = null;
        MenuTagName elementName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case MENU:
                            menu = new Menu();
                            menu.setId(Integer.parseInt(reader.getAttributeValue(null, "ID")));
                            break;
                        case DISH:
                            dish = new Dish();
                            dish.setId(Integer.parseInt(reader.getAttributeValue(null, "ID")));
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case NAMEOFMENU:
                            menu.setNameOfMenu(text.toString());
                            break;
                        case FOTO:
                            dish.setFoto(text.toString());
                            break;
                        case TITLE:
                            dish.setTitle(text.toString());
                            break;
                        case DESCRIPTION:
                            dish.setDescription (text.toString());
                            break;
                        case PORTION:
                            dish.setPortion (text.toString());
                            break;
                        case PRICE:
                            if(text.length()>0) {
                                dish.setPrice(Integer.parseInt(text.toString()));
                            }
                                break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case MENU:
                            listOfMenu.add(menu);
                            break;
                        case DISH:
                            menu.addDish(dish);
                            break;
                    }
                    break;
            }
        }
        return listOfMenu;
    }
}




