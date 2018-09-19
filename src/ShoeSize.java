import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* ShoeSize - Eric McCreath 2015 - GPL
 * This class stores a persons shoe size.
 */

public class ShoeSize {
	private static final String SHOE = "SHOE";
	private static final String SHOESIZEENAME = "SHOESIZE";
	public static final int SHOESIZEMAX = 15;
	public static final int SHOESIZEMIN = 3;

	static final String FILENAME = "shoesize.xml";

	private Integer shoesize;

	public ShoeSize() {
		shoesize = null;
	}

	public String show() {
		return (shoesize == null ? "" : shoesize.toString());
	}

	public boolean set(Integer v) {
		if (v == null || v >= ShoeSize.SHOESIZEMIN && v <= ShoeSize.SHOESIZEMAX) {
			shoesize = v;
			save(FILENAME);
			return true;
		} else {
			shoesize = null;
			return false;
		}
	}

	static ShoeSize load(String filename) {
		File f = new File(filename);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		ShoeSize res = new ShoeSize();

		try {
// load the xml tree
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(f);

// parse the tree and obtain the person info
			Node person = doc.getFirstChild();

			NodeList nl = person.getChildNodes();
			for (int i =0;i< nl.getLength();i++) {
				Node n = nl.item(i);
				if (n.getNodeName().equals(SHOESIZEENAME)) {
					res.shoesize = Integer.parseInt(n.getTextContent());
				}
			}

		} catch (Exception e) {
			System.err.println("Problem loading " + filename);
		}
		return res;

	}

	void save(String filename) {

		File f = new File(filename);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
// make the xml tree
			db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element person = doc.createElement(SHOE);


			Element ea = doc.createElement(SHOESIZEENAME);
			ea.appendChild(doc.createTextNode(Integer.toString(shoesize)));
			person.appendChild(ea);

			doc.appendChild(person);
// save the xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

// set xml encoding to utf-8
			transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			transformer.transform(source, result);
		} catch (Exception e) {
			System.err.println("Problem saving " + filename);
		}

	}
}


