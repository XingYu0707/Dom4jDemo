package Dom4jDemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 
 * @author hongliang.dinghl Dom4j 生成XML文档与解析XML文档
 */
public class Dom4jDemo implements XmlDocument {

	
	int TwoElement = 0;
	int ThreeElement = 0;
	int fourElement = 0;
	
	public void createXml(String fileName) {
		Document document = DocumentHelper.createDocument();//根文件
		Element employees = document.addElement("employees");//根元素
		
		for (int i = 0; i < 5; i++) {
			Element employee = employees.addElement("employee"+i);//第二级元素
			Element name = employee.addElement("name"+i);//第三级元素
			name.setText("ddvip"+i);//元素内容
			Element sex = employee.addElement("sex"+i);
			sex.setText("m"+i);
			Element age = employee.addElement("age"+i);
			age.setText("29"+i);
		}
		
		
/*		Element employee = employees.addElement("employee");//第二级元素
		Element name = employee.addElement("name");//第三级元素
		name.setText("ddvip");//元素内容
		Element sex = employee.addElement("sex");
		sex.setText("m");
		Element age = employee.addElement("age");
		age.setText("29");*/
		
		try {
			Writer fileWriter = new FileWriter(fileName);
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}

	}

	public void parserXml(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);//获取xml文件
			Element employees = document.getRootElement();//获取根标签
			System.out.println("根元素名称："+employees.getName());
			for (Iterator  two= employees.elementIterator(); two.hasNext();) {//遍历第二级标签
				TwoElement++;
				Element employee = (Element) two.next();
				System.out.println("第二级元素名称:"+employee.getName());
				for (Iterator three = employee.elementIterator(); three.hasNext();) {//遍历第三级标签
					ThreeElement++;
					Element node = (Element) three.next();
					System.out.println("第三级元素名称"+node.getName() + ":" + node.getText());
					for (Iterator four = node.elementIterator(); four.hasNext();) {//遍历第四级标签
						fourElement++;
						Element fourth = (Element) four.next();
						System.out.println("第四级元素名称"+fourth.getName() + ":" + fourth.getText());
					}
				}

			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("第二级元素个数："+TwoElement);
		System.out.println("第三级元素个数："+ThreeElement);
		System.out.println("第四级元素个数："+fourElement);
		System.out.println("dom4j parserXml");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "F://test.xml" ;
		Dom4jDemo dom4jDemo = new Dom4jDemo();
		dom4jDemo.createXml(fileName);
		dom4jDemo.parserXml(fileName);

	}
}
