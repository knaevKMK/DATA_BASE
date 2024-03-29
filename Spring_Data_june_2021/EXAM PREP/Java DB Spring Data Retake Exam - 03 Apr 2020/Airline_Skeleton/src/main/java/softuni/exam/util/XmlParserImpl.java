package softuni.exam.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

public class XmlParserImpl implements XmlParser {
    @Override
    @SuppressWarnings(value = "unchecked")
    public <O> O importXMl(Class<O> objectClass, String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (O) unmarshaller.unmarshal(new File(path));
    }

    @Override
    public <O> void exportXML(O entity, String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(entity.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(entity, new File(path));
    }

//        @Override
//        @SuppressWarnings("unchecked")
//        public <O> O parseXml(Class<O> objectClass, String filePath) throws Exception {
//            try {
//                JAXBContext context = JAXBContext.newInstance(objectClass);
//                Unmarshaller unmarshaller = context.createUnmarshaller();
//                return (O) unmarshaller.unmarshal(new File(filePath));
//            } catch (JAXBException ex) {
//                throw new Exception(ex.getMessage(), ex);
//            }
//        }
//
//        @Override
//        public <O> void exportXml(O object, Class<O> objectClass, String filePath) throws Exception {
//            try {
//                JAXBContext context = JAXBContext.newInstance(objectClass);
//                Marshaller marshaller = context.createMarshaller();
//                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//                marshaller.marshal(object, new File(filePath));
//            } catch (JAXBException ex) {
//                throw new Exception(ex.getMessage(), ex);
//            }
//        }
//
//        @Override
//        public <O> String exportXml(O object, Class<O> objectClass) throws Exception {
//            try {
//                JAXBContext context = JAXBContext.newInstance(objectClass);
//                Marshaller marshaller = context.createMarshaller();
//                StringWriter sw = new StringWriter();
//                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//                marshaller.marshal(object, sw);
//                return sw.toString();
//            } catch (JAXBException ex) {
//                throw new Exception(ex.getMessage(), ex);
//            }
//
//        }
}
