package onion.nmap.builder.results;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class ScanResultNmap {

    public ScanResultNmap(String pathToFileXML) throws JAXBException {

        File fileXML = new File(pathToFileXML);

        if(!fileXML.exists()) {
            throw new RuntimeException("Результаты сканирования не найдны: " + pathToFileXML);
        }

        System.out.println(XmlResultNmap.class);

        JAXBContext jaxbContext = JAXBContext.newInstance(XmlResultNmap.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        XmlResultNmap xmlResultNmap = (XmlResultNmap) jaxbUnmarshaller.unmarshal(new File(pathToFileXML));

        System.out.println(xmlResultNmap);
    }
}
