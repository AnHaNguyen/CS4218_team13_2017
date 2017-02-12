package sg.edu.nus.comp.cs4218;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;

import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SeqCommand;
import sg.edu.nus.comp.cs4218.Constants;

/**
 * Created by thientran on 2/7/17.
 */
public class Utility {
	/**
	 * Return 2D array of default value
	 * @param rowSize
	 * @param colSize
	 * @param defaultValue
	 * @return result
	 */
	public static int[][] initArray(int rowSize, int colSize, int defaultValue) {
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				result[i][j] = defaultValue;
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param date
	 * @return month
	 */
	public static int getMonth(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		return Integer.parseInt(dateFormat.format(date));
	}
	
	/**
	 * 
	 * @param date
	 * @return year
	 */
	public static int getYear(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return Integer.parseInt(dateFormat.format(date));		
	}
	
	/**
	 * 
	 * @param year
	 * @return boolean to know if this is leap year
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 !=0) || (year % 400 == 0);
	}
	
	/**
	 * 
	 * @param month
	 * @param year
	 * @return number of days in that month of year
	 */
	public static int getNumberOfDayForMonth(int month, int year) {
		switch (month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			return 31;
		case 4: case 6: case 9: case 11:
			return 30;
		case 2:
			if (isLeapYear(year)) {
				return 29;
			} else {			
				return 28;
			}
		default:
			return -1; // invalid
		}
	}
	
	/**
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return dayCode of week (from 0 to 6) as Sun to Sat
	 */
	public static int getDayOfWeek(int day, int month, int year) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateString = "" + year + "/";
		if (month < 10) {
			dateString += "0";
		}
		dateString += (month + "/");
		
		if (day < 10) {
			dateString += "0";
		}
		dateString += (day + " 00:00:00");
		
		try {
			Date dateObject = dateFormat.parse(dateString);
			dateFormat.applyPattern("EE");
			String dayText = dateFormat.format(dateObject);
			
			switch (dayText) {
			case "Sun":
				return 0;
			case "Mon":
				return 1;
			case "Tue":
				return 2;
			case "Wed":
				return 3;
			case "Thu":
				return 4;
			case "Fri":
				return 5;
			case "Sat":
				return 6;
			default:
				return -1;
			}
		} catch (Exception e) {
			return -1;
		}
	}
	
	/**
	 * 
	 * @param month
	 * @param year
	 * @param mondayFirst
	 * @return 2D calendar array
	 */
	public static int[][] getCalendarArrayForMonth(int month, int year, boolean mondayFirst) {
		int[][] calendarArr = initArray(Constants.Common.CALENDAR_ROW_SIZE, Constants.Common.CALENDAR_COL_SIZE, -1);
		int maxDay = getNumberOfDayForMonth(month, year);
		int dayIndex = getDayOfWeek(1, month, year);
		if (mondayFirst) {
			dayIndex = (dayIndex + 7 - 1) % 7;
		}
		
		for (int i = 1; i <= maxDay; i++) {
			int rowIndex = dayIndex / Constants.Common.CALENDAR_COL_SIZE;
			int colIndex = dayIndex % Constants.Common.CALENDAR_COL_SIZE;
			calendarArr[rowIndex][colIndex] = i;
			dayIndex++;
		}
		
		return calendarArr;
	}
	
	/**
	 * Print calendar title
	 * @param month
	 * @param year
	 */
	public static void printCalTitle(int month, int year) {
		int calendarWidth = 20;
		String calendarTitle = Constants.Common.MONTH_TO_TEXT[month - 1] + ", " + year;
		int space = (calendarWidth - calendarTitle.length()) / 2;
		for (int i = 0; i < space; i++) {
			System.out.print(" ");
		}
		
		System.out.print(calendarTitle);
		for (int i = 0; i < space; i++) {
			System.out.print(" ");
		}		
	}
	
	/**
	 * Print calendar headers
	 * @param mondayFirst
	 */
	public static void printCalHeaders(boolean mondayFirst) {
		// print headers
		if (mondayFirst) {
			System.out.print("Mo Tu We Th Fr Sa Su");
		} else {
			System.out.print("Su Mo Tu We Th Fr Sa");
		}		
	}
	
	/**
	 * Print space between calendar 2D array
	 */
	public static void printMonthSpace() {
		System.out.print(Constants.Common.SPACE_BETWEEN_MONTH);
	}

	/**
	 * function to create Command from the respective String
	 * @param command
	 * @return command as type Command
	 */
	public static Command getCommandFromString(String command) {
		command = command.trim();
		if (command.indexOf(";") != -1) {
			return new SeqCommand(command);
		}
		
		return new CallCommand(command);
	}
	
	//Implement merge sort
	public static <T extends Comparable<T>> void sort (T[] values) {
		mergeSort(values, 0, values.length/2);
	}
	
	public static <T extends Comparable<T>> void mergeSort (T[] values, int left, int right) {
		int middle = (left+right)/2;
		
		//Recursively dividing
		mergeSort(values, left, middle);
		mergeSort(values, middle+1, right);
		
		//Merge partitions
		merge(values, left, middle, right);
	}
	
	public static <T extends Comparable<T>> void merge (T[] values, int left, int middle, int right) {
		int stepCount = right-left+1;
		
		int leftStep = left;
		T leftVal = null;
		
		int rightStep = middle+1;
		T rightVal = null;
		
		//Initialize tempArr with T objects of values' size
		ArrayList<T> tempList = new ArrayList<T>(Collections.nCopies(stepCount, values[0]));
		
		//Merge the 2 lists' items iteratively
		for (int i = 0; i<stepCount; i++){
			if (leftStep <= middle &&rightStep <= right) {
				leftVal = values[leftStep];
				rightVal = values[rightStep];
			}
			//If 1 of the 2 lists is already exhausted, add the remaining sorted items to list
			else {
				if (leftStep == middle) {
					for (int j = rightStep; j<stepCount; j++) {
						rightVal = values[j];
						tempList.set(j, rightVal);
					}
				}
				else {
					for (int j = leftStep; j<=middle; j++) {
						leftVal = values[j];
						tempList.set(j, leftVal);
					}
				}
				break;
			}
			
			//Add the smaller value to tempList
			if(compare(leftVal, rightVal)<=0) {
				tempList.set(i, leftVal);
				leftStep++;
			}
			else {
				tempList.set(i, rightVal);
				rightStep++;
			}
		}
		for (int i = 0; i<stepCount; i++) {
			values[left + i] = tempList.get(i);
		}
	}
	
	//Compare 2 T objects as define in object comparator
	public static <T extends Comparable<T>> int compare(T first, T second) {
		return first.compareTo(second);
	}
	
}