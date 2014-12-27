package PreProcess;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
    private Map<String,String> map=null;
    private List<Map<String,String>> list=null;
    String currentTag=null;
    String currentValue=null;
    String nodeName=null;
    

    public XMLHandler(String nodeName) {
        this.nodeName=nodeName;
    }
    
    public List<Map<String, String>> getList() {
        return list;
    }

    @Override
    public void startDocument() throws SAXException {
        list=new ArrayList<Map<String,String>>();
    }
    
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if(qName.equals(nodeName)){
            map=new HashMap<String, String>();
        }
        
        if(attributes!=null&&map!=null){
            for(int i=0;i<attributes.getLength();i++){
                map.put(attributes.getQName(i), attributes.getValue(i));
            }
        }
        currentTag=qName;  
    }
    
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if(currentTag!=null&&map!=null){
            currentValue=new String(ch,start,length);
            if(currentValue!=null&&!currentValue.trim().equals("")&&!currentValue.trim().equals("\n")){
                map.put(currentTag, currentValue);
            }
            currentTag=null;
            currentValue=null;
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(qName.equals(nodeName)){
            list.add(map);
            map=null;
        }
    }
    
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}