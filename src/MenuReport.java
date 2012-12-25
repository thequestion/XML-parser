package XmlReader;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuReport implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 602560816714101992L;
	private String report_name;
	private List<MenuStatistics> statistics = new ArrayList<MenuStatistics>();;
	public String getReport_name()
	{
		return report_name;
	}
	public void setReport_name(String report_name)
	{
		this.report_name = report_name;
	}
	public List<MenuStatistics> getStatistics()
	{
		return statistics;
	}
	public void setStatistics(List<MenuStatistics> statistics)
	{
		this.statistics = statistics;
	}

}