package cgh.util;
import java.util.Arrays;


public class FindRange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] list =
            new int[] { 6, 33, 4, 1, 9, 7, 3, 10, 15, 19, 11, 18, 13, 22, 24, 20, 27, 29, 25, 28, 31 };

		String l = findRanges(list);
		
		System.err.println(l);
	}
	
	private static String SEP = ",";

	private static String findRanges(int[] list) {
		StringBuilder b = new StringBuilder();
		
		// Sort list
		Arrays.sort(list);
		
		for (int i : list) {
			System.err.println(i);
		}
		
		if (list == null || list.length == 0) {
			return b.toString();
		}
		else if (list.length < 2) {
			b.append(list[0] + SEP + list[1]);
		}
		else { // Requires real work.
			int start = list[0];
			int end = list[0];
			// Count entire list tracking ranges (min to max)
			for (int i = 1; i < list.length; i++) {
				if ((end + 1) == list[i]) { // Range continues
					end = list[i];
					System.err.println("countines:" + start + SEP + end);
				}
				else { // Range ended
					System.err.println("end:" + start + SEP + end);
					if (start == end) {
						b.append(start + ",");
					}
					else {
						b.append("[" + start + "-" + end + "],");
					}
					start = list[i];
					end = list[i];
				}
			}
			
			// Add the last range
			System.err.println("final:" + start + SEP + end);
			if (start == end) {
				b.append(start);
			}
			else {
				b.append("[" + start + "-" + end + "]");
			}
		}
		
		return b.toString();
	}

}
