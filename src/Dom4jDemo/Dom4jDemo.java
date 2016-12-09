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
 * @author hongliang.dinghl Dom4j ����XML�ĵ������XML�ĵ�
 */
public class Dom4jDemo implements XmlDocument {

	
	int TwoElement = 0;
	int ThreeElement = 0;
	int fourElement = 0;
	
	public void createXml(String fileName) {
		Document document = DocumentHelper.createDocument();//���ļ�
		Element employees = document.addElement("employees");//��Ԫ��
		
		for (int i = 0; i < 5; i++) {
			Element employee = employees.addElement("employee"+i);//�ڶ���Ԫ��
			Element name = employee.addElement("name"+i);//������Ԫ��
			name.setText("ddvip"+i);//Ԫ������
			Element sex = employee.addElement("sex"+i);
			sex.setText("m"+i);
			Element age = employee.addElement("age"+i);
			age.setText("29"+i);
		}
		
		
/*		Element employee = employees.addElement("employee");//�ڶ���Ԫ��
		Element name = employee.addElement("name");//������Ԫ��
		name.setText("ddvip");//Ԫ������
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
			Document document = saxReader.read(inputXml);//��ȡxml�ļ�
			Element employees = document.getRootElement();//��ȡ����ǩ
			System.out.println("��Ԫ�����ƣ�"+employees.getName());
			for (Iterator  two= employees.elementIterator(); two.hasNext();) {//�����ڶ�����ǩ
				TwoElement++;
				Element employee = (Element) two.next();
				System.out.println("�ڶ���Ԫ������:"+employee.getName());
				for (Iterator three = employee.elementIterator(); three.hasNext();) {//������������ǩ
					ThreeElement++;
					Element node = (Element) three.next();
					System.out.println("������Ԫ������"+node.getName() + ":" + node.getText());
					for (Iterator four = node.elementIterator(); four.hasNext();) {//�������ļ���ǩ
						fourElement++;
						Element fourth = (Element) four.next();
						System.out.println("���ļ�Ԫ������"+fourth.getName() + ":" + fourth.getText());
					}
				}

			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("�ڶ���Ԫ�ظ�����"+TwoElement);
		System.out.println("������Ԫ�ظ�����"+ThreeElement);
		System.out.println("���ļ�Ԫ�ظ�����"+fourElement);
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
