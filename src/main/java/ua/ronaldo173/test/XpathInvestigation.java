package ua.ronaldo173.test;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class XpathInvestigation {

	public static void main(String[] args) throws Exception {

		System.out.println(1);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File file = new File("test2.xml");
		System.out.println(file.exists());
		Document document = builder.parse(file);

		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();

		// String expression = "/howto/topic[4]/url/text()";
		String expression = "configurations/configuration[%s]/name";
		XPathExpression expresComp = xPath.compile(expression);

		String evaluate = (String) expresComp.evaluate(document, XPathConstants.STRING);
		System.out.println(evaluate);
	}

}
