package ua.ronaldo173.mypackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MainXml {

	static String pathXmlFile = "monitorsXmlForTest_VALID.xml";
	static String pathXsdFile = "monitorsXsdForTest_VALID.xsd";
	static String pathXmlFileEmployees = "xmlEmployees.xml";

	public static void main(final String[] args)
			throws FileNotFoundException, IOException, JAXBException, SAXException, XPathExpressionException {

		File fileXsd = new File(pathXsdFile);
		File fileXml = new File(pathXmlFile);
		File fileXmlEmployee = new File(pathXmlFileEmployees);
		System.out.println(
				"Files exists: " + fileXsd.exists() + " " + fileXml.exists() + ", " + fileXmlEmployee.exists());

		FileInputStream fisXml = new FileInputStream(fileXml);

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document document = builder.parse(fisXml);

		XPath xPath = XPathFactory.newInstance().newXPath();

		// String expression = "monitors/monitor";
		//
		// NodeList nodeList = (NodeList)
		// xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
		// System.out.println(nodeList);
		// System.out.println("list length:" + nodeList.getLength());
		//
		// for (int i = 0; i < nodeList.getLength(); i++) {
		// Node item = nodeList.item(i);
		// System.out.println("/nITEM - " + i);
		// System.out.println(item.getTextContent());
		// }

		// get path for our nodes with information
		StringBuilder expression = new StringBuilder("/");
		System.out.println("PATH: --> " + expression);
		Node nodeDoc = (Node) xPath.compile(expression.toString()).evaluate(document, XPathConstants.NODE);
		Node rootNode = nodeDoc.getFirstChild();
		expression.append(rootNode.getNodeName());
		Node childNode = rootNode.getFirstChild().getNextSibling();
		expression.append("/" + childNode.getNodeName());

		System.err.println("PATH: " + expression);
		NodeList nodeList = (NodeList) xPath.compile(expression.toString()).evaluate(document, XPathConstants.NODESET);

		List<Node> listNodes = convertToList(nodeList);
		for (Node node : listNodes) {
			System.out.println(node.getTextContent());
		}

		String[] sortOrder = { "diagonal", "price" };
		Comparator<Node> comparator = getComparator(sortOrder);
		Collections.sort(listNodes, comparator);

		System.out.println("\nAFTER SORT:");
		for (Node node : listNodes) {
			System.out.println(node.getTextContent());
		}
	}

	private static Comparator<Node> getComparator(final String[] sortOrder) {
		final XPath xPath = XPathFactory.newInstance().newXPath();
		Comparator<Node> comparator = new Comparator<Node>() {

			public int compare(Node node1, Node node2) {
				int compareResult = 0;
				try {
					for (String orderElement : sortOrder) {
						Node node1ForComparing = (Node) xPath.compile(orderElement).evaluate(node1,
								XPathConstants.NODE);
						Node node2ForCOmparing = (Node) xPath.compile(orderElement).evaluate(node2,
								XPathConstants.NODE);

						if (node1ForComparing != null && node2ForCOmparing != null) {
							if (node1ForComparing.hasChildNodes() && node2ForCOmparing.hasChildNodes()) {
								String node1Value = node1ForComparing.getFirstChild().getNodeValue();
								String node2Value = node2ForCOmparing.getFirstChild().getNodeValue();

								compareResult = node2Value.compareTo(node1Value);
							}
						}
						if (compareResult != 0) {
							break;
						}
					}

				} catch (XPathExpressionException e) {
					e.printStackTrace();
				}
				return compareResult;
			}
		};
		return comparator;
	}

	private static List<Node> convertToList(NodeList nodeList) {
		List<Node> list = new ArrayList<Node>();

		for (int i = 0; i < nodeList.getLength(); i++) {
			list.add(nodeList.item(i));
		}
		return list;
	}
}
