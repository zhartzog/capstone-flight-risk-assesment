package edu.unomaha.flightriskassessment.services;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.unomaha.flightriskassessment.models.Metar;
import edu.unomaha.flightriskassessment.models.Taf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;

@Service
public class AWCServices
{
    private static final Logger logger = LogManager.getLogger(AWCServices.class);

    private DocumentBuilder builder;

    public AWCServices()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            this.builder = factory.newDocumentBuilder();
        }
        catch ( ParserConfigurationException e )
        {
            e.printStackTrace();
        }
    }

    private Document getDocument(String URL)
    {
        Document doc = null;
        try
        {
            doc = builder.parse(URL);
        } catch ( SAXException e )
        {
            e.printStackTrace();
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();

        return doc;
    }

    public Metar getMetarData(String airportID)
    {
        try
        {
            String URL = "https://aviationweather.gov/adds/dataserver_current/httpparam?dataSource=metars&requestType=retrieve&format=xml&mostrecent=true&hoursBeforeNow=1&stationString=" + airportID;

            Document doc = getDocument(URL);

            Node data = doc.getElementsByTagName("data").item(0);

            if(data.getAttributes().equals("num_results=\"1\""))
            {
                Node metarData = doc.getElementsByTagName("METAR").item(0);

                JAXBContext jaxbContext = JAXBContext.newInstance(Metar.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                return (Metar) jaxbUnmarshaller.unmarshal(metarData);
            }
            else{
                logger.error("Metar Request did not return a valid result");
                return null;
            }
        }
        catch ( JAXBException e )
        {
            e.printStackTrace();
        }

        return null;
    }


    public Taf getTafData(int radius, String latLong) //-95.755454,41.259877
    {
        logger.debug("Beginning getTafData...");
        try
        {
            String URL = "https://aviationweather.gov/adds/dataserver_current/httpparam?dataSource=tafs&requestType=retrieve&format=xml&radialDistance="+radius+";"+latLong+"&hoursBeforeNow=3&mostRecent=true";

            Document doc = getDocument(URL);

            Node tafData = doc.getElementsByTagName("TAF").item(0);

            JAXBContext jaxbContext = JAXBContext.newInstance(Taf.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (Taf) jaxbUnmarshaller.unmarshal(tafData);
        }
        catch ( JAXBException e )
        {
            e.printStackTrace();
        }

        return null;
    }

}
