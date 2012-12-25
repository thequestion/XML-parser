package XmlReader;

import java.io.Serializable;

public class MenuCounter implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String counter_type;
		private String counter_PKTs;
		private String counter_BITs;
		private String counter_AvgPPs;
		private String counter_AvgBPs;
		private String counter_MaxPPs;
		private String counter_MaxBPs;
		public String getCounterType()
		{
			return counter_type;
		}
		public void setCounterType(String counter_type)
		{
			this.counter_type = counter_type;
		}
		public String getCounter_PKTs()
		{
			return counter_PKTs;
		}
		public void setCounter_PKTs(String counter_PKTs)
		{
			this.counter_PKTs = counter_PKTs;
		}
		public String getCounter_BITs()
		{
			return counter_BITs;
		}
		public void setCounter_BITs(String counter_BITs)
		{
			this.counter_BITs = counter_BITs;
		}
		public String getcounter_AvgPPs()
		{
			return counter_AvgPPs;
		}
		public void setcounter_AvgPPs(String counter_AvgPPs)
		{
			this.counter_AvgPPs = counter_AvgPPs;
		}
		public String getcounter_AvgBPs()
		{
			return counter_AvgBPs;
		}
		public void setcounter_AvgBPs(String counter_AvgBPs)
		{
			this.counter_AvgBPs = counter_AvgBPs;
		}
		public String getcounter_MaxPPs()
		{
			return counter_MaxPPs;
		}
		public void setcounter_MaxPPs(String counter_MaxPPs)
		{
			this.counter_MaxPPs = counter_MaxPPs;
		}
		public String getcounter_MaxBPs()
		{
			return counter_MaxBPs;
		}
		public void setcounter_MaxBPs(String counter_MaxBPs)
		{
			this.counter_MaxBPs = counter_MaxBPs;
		}
		
}
