package pl.betacraft.logblock;

public class Data {

	public static String current() {
/*		Date d = new Date();
		StringBuilder sb = new StringBuilder();
		String day = Integer.toString(Calendar.DAY_OF_MONTH);
		int rawmonth = d.getDate();
		String year = Integer.toString(d.getYear());

		rawmonth++;
		String month = Integer.toString(rawmonth);

		sb.append(day + ".");
		sb.append(month + ".");
		sb.append(year);
		// Time
		int time = Calendar.AM_PM;*/

		//System.out.println("[TEST DO KURWY] " + Logging.getDateAndTime());

		return Logging.getDateAndTime();
	}
}