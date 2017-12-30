package bg.model;

import java.util.Date;

public class Certificate {

	private Date date;

	public Certificate() {
		date = null;
	}

	public Certificate(Date date) {
		this.date = date;
	}

	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		return "цертификат от министерството на образованието, издаден на : =" + date.getDay() + "/" + date.getMonth()
				+ "/" + date.getYear();
	}

}
