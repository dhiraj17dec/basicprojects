package test.historical.eq.nse.inparts.part1;

public class Action2 {
	
	private int day;
	private String name;
	private String date;
	private float buyAt;
	private float sellAt;
	private float lowOnActionDay;
	private float HighOnActionDay;
	private String action;
	
	private String nextAction;
	
	private float _FromLast;
	private float _ToNew;
	private float newPh;
	private float newPl;

	public Action2() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Action2(int day, String name, String date, float buyAt, float sellAt, float HighOnActionDay, float lowOnActionDay, String action) {
		super();
		this.day = day;
		this.name = name;
		this.date = date;
		this.buyAt = buyAt;
		this.sellAt = sellAt;
		this.HighOnActionDay = HighOnActionDay;
		this.lowOnActionDay = lowOnActionDay;
		this.action = action;
	}



	public Action2(int day, String name, String date, float buyAt, float sellAt, String action, float _FromLast,
			float _ToNew, float newPh, float newPl) {
		super();
		this.day = day;
		this.name = name;
		this.date = date;
		this.buyAt = buyAt;
		this.sellAt = sellAt;
		this.action = action;
		this._FromLast = _FromLast;
		this._ToNew = _ToNew;
		this.newPh = newPh;
		this.newPl = newPl;
	}
	


	public String getNextAction() {
		return nextAction;
	}



	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}



	public float getLowOnActionDay() {
		return lowOnActionDay;
	}



	public void setLowOnActionDay(float lowOnActionDay) {
		this.lowOnActionDay = lowOnActionDay;
	}



	public float getHighOnActionDay() {
		return HighOnActionDay;
	}



	public void setHighOnActionDay(float highOnActionDay) {
		HighOnActionDay = highOnActionDay;
	}



	public int getDay() {
		return day;
	}



	public void setDay(int day) {
		this.day = day;
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



	public float getBuyAt() {
		return buyAt;
	}



	public void setBuyAt(float buyAt) {
		this.buyAt = buyAt;
	}



	public float getSellAt() {
		return sellAt;
	}



	public void setSellAt(float sellAt) {
		this.sellAt = sellAt;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public float get_FromLast() {
		return _FromLast;
	}



	public void set_FromLast(float _FromLast) {
		this._FromLast = _FromLast;
	}



	public float get_ToNew() {
		return _ToNew;
	}



	public void set_ToNew(float _ToNew) {
		this._ToNew = _ToNew;
	}



	public float getNewPh() {
		return newPh;
	}



	public void setNewPh(float newPh) {
		this.newPh = newPh;
	}



	public float getNewPl() {
		return newPl;
	}



	public void setNewPl(float newPl) {
		this.newPl = newPl;
	}


	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(_FromLast);
		result = prime * result + Float.floatToIntBits(_ToNew);
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + Float.floatToIntBits(buyAt);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + day;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(newPh);
		result = prime * result + Float.floatToIntBits(newPl);
		result = prime * result + Float.floatToIntBits(sellAt);
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
		Action2 other = (Action2) obj;
		if (Float.floatToIntBits(_FromLast) != Float.floatToIntBits(other._FromLast))
			return false;
		if (Float.floatToIntBits(_ToNew) != Float.floatToIntBits(other._ToNew))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (Float.floatToIntBits(buyAt) != Float.floatToIntBits(other.buyAt))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (day != other.day)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(newPh) != Float.floatToIntBits(other.newPh))
			return false;
		if (Float.floatToIntBits(newPl) != Float.floatToIntBits(other.newPl))
			return false;
		if (Float.floatToIntBits(sellAt) != Float.floatToIntBits(other.sellAt))
			return false;
		return true;
	}

	
	

	@Override
	public String toString() {
		
		
		return "Day=" + day + ", name=" + name + ", date=" + date + ", buyAt=" + String.format("%.2f", buyAt)
		+ ", sellAt=" + String.format("%.2f", sellAt)+ ", action=" + action + ", _FromLast=" + String.format("%.2f", _FromLast) 
		+ "%, _ToNew=" + String.format("%.2f", _ToNew) + "%, newPh=" + String.format("%.2f", newPh) 
				+ ", newPl=" + String.format("%.2f", newPl)   ;
	}


	

}
