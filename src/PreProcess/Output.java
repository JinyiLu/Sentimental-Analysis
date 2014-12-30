package PreProcess;

import java.util.*;
import java.io.*;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class Output {
	private Data data;
	
	public void loadIn(String inputPath){
		data.loadIn(inputPath);
	}
	
	public void loadIn(Data data){
		this.data = data;
	}

	
	public void output(Map<Integer,Boolean> map, String outputaddr) throws Exception{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
        Writer stream = new FileWriter(outputaddr);
        XMLStreamWriter writer = factory.createXMLStreamWriter(stream);
        
        writer.writeStartDocument();
        writer.writeCharacters("\n");
        writer.writeStartElement("weibos");
        for(int id: data.getIdList()){
        	writer.writeStartElement("weibo");
        
        	writer.writeAttribute("id", String.valueOf(id));
        	
        	if(map.get(id))
        		writer.writeAttribute("polarity", "1");
        	else
        		writer.writeAttribute("polarity", "-1");
       
        	writer.writeCharacters(data.getId2Text().get(id));
        
        	writer.writeEndElement();
        	writer.writeCharacters("\n");
        	writer.flush();
        }
        writer.writeEndElement();
       
       // writer.writeEndDocument();
        
        writer.close();
	} 
	
}
