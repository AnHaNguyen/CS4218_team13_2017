package sg.edu.nus.comp.cs4218.impl.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sg.edu.nus.comp.cs4218.app.Cal;
import sg.edu.nus.comp.cs4218.exception.CalException;
import sg.edu.nus.comp.cs4218.Constants;
import sg.edu.nus.comp.cs4218.Utility;

public class CalApplication implements Cal {
	static final String MONDAY_FIRST_FLAG = "-m";
	
	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws CalException {				
		if (args == null) {
			throw new CalException(Constants.CalMessage.INVALID_ARGS);
		}
	
		if (args.length > 3) {
			throw new CalException(Constants.CalMessage.INVALID_NUMBER_ARGUMENTS);
		}
		
		String inputArgs = Utility.arrayToString(args);
		String output = null;
		
		if (args.length == 0) {
			output = printCal(inputArgs);
		} else if (args.length == 1 && args[0].equals(MONDAY_FIRST_FLAG)) {
			output = printCalWithMondayFirst(inputArgs);
		} else if (args.length == 1 && !args[0].equals(MONDAY_FIRST_FLAG)) {
			output = printCalForYear(inputArgs);
		} else if (args.length == 2 && args[0].equals(MONDAY_FIRST_FLAG)) {
			output = printCalForYearMondayFirst(inputArgs);
		} else if (args.length == 2 && !args[0].equals(MONDAY_FIRST_FLAG)) {
			output = printCalForMonthYear(inputArgs);
		} else if (args.length == 3 && args[0].equals(MONDAY_FIRST_FLAG)) {
			output = printCalForMonthYearMondayFirst(inputArgs);
		}
		
		if (output == null) {
			throw new CalException(Constants.CalMessage.INVALID_ARGS);
		} else {
			System.out.print(output);
		}
	}
	
	/**
	 * Print the calendar of the current month
	 * @param args String containing command and arguments to print the calendar of the current month
	 */
	@Override
	public String printCal(String args) {
		Date current = new Date();
		int month = Utility.getMonth(current);
		int year = Utility.getYear(current);
		return Utility.generateCalendarForMonthYear(month, year, false);
	}

	
	/**
	 * Returns the string to print the calendar of the current month with Monday
	 * as the first day of the week
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalWithMondayFirst(String args) {
		Date current = new Date();
		int month = Utility.getMonth(current);
		int year = Utility.getYear(current);
		return Utility.generateCalendarForMonthYear(month, year, true);
	}

	/**
	 * Returns the string to print the calendar for specified month and year
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForMonthYear(String args) {
		String[] params = Utility.stringToArray(args);
		try {
			int month = Integer.parseInt(params[0]);
			int year = Integer.parseInt(params[1]);
			return Utility.generateCalendarForMonthYear(month, year, false);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Returns the string to print the calendar for specified month and year
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForMonthYearMondayFirst(String args) {
		String[] params = Utility.stringToArray(args);
		try {
			int month = Integer.parseInt(params[1]);
			int year = Integer.parseInt(params[2]);
			return Utility.generateCalendarForMonthYear(month, year, true);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns the string to print the calendar for specified year
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForYear(String args) {
		String[] params = Utility.stringToArray(args);
		try {
			int year = Integer.parseInt(params[0]);
			return Utility.generateCalendarForYear(year, false);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns the string to print the calendar for specified year with Monday
	 * as the first day of the week
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForYearMondayFirst(String args) {
		String[] params = Utility.stringToArray(args);
		try {
			int year = Integer.parseInt(params[1]);
			return Utility.generateCalendarForYear(year, true);
		} catch (Exception e) {
			return null;
		}
	}

}
