package edu.unomaha.flightriskassessment.services;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Service
public class AWCServices
{
    private static final Logger logger = LogManager.getLogger(AWCServices.class);


    public Metar getMetarData(String airportID)
    {
        try
        {
            String URL = "https://aviationweather.gov/adds/dataserver_current/httpparam?dataSource=metars&requestType=retrieve&format=xml&mostrecent=true&hoursBeforeNow=1&stationString=" + airportID;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);

            doc.getDocumentElement().normalize();

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
        catch (  MalformedURLException mue )
        {
            mue.printStackTrace();
        }
        catch ( ParserConfigurationException e )
        {
            e.printStackTrace();
        } catch ( IOException e )
        {
            e.printStackTrace();
        } catch ( SAXException e )
        {
            e.printStackTrace();
        }

        return null;
    }


}
