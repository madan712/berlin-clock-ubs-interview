package com.ubs.opsit.interviews;

import java.util.stream.Stream;

public class BerlinClock implements TimeConverter {

	@Override
	public String convertTime(String aTime) {
		int[] parts = Stream.of(aTime.split(":")).mapToInt(Integer::parseInt).toArray();

		StringBuffer time = new StringBuffer();

		return time.append(getSeconds(parts[2])).append(System.getProperty("line.separator"))
				.append(getTopHours(parts[0])).append(System.getProperty("line.separator"))
				.append(getBottomHours(parts[0])).append(System.getProperty("line.separator"))
				.append(getTopMinutes(parts[1])).append(System.getProperty("line.separator"))
				.append(getBottomMinutes(parts[1])).toString();
		
		

	}

	protected String getSeconds(int number) {
		if (number % 2 == 0)
			return "Y";
		else
			return "O";
	}

	protected String getTopHours(int number) {
		return getOnOff(4, getTopNumberOfOnSigns(number));
	}

	protected String getBottomHours(int number) {
		return getOnOff(4, number % 5);
	}

	protected String getTopMinutes(int number) {
		return getOnOff(11, getTopNumberOfOnSigns(number), "Y").replaceAll("YYY", "YYR");
	}

	protected String getBottomMinutes(int number) {
		return getOnOff(4, number % 5, "Y");
	}

	private String getOnOff(int lamps, int onSigns) {
		return getOnOff(lamps, onSigns, "R");
	}

	private String getOnOff(int lamps, int onSigns, String onSign) {
		String out = "";
		for (int i = 0; i < onSigns; i++) {
			out += onSign;
		}
		for (int i = 0; i < (lamps - onSigns); i++) {
			out += "O";
		}
		return out;
	}

	private int getTopNumberOfOnSigns(int number) {
		return (number - (number % 5)) / 5;
	}

}
