package com.student.manage;

import java.util.Comparator;

public class ChineseComparator implements Comparator<Banji>{
	
	public int compare(Banji bj1, Banji bj2) {
		float f1=bj1.getChineseAverageScore();
		float f2=bj2.getChineseAverageScore();
		int n = 0;
		if (f1 > f2) {
			n = -1;
		} else if (f1 < f2) {
			n = 1;
		} else {
			n = 0;
		}

		return n;
	}

}
