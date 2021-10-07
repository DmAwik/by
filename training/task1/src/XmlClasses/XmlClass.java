package XmlClasses;

import Classes.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlClass {
    public Depot readXml () throws ParserConfigurationException {
        Depot depot = new Depot();
        File fXmlFile = new File("training/task1/src/Resource/input.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = null;
        try {
            doc = dBuilder.parse(fXmlFile);
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("carriage");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                switch (eElement.getElementsByTagName("type").item(0).getTextContent()){
                    case ("0"):{depot.setCountPassCarriage(Integer.parseInt(eElement.getElementsByTagName("countCarriage").item(0).getTextContent()));
                        PassCarriage firstPassCarriage = new PassCarriage();
                        PassCarriage secondPassCarriage = new PassCarriage();

                        firstPassCarriage.setCountPassenger(Integer.parseInt(eElement.getElementsByTagName("countPerson").item(0).getTextContent()));
                        secondPassCarriage.setCountPassenger(Integer.parseInt(eElement.getElementsByTagName("countPerson").item(0).getTextContent()));

                        depot.list.add(firstPassCarriage);
                        depot.list.add(secondPassCarriage);
                    }
                    break;
                    case ("1"):{depot.setCountCapacityCarriage(Integer.parseInt(eElement.getElementsByTagName("countCarriage").item(0).getTextContent()));
                        Locomotive firstLocCarriage = new Locomotive();
                        Locomotive secondLocCarriage = new Locomotive();

                        firstLocCarriage.setCountCapacity(Integer.parseInt(eElement.getElementsByTagName("capacity").item(0).getTextContent()));
                        secondLocCarriage.setCountCapacity(Integer.parseInt(eElement.getElementsByTagName("capacity").item(0).getTextContent()));

                        depot.list.add(firstLocCarriage);
                        depot.list.add(secondLocCarriage);

                    }break;
                    case ("2"):{depot.setCountMainCarriage(Integer.parseInt(eElement.getElementsByTagName("countCarriage").item(0).getTextContent()));
                        MainCarriage firstMainCarriage = new MainCarriage();
                        MainCarriage secondMainCarriage = new MainCarriage();

                        depot.list.add(firstMainCarriage);
                        depot.list.add(secondMainCarriage);
                        //System.out.println(depot.list.size());
                    }break;
                }
            }
        }
        return depot;
    }

    public void writerXml(Train train) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            // создаем пустой объект Document, в котором будем
            // создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement =
                    doc.createElement("Trains");
            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);
            if ("PassCarriage".equals(train.list.get(1).getClass().getSimpleName())){
                rootElement.appendChild(getTrain(doc, train.list.get(1).getClass().getSimpleName(),
                        Integer.toString(train.getTotalWeight()), Integer.toString(train.getTotalCountPeople()),Integer.toString(train.getCountCarriage())));
            }
            if ("Locomotive".equals(train.list.get(1).getClass().getSimpleName())){
                rootElement.appendChild(getTrain(doc, train.list.get(1).getClass().getSimpleName(),
                        Integer.toString(train.getTotalWeight()), Integer.toString(train.getTotalCountPeople()),Integer.toString(train.getCountCarriage())));
            }
            // добавляем первый дочерний элемент к корневому
           // rootElement.appendChild(getLanguage(doc, "C", "44"));

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            StreamResult console = new StreamResult(System.out);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult file = new StreamResult(new File("training/task1/src/Resource/output.xml"));

            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // метод для создания нового узла XML-файла
    private static Node getTrain(Document doc, String type, String capacity,String people,String carriages) {
        Element trainElement = doc.createElement("Train");

        // создаем элементы
        trainElement.appendChild(getLanguageElements(doc, trainElement, "type", type));
        trainElement.appendChild(getLanguageElements(doc, trainElement, "totalCapacity", capacity));
        trainElement.appendChild(getLanguageElements(doc, trainElement, "totalCountPeople", people));
        trainElement.appendChild(getLanguageElements(doc, trainElement, "getCountCarriage", carriages));
        return trainElement;
    }


    // утилитный метод для создание нового узла XML-файла
    private static Node getLanguageElements(Document doc, Element element, String type, String value) {
        Element node = doc.createElement(type);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}

