package PreProcess;


import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class SaxService {
    
    public static List<Map<String,String>> ReadXML(String uri,String NodeName){
        try {
            SAXParserFactory parserFactory=SAXParserFactory.newInstance();
            SAXParser parser=parserFactory.newSAXParser();
            XMLHandler myhandler=new XMLHandler("weibo");
            parser.parse(uri, myhandler);
            return myhandler.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            
        }
        return null;
        
    }
}