package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    <O> O importXMl(Class<O> objectClass,String path) throws JAXBException;

    <O> void exportXML(O entity,String path) throws JAXBException;

//    <O> O parseXml(Class<O> objectClass, String filePath) throws Exception;
//
//    <O> void exportXml(O object, Class<O> objectClass, String filePath) throws Exception;
//
//    <O> String exportXml(O object, Class<O> objectClass) throws Exception;
}
