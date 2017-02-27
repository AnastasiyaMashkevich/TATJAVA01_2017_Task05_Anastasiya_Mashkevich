package by.epam.parser.sax;


import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import by.epam.bean.Menu;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Runner {

    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main (String [] args) throws org.xml.sax.SAXException, IOException, jdk.internal.org.xml.sax.SAXException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource("menu.xml"));
        List<Menu> menu = handler.getMenuList();
        
    }
}
