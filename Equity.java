package test.historical.eq.nse.inparts.part1;

import java.util.List;

public class Equity {
	
	private String name;
	private String date;
	private float open;
	private float high;
	private float low;
	private float close;
	private long volume;

	private float avgHigh;
	private float avgLow;
	private float currentLtp;
	
	private float pH;
	private float pL;
	
	private List<Action2> actionList;
	
	private float ltpDeviationLastActionPt;
	private float ltpDeviationNextActionPt;
	
	public Equity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Equity(String name) {
		super();
		this.setName(name);
	}

	public Equity(String name, String date, float open, float high, float low, float close, long volume) {
		super();
		this.name = name;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	
	
	public float getpH() {
		return pH;
	}

	public void setpH(float pH) {
		this.pH = pH;
	}

	public float getpL() {
		return pL;
	}

	public void setpL(float pL) {
		this.pL = pL;
	}

	public float getLtpDeviationLastActionPt() {
		return ltpDeviationLastActionPt;
	}

	public void setLtpDeviationLastActionPt(float ltpDeviationLastActionPt) {
		this.ltpDeviationLastActionPt = ltpDeviationLastActionPt;
	}

	public float getLtpDeviationNextActionPt() {
		return ltpDeviationNextActionPt;
	}

	public void setLtpDeviationNextActionPt(float ltpDeviationNextActionPt) {
		this.ltpDeviationNextActionPt = ltpDeviationNextActionPt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getOpen() {
		return open;
	}

	public void setOpen(float open) {
		this.open = open;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public float getClose() {
		return close;
	}

	public void setClose(float close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public float getAvgHigh() {
		return avgHigh;
	}

	public void setAvgHigh(float avgHigh) {
		this.avgHigh = avgHigh;
	}

	public float getAvgLow() {
		return avgLow;
	}

	public void setAvgLow(float avgLow) {
		this.avgLow = avgLow;
	}

	public float getCurrentLtp() {
		return currentLtp;
	}

	public void setCurrentLtp(float currentLtp) {
		this.currentLtp = currentLtp;
	}

	public List<Action2> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action2> actionList) {
		this.actionList = actionList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(close);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + Float.floatToIntBits(high);
		result = prime * result + Float.floatToIntBits(low);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(open);
		result = prime * result + (int) (volume ^ (volume >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equity other = (Equity) obj;
		if (Float.floatToIntBits(close) != Float.floatToIntBits(other.close))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Float.floatToIntBits(high) != Float.floatToIntBits(other.high))
			return false;
		if (Float.floatToIntBits(low) != Float.floatToIntBits(other.low))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(open) != Float.floatToIntBits(other.open))
			return false;
		if (volume != other.volume)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Equity [name=" + name + ", date=" + date + ", open=" + open + ", high=" + high + ", low=" + low
				+ ", close=" + close + ", volume=" + volume + ", avgHigh=" + avgHigh + ", avgLow=" + avgLow
				+ ", currentLtp=" + currentLtp + ", actionList=" + actionList + "]";
	}

	
	
	
}
