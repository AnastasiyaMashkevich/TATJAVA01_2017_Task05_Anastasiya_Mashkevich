package by.epam.parser.DOM;

import by.epam.bean.Dish;
import by.epam.bean.Menu;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Runner {
    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main (String [] arg) {
        DOMParser parser = new DOMParser();
        try {
            parser.parse("bean.xml");
        } catch (org.xml.sax.SAXException e){
            log.error("SAXException");
        } catch ( IOException e) {
            log.error("IOException");
        }

        Document document = parser.getDocument();
        Element root = document.getDocumentElement();
        List<Menu> listOfMenu = new ArrayList<Menu>();
        NodeList menuNodes = root.getElementsByTagName("mn:Menu");
        Menu menu;
        Dish dish;


        for (int i = 0; i < menuNodes.getLength(); i++) {
            menu = new Menu();
            Element menuElement = (Element) menuNodes.item(i);
            menu.setId(Integer.parseInt(menuElement.getAttribute("ID")));
            menu.setNameOfMenu(getSingleChild(menuElement, "mn:NameOfMenu").getTextContent().trim());

            NodeList dishNodes = root.getElementsByTagName("mn:Dish");
            for (int j = 0; j < dishNodes.getLength(); j++) {
                dish = new Dish();
                Element dishElement = (Element) dishNodes.item(i);
                dish.setId(Integer.parseInt(menuElement.getAttribute("ID")));
                dish.setFoto(getSingleChild(menuElement, "mn:Foto").getTextContent().trim());
                dish.setTitle(getSingleChild(menuElement, "mn:Title").getTextContent().trim());
                dish.setDescription(getSingleChild(menuElement, "mn:Description").getTextContent().trim());
                dish.setPortion(getSingleChild(menuElement, "mn:Portion").getTextContent().trim());
                if (getSingleChild(menuElement, "mn:Price").getTextContent().length() > 0) {
                    dish.setPrice(Integer.parseInt(getSingleChild(menuElement, "mn:Price").getTextContent().trim()));
                }
                menu.addDish(dish);
            }
            listOfMenu.add(menu);
        }
        //System.out.print(listOfMenu.size());
    }

    public static Element getSingleChild (Element element, String childName) {
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }
}
