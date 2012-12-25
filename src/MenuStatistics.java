package XmlReader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuStatistics implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MenuCounter> counter = new ArrayList<MenuCounter>();
	public List<MenuCounter> getCounter()
	{
		return counter;
	}
	public void setCounter(List<MenuCounter> counter)
	{
		this.counter = counter;
	}
	
}
