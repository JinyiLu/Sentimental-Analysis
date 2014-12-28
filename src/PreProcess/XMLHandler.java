package PreProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
	private Map<String, String> map = null;
	private List<Map<String, String>> list = null;
	String currentTag = null;
	String currentValue = null;
	String nodeName = null;
	boolean flag = false;
	private StringBuffer buf;

	public XMLHandler(String nodeName) {
		this.nodeName = nodeName;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	@Override
	public void startDocument() throws SAXException {
		list = new ArrayList<Map<String, String>>();
		buf = new StringBuffer();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(nodeName)) {
			map = new HashMap<String, String>();
		}

		if (attributes != null && map != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				map.put(attributes.getQName(i), attributes.getValue(i));
				// System.out.println(attributes.getQName(i));
				if (map.get("id").equals("112")) {
					flag = true;
				}
			}
		}
		currentTag = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (currentTag != null && map != null) {
			currentValue = new String(ch, start, length);
			if (flag) {
				System.out.println(currentValue);
			}
			if (currentValue != null && !currentValue.trim().equals("")) {
				map.put(currentTag, currentValue);
				// System.out.println("**check**"+currentValue);
			}
			currentTag = null;
			currentValue = null;
		}
		buf.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals(nodeName)) {
			list.add(map);
			if (flag) {
				System.out.println(map);
				flag = false;
			}
			map = null;
		}
		if (!buf.toString().trim().equals("")) {
			if (flag) {
				System.out.println(buf.toString().trim());
			}
		}
		buf.setLength(0);
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

}