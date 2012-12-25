package XmlReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlTest
{

	/**
	 * Dom4j 解析XML文件
	 */
	static List<MenuCounter> listCounter = new ArrayList<MenuCounter>();
	static List<MenuStatistics> listStatistics = new ArrayList<MenuStatistics>();
	//static List<MenuItem> listItem = new ArrayList<MenuItem>();
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		File file=new File("clean.xml");
		XmlTest.readXmlFile(file);
		XmlTest.outPut();
		System.out.println(System.currentTimeMillis()-start);
	}
	public static void readXmlFile(File file)
	{
		try
		{
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			//根节点zone节点
			Element root = doc.getRootElement();
			if("DDOS_0002_61.152.81.218".equals(root.attributeValue("NAME"))
					&&"SHTel_Detector".equals(root.attributeValue("GUARD_NAME")))
			{
				//获得statistics节点
				for (Iterator<?> report = root.elementIterator("PEPORT"); report.hasNext();) 
				{
					MenuReport menu_Report = new MenuReport();
					
					Element menuReport;
					menuReport = (Element) report.next();	
					//获得group节点
					for (Iterator<?> statistics = root.elementIterator("STATISTICS"); statistics.hasNext();) 
					{
						MenuStatistics menu_Statistics = new MenuStatistics();
						
						Element menuStatistics;
						menuStatistics = (Element) statistics.next();	
						//获得item节点
						for(Iterator<?> counter = menuStatistics.elementIterator("COUNTER"); counter.hasNext();)
						{
							MenuCounter menu_Counter = new MenuCounter();
							Element counterGroup;
							counterGroup = (Element) counter.next();
							//获得item节点的属性值
							menu_Counter.setCounterType(counterGroup.attributeValue("TYPE"));
							menu_Counter.setCounter_PKTs(counterGroup.attributeValue("TOTAL_PKTS"));
							menu_Counter.setCounter_BITs(counterGroup.attributeValue("TOTAL_BITS"));
							menu_Counter.setCounter_BITs(counterGroup.attributeValue("AVG_PPS"));
							menu_Counter.setCounter_BITs(counterGroup.attributeValue("AVG_BPS"));
							menu_Counter.setCounter_BITs(counterGroup.attributeValue("MAX_PPS"));
							menu_Counter.setCounter_BITs(counterGroup.attributeValue("MAX_BPS"));
							
							listCounter.add(menu_Counter);
						}
						
						menu_Statistics.setCounter(listCounter);
						//listCounter.add(menu_Statistics);
						}
					menu_Report.setReport_name(menuReport.attributeValue("ID"));
					menu_Report.setStatistics(listStatistics);
					/*menu_Group.setGroup_name(menuGroup.attributeValue("name"));
					menu_Group.setItem(listItem);
					listGroup.add(menu_Group);*/	}
				System.out.println("SUCCESS");
			}
			else
			{
				System.out.println("FAIL");
			}
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void outPut()
	{
		System.out.println(listStatistics.size());
		System.out.println(listCounter.size());
		//System.out.println(listItem.size());
 		for(MenuCounter i : listCounter)
 		{
 			System.out.println(i.getCounterType());
 			System.out.println(i.getCounter_BITs());
 			System.out.println(i.getCounter_PKTs());
 			System.out.println(i.getcounter_AvgBPs());
 			System.out.println(i.getcounter_AvgPPs());
 			System.out.println(i.getcounter_MaxBPs());
 			System.out.println(i.getcounter_MaxPPs());
 		}
//		
//		for(MenuGroup i : listGroup)
//		{
//			System.out.println(i.getGroup_name());
//		}
	}
}