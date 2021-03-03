package edu.unomaha.flightriskassessment.services;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.unomaha.flightriskassessment.models.Metar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class MetarService
{
    private static final Logger logger = LogManager.getLogger(MetarService.class);

    public Metar getMetarData(String airportID)
    {
       logger.info("Beginning to get Metar Data...");
       Metar returnValue = new Metar();
        try {
            //TODO: Store the base URL in a better place.
            String URL = "https://aviationweather.gov/adds/dataserver_current/httpparam?dataSource=metars&requestType=retrieve&format=xml&mostrecent=true&hoursBeforeNow=1&stationString=" + airportID;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);

            doc.getDocumentElement().normalize();

            Node data = doc.getElementsByTagName("data").item(0);
            System.out.println("data attribute: "+data.getAttributes().item(0));

            //All the metar data is kept in a <METAR> tag, so we are just getting the next layer
            NodeList children = data.getChildNodes().item(0).getChildNodes();

            System.out.println("children length: "+children.getLength());

            for(int i = 0; i < children.getLength(); i++)
            {
                Node temp = children.item(i);
                logger.info("Item i: "+temp);
                if(temp.getLocalName().equals("raw_text")){
                    returnValue.setRawText(temp.getTextContent());
                }

            }
            //if(doc.getElementsByTagName("data").)
            //String rawText = doc.getElementsByTagName()

        } catch ( ParserConfigurationException e )
        {
            e.printStackTrace();
        } catch ( IOException e )
        {
            e.printStackTrace();
        } catch ( SAXException e )
        {
            e.printStackTrace();
        }

        return new Metar();
    }

}
