package com.turningcloud.pdftotxt.AbhudayaBank;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import com.turningcloud.pdftotxt.DataFeild;
import com.turningcloud.pdftotxt.DataLocationCompareImpl;
import com.turningcloud.pdftotxt.ListFiles;
import com.turningcloud.pdftotxt.PDFStreamEngine1;
import com.turningcloud.pdftotxt.Utility;

public class AbhudayaFormat2 extends PDFStreamEngine1{

	TreeSet<DataFeild> allDataStringSet = new TreeSet<DataFeild>(new DataLocationCompareImpl());
	ArrayList<String> word_location_of_tj1 = new ArrayList<String>();
	ArrayList<Float> x_location_of_tj1 = new ArrayList<Float>();
	ArrayList<Float> y_location_of_tj1 = new ArrayList<Float>();
	ArrayList<Float> height_location1 = new ArrayList<Float>();
	ArrayList<String> mergeString = new ArrayList<>();
	ArrayList<String> finalString = new ArrayList<>();
	ArrayList<String>  finalArray=new ArrayList<>();
	int pagecount;
	int firstCount;
	String prestr = "";
	float minx;
	float maxx;
	float miny;
	float maxy;
	float height;
	int count;
	float headerMiny;
	boolean is_header_found=false;
	int firstIterate=0;
	int headercount=0;
	int headerSize;
	float globalMinx;
	boolean garbageValue;

	public File goToLocation(ArrayList<String> for_canara_string, List<String> characterLocations,File fileTxt, String bank, int getpages, String accountType, String accountNo) throws IOException {

		pagecount++;
		int characterIterator1 = 0;
		float All_of_x1 = 0.0f;
		float maxx1 = 0.0f;
		float miny1 = 0.0f;
		float All_of_y1 = 0.0f;
		float maxy1 = 0.0f;
		float width_of_x1 = 0.0f;
		float hieght1 = 0.0f;
		float width_of_y1 = 0.0f;
		float maxHeightLocation1 = 0.0f;
		float minxlocation1 = 0.0f;
		String character_of_array1 = "";
		int counter1 = 0;
		for (String array_of_word : for_canara_string) {
			array_of_word = array_of_word.replaceAll("[\u0000-\u001f]", "");
			counter1++;
			array_of_word = array_of_word.trim();
			String newstr = "";

			int spaces = 0;
			boolean first = false;
			if (array_of_word != null) {
				for (char c : array_of_word.toCharArray()) {

					int code = (int) c;

					if (code == 160) {
						spaces++;
						if (spaces == 1 && first == true) {
							newstr = newstr + c;
						}
					} else {
						newstr = newstr + c;
						first = true;
					}
				}
				spaces = 0;
			}
			array_of_word = newstr;
			int Charactercount = 1;
			character_of_array1 = "";
			String sd1 = array_of_word.replaceAll(" ", "");
			String new_sd1 = "";
			if (sd1 != null) {
				for (char c : sd1.toCharArray()) {
					int code = (int) c;
					if (code == 160) {
					} else {
						new_sd1 = new_sd1 + c;
					}
				}
			}
			sd1 = new_sd1;
			sd1 = sd1.trim();
			if (sd1.length() == 0) {
				continue;
			}
			for (int i = 0; i < sd1.length(); i++) {
				String[] currentAlphabetArray1 = characterLocations.get(characterIterator1).split(" ");
				characterIterator1++;
				character_of_array1 = character_of_array1 + currentAlphabetArray1[0];
				All_of_x1 = Float.parseFloat(currentAlphabetArray1[1]);
				All_of_y1 = Float.parseFloat(currentAlphabetArray1[2]);
				hieght1 = Float.parseFloat(currentAlphabetArray1[3]);
				x_location_of_tj1.add(All_of_x1);
				y_location_of_tj1.add(All_of_y1);
				height_location1.add(hieght1);
			}
			String sorting_of_x1 = Utility.maxxlocation1(x_location_of_tj1);
			String sorting_of_y1 = Utility.maxxlocation1(y_location_of_tj1);
			String sorting_of_height1 = Utility.maxxlocation1(height_location1);
			sorting_of_x1 = sorting_of_x1.replaceAll("[\\[\\]]", "");
			sorting_of_y1 = sorting_of_y1.replaceAll("[\\[\\]]", "");
			sorting_of_height1 = sorting_of_height1.replaceAll("[\\[\\]]", "");

			String[] inp_of_x1 = sorting_of_x1.split(" ");
			String minxxx1 = inp_of_x1[0];
			minxxx1 = minxxx1.trim();
			minxlocation1 = Float.parseFloat(minxxx1);
			String maxxx1 = inp_of_x1[inp_of_x1.length - 1];
			maxxx1 = maxxx1.trim();
			maxx1 = Float.parseFloat(maxxx1);

			String[] inp_of_y1 = sorting_of_y1.split(" ");
			String minyy1 = inp_of_y1[0];
			minyy1 = minyy1.trim();
			miny1 = Float.parseFloat(minyy1);
			String may1 = inp_of_y1[inp_of_y1.length - 1];
			may1 = may1.trim();
			maxy1 = Float.parseFloat(may1);

			String[] inp_of_height1 = sorting_of_height1.split(" ");
			String heightt1 = inp_of_height1[1];
			heightt1 = heightt1.trim();
			hieght1 = Float.parseFloat(heightt1);
			miny1 = miny1 - hieght1;

			minxlocation1 = Math.round(minxlocation1 * 100) / 100;
			maxx1 = Math.round(maxx1 * 100) / 100;
			miny1 = Math.round(miny1 * 100) / 100;
			maxy1 = Math.round(maxy1 * 100) / 100;
			String single = array_of_word + "qwer" + minxlocation1 + "qwer" + maxx1 + "qwer" + miny1 + "qwer" + maxy1
					+ "qwer" + hieght1;
			allDataStringSet.add(new DataFeild(single, minxlocation1, maxy1));
			word_location_of_tj1.add(single);
			height_location1.clear();
			y_location_of_tj1.clear();
			x_location_of_tj1.clear();
		}
		if(pagecount==getpages) {
			int dataCount = 0;
			float narrationmaxx=0;
			float InstrNomaxx=0;
			float Debitminx=0;
			for(String str:word_location_of_tj1) {
				String [] string_of_tree_set=str.split("qwer");
				String real_string = string_of_tree_set[0];
				float real_minXloc = Float.parseFloat(string_of_tree_set[1]);
				float real_maxx = Float.parseFloat(string_of_tree_set[2]);
				float real_miny = Float.parseFloat(string_of_tree_set[3]);
				float real_maxy = Float.parseFloat(string_of_tree_set[4]);
				float real_height = Float.parseFloat(string_of_tree_set[5]);
				dataCount++;
				String[] masterHeaderList = new String[] { "Date","Instr No","Particulars","Debits","Credits","Balance" };

				if (Arrays.asList(masterHeaderList).contains(real_string) && !is_header_found) {
					if(real_string.equals("Particulars")) {
						narrationmaxx=real_maxx;
					}
					if(real_string.equals("Instr No")) {
						InstrNomaxx=real_maxx;
					}
					if(real_string.equals("Debits")) {
						Debitminx=real_minXloc;
					}
					if (headerSize == 0) {
						mergeString.add(real_string + "qwer" + real_minXloc + "qwer" + real_maxx + "qwer" + real_miny
								+ "qwer" + real_maxy + "qwer" + real_height);

						minx = real_minXloc;
						maxx = real_maxx;
						miny = real_miny;
						maxy = real_maxy;
						height = real_height;
						headerMiny=real_miny;
						headerSize++;

					} else if (Math.abs(miny - real_miny) > 16) {
						headerSize = 0;
						mergeString.clear();
						minx = real_minXloc;
						maxx = real_maxx;
						miny = real_miny;
						maxy = real_maxy;
						height = real_height;
						prestr=real_string;
						headerSize++;
					} else {
						mergeString.add(real_string + "qwer" + real_minXloc + "qwer" + real_maxx + "qwer" + real_miny
								+ "qwer" + real_maxy + "qwer" + real_height);

						minx = real_minXloc;
						maxx = real_maxx;
						miny = real_miny;
						maxy = real_maxy;
						height = real_height;
						headerMiny=real_miny;
						prestr=real_string;
						headerSize++;

					}
				}
				else
				{
					if(headerSize>4)
					{
						is_header_found = true;
						boolean date= isValidDate(real_string);
						if(real_string.contains("-----------------------------------"))
						{
							garbageValue=true;
							continue;
						}
						if(firstCount==0)
						{
							finalString.addAll(mergeString);
							mergeString.clear();
							mergeString.add(real_string + "qwer" + real_minXloc + "qwer" + real_maxx + "qwer" + real_miny
									+ "qwer" + real_maxy + "qwer" + real_height);
							prestr= real_string;
							minx = real_minXloc;
							maxx = real_maxx;
							miny = real_miny;
							maxy = real_maxy;
							height = real_height;
							globalMinx=real_minXloc;
							firstCount++;
							garbageValue=false;
						}
						else
						{
							boolean date1= isValidDate(real_string);
							boolean checkNumber=checkOnlyNumber(real_string);

							if(date1&&globalMinx==real_minXloc)
							{
								finalString.addAll(mergeString);
								mergeString.clear();
								mergeString.add(real_string + "qwer" + real_minXloc + "qwer" + real_maxx + "qwer" + real_miny
										+ "qwer" + real_maxy + "qwer" + real_height);
								prestr= real_string;
								minx = real_minXloc;
								maxx = real_maxx;
								miny = real_miny;
								maxy = real_maxy;
								height = real_height;
								globalMinx=real_minXloc; 
								garbageValue=false;
							}
							else if (Math.abs(narrationmaxx - real_minXloc) < 134 && ischeckOnlyNumber(real_string) &&!isValidDate(real_string)
									&& real_string.contains(".")) {
								finalString.addAll(mergeString);
								mergeString.clear();
								continue;
							}
							else if(garbageValue)
							{
								continue;
							}
							else if(checkNumber&&real_string.contains(".")&&!isValidDate(real_string))
							{
								mergeString.add(real_string + "qwer" + real_minXloc + "qwer" + real_maxx + "qwer" + real_miny
										+ "qwer" + real_maxy + "qwer" + real_height);
								prestr= real_string;
								minx = real_minXloc;
								maxx = real_maxx;
								miny = real_miny;
								maxy = real_maxy;
								height = real_height;
							}
							else if(real_string.contains("Abhyudaya Co-op. Bank Ltd.,"))
							{
								finalString.addAll(mergeString);
								mergeString.clear();
								garbageValue=true;
								continue;
							}
							else if(real_string.contains("Balance"))
							{
								garbageValue=false;
								continue;
							}
							else if(garbageValue)
							{
								continue;
							}
							else if (!isValidDate(prestr) && real_minXloc > InstrNomaxx && real_maxx < Debitminx) {

								boolean newData = true;
								float curMidX = (real_minXloc + real_maxx) / 2;
								for (int i = 0; i < mergeString.size(); i++) {
									String ds_text = mergeString.get(i);
									String[] single_loc_split = ds_text.split("qwer");
									String str1 = single_loc_split[0];
									float minx = Float.parseFloat(single_loc_split[1]);
									float maxx = Float.parseFloat(single_loc_split[2]);
									float miny = Float.parseFloat(single_loc_split[3]);
									float maxy = Float.parseFloat(single_loc_split[4]);
									float Height = Float.parseFloat(single_loc_split[5]);
									if (((minx > InstrNomaxx+20) && (minx <= (Debitminx-80)))
											&& (((InstrNomaxx+20) < real_minXloc) && ((Debitminx-60) >real_maxx))
													&& !isValidDate(str1)) {
										str1 = str1 + " " + real_string;
										if (real_maxx > maxx) {
											maxx = real_maxx;
										}
										newData = false;
										mergeString.remove(i);
										String get_string_location_updatedd = str1 + "qwer" + minx + "qwer" + maxx + "qwer"
												+ miny + "qwer" + maxy + "qwer" + Height;
										mergeString.add(i, get_string_location_updatedd);
									}
								}
								if (newData) {
									String get_string_location_updated1 = real_string + "qwer" + real_minXloc + "qwer" + real_maxx + "qwer" + real_miny
											+ "qwer" + real_maxy + "qwer" + real_height;
									mergeString.add(get_string_location_updated1);
								}

							}
							else
							{
								mergeString.add(real_string + "qwer" + real_minXloc + "qwer" + real_maxx + "qwer" + real_miny
										+ "qwer" + real_maxy + "qwer" + real_height);
								prestr= real_string;
								minx = real_minXloc;
								maxx = real_maxx;
								miny = real_miny;
								maxy = real_maxy;
								height = real_height;

							}
						}
					}
				}
				if (dataCount == word_location_of_tj1.size()) {
					finalString.addAll(mergeString);
					mergeString.clear();
				}
			}
		}
		for (String fg : finalString) {
			String[] string_of_array_set = fg.split("qwer");
			String current_string1 = string_of_array_set[0];
			float current_minXloc1 = Float.parseFloat(string_of_array_set[1]);
			float current_maxx1 = Float.parseFloat(string_of_array_set[2]);
			float current_miny1 = Float.parseFloat(string_of_array_set[3]);
			float current_maxy1 = Float.parseFloat(string_of_array_set[4]);
			float current_height1 = Float.parseFloat(string_of_array_set[5]);
			Header_Of_PDF(current_string1, current_minXloc1, current_maxx1, current_miny1, current_maxy1,
					current_height1, finalString, -5, 7, 5, 5, fileTxt, bank, getpages, accountType, accountNo);
		}
		return fileTxt;	

	}
	public boolean checkOnlyNumber(String str) {
		if (str.contains(","))
			str = str.replaceAll(",", "");
		if (str.contains("."))
			str = StringUtils.remove(str, '.');
		if (str.contains(" "))
			str = StringUtils.remove(str, ' ');

		boolean response = false;
		try {

			if (!StringUtils.isEmpty(str)) {
				long l = Long.parseLong(str);
				response = true;
			}
		} catch (Exception e) {
			return false;
		}
		return response;
	}
	public boolean isValidDate(String inDate) {
		inDate = StringUtils.remove(inDate, ' ');
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");

		dateFormat.setLenient(false);
		dateFormat1.setLenient(false);
		boolean status = false;
		try {
			dateFormat.parse(inDate.trim());
			status = true;
		} catch (ParseException pe) {
			try {
				dateFormat1.parse(inDate.trim());
				status = true;
			} catch (ParseException pe1) {

				status = false;
			}
		}
		return status;
	}
	
	public static boolean ischeckOnlyNumber(String str) {

		if (str.contains(","))
			str = str.replaceAll(",", "");
		if (str.contains("-"))
			str = str.replaceAll("-", "");

		if (str.contains("."))
			str = StringUtils.remove(str, '.');
		if (str.contains(" "))
			str = StringUtils.remove(str, ' ');

		boolean response = false;
		try {

			if (!StringUtils.isEmpty(str)) {
				// str = str.substring(1, str.length() - 1);
				long l = Long.parseLong(str);
				// int i = Integer.parseInt(str);
				response = true;
			}
		} catch (Exception e) {
			return false;
		}
		return response;
	}

}
