package test.historical.eq.nse.inparts.part1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetNSEHistoricalData_fromHome {

	private static final String FILENAME_ALLFNO = "D:\\DHIRAJ\\Fin-Markets-Brokers\\New\\equityData_AllFnO.txt";
	private static final String FILENAME_N150 = "D:\\DHIRAJ\\Fin-Markets-Brokers\\New\\equityData_N150.txt";
	
	public static void main(String[] args) {
		
		int days = 60;
		StringBuilder sb = new StringBuilder("");
		StringBuilder sb150 = new StringBuilder("");
		sb150.append("NAME, DATE, LAST, LL, LH, NEXT, NL, NH"+"\n");
		float ltp = 0.0f;
		URLReader ur = new URLReader();
		Map<Integer, Equity> equityData = new HashMap<Integer, Equity>();
		
		List<String> equityList = new ArrayList<String>();
		getNiftyStockWatch(equityData);

		equityList = getEquityListTest();		
//		equityList = getEquityListAllFNO();
//		equityList = getEquityListNifty150();

//		for(String equity: equityList){
//			if(equity.contains("&")){
//				equityData = URLReader.getUrlContents("https://www.nseindia.com/products/dynaContent/common/productsSymbolMapping.jsp?symbol="+equity.replace("&", "%26")+"&segmentLink=3&symbolCount=1&series=EQ&dateRange=3month&fromDate=&toDate=&dataType=PRICEVOLUMEDELIVERABLE",days, equity);
//			}else{
//				equityData = URLReader.getUrlContents("https://www.nseindia.com/products/dynaContent/common/productsSymbolMapping.jsp?symbol="+equity+"&segmentLink=3&symbolCount=1&series=EQ&dateRange=3month&fromDate=&toDate=&dataType=PRICEVOLUMEDELIVERABLE",days, equity);	
//			}
//			if(equityData.size() == 0){
//				if(equity.contains("&")){
//					equityData = URLReader.getUrlContents("https://www.nseindia.com/products/dynaContent/common/productsSymbolMapping.jsp?symbol="+equity.replace("&", "%26")+"&segmentLink=3&symbolCount=2&series=EQ&dateRange=3month&fromDate=&toDate=&dataType=PRICEVOLUMEDELIVERABLE",days, equity);
//				}else{
//					equityData = URLReader.getUrlContents("https://www.nseindia.com/products/dynaContent/common/productsSymbolMapping.jsp?symbol="+equity+"&segmentLink=3&symbolCount=2&series=EQ&dateRange=3month&fromDate=&toDate=&dataType=PRICEVOLUMEDELIVERABLE",days, equity);	
//				}
//			}
//			
//			
//			
//			//Calculate Average based on input parameter
//			equityData = calculateAverageHighLow(equityData, 16);
//			
//			//display the data obtained:
//			for(Integer in : equityData.keySet()){
//				System.out.println(equityData.get(in).getDate()+", "+equityData.get(in).getName()+", "+equityData.get(in).getOpen()+", "+equityData.get(in).getHigh()+", "+
//						equityData.get(in).getLow()+", "+equityData.get(in).getClose()+", "+equityData.get(in).getVolume()+", "+equityData.get(in).getAvgHigh()+", "+equityData.get(in).getAvgLow());
//			}
//			
//			//Calculate Action points
//			Map<String, List<Action2>> eqActionDetails = new HashMap<String, List<Action2>>();
//			eqActionDetails = calculateActionList(equityData, 16);
//			
//			ltp = getCurrentLtp(equity);
//			System.out.println(eqActionDetails);
//			System.out.println(equity+" LTP: "+ltp);
//			
//			//Get Todays LTP and Deviation
//			float belowLastBuy = 0.00f;
//			float aboveLastSell = 0.00f;
//			Action2 lastAction = null;
//			int actionListSize = 0;
//			if(eqActionDetails.get(new String(equity)) != null && eqActionDetails.get(new String(equity)).size() >= 1){
//				actionListSize = eqActionDetails.get(new String(equity)).size();
//				lastAction = eqActionDetails.get(new String(equity)).get(actionListSize-1);
//				
//				if(lastAction.getAction().equals("B")){
//					lastAction.setNewPl(equityData.get(new Integer(days)).getAvgLow());
//					lastAction.setNewPh(equityData.get(new Integer(days)).getAvgHigh());
//					lastAction.set_FromLast((ltp - lastAction.getBuyAt())/lastAction.getBuyAt() *100);
//					lastAction.set_ToNew((lastAction.getSellAt() - ltp)/ltp *100);
//					lastAction.setNextAction("S");
//				}else if(lastAction.getAction().equals("S")){
//					lastAction.setNewPl(equityData.get(new Integer(days)).getAvgLow());
//					lastAction.setNewPh(equityData.get(new Integer(days)).getAvgHigh());
//					lastAction.set_FromLast((ltp - lastAction.getSellAt())/lastAction.getSellAt() *100);
//					lastAction.set_ToNew((lastAction.getNewPh() - ltp)/ltp *100);
//					lastAction.setNextAction("B");
//				}
//				
//				System.out.println(equity+" : "+lastAction +"\n");
//				
//				
//				if((lastAction.getHighOnActionDay() >  lastAction.getBuyAt()) &&  (ltp < lastAction.getBuyAt())){
//					belowLastBuy = (lastAction.getBuyAt() - ltp) /lastAction.getBuyAt() *100;
//				}
//				if((lastAction.getLowOnActionDay() <  lastAction.getSellAt()) &&  (ltp > lastAction.getSellAt())){
//					aboveLastSell = (ltp - lastAction.getSellAt())/ltp*100 ;
//				}
//				
//				
//			}
//			
//			sb.append(eqActionDetails+"\n");
//			sb.append(" LTP: "+ltp+", "+lastAction+", revertedFromBuyActionBy="+String.format("%.2f", belowLastBuy)
//						+" , revertedFromSellActionBy="+String.format("%.2f", aboveLastSell)+","+lastAction.getNewPl()+","+lastAction.getNewPh()+"\n\n");
//			System.out.println(sb.toString());
//
//			if(lastAction != null){
//				sb150.append(lastAction.getName()+","+lastAction.getDate()+","+lastAction.getAction()+","+lastAction.getSellAt()+","+lastAction.getBuyAt()+","+lastAction.getNextAction()+","+lastAction.getNewPl()+","+lastAction.getNewPh()+"\n");
//			}else{
//				sb150.append(equity+",NODATA,NODATA,NODATA,NODATA,NODATA,NODATA,NODATA"+"\n");
//			}
//			
//			// 	Use This in case equityList = getEquityListAllFNO();
//			//	writeToFile(FILENAME_ALLFNO,sb.toString());
//
//			// 	Use This in case equityList = getEquityListNifty150();
				writeToFile(FILENAME_N150,sb150.toString());
	}

	private static Map<String, List<Action2>> calculateActionList(Map<Integer, Equity> equityData, int averageForDays) {

		Map<String, List<Action2>> returnEquity = new HashMap<String, List<Action2>>();
		List<Action2> actionList = new ArrayList<Action2>();
		String newAction = "B";
		float newPrice = 0.0f;
//		System.out.println(equityData.size());
 		for (int i = averageForDays+1; i<=equityData.size();i++){
// 			System.out.println(i);
			if(equityData.get(new Integer(i)).getHigh() > equityData.get(new Integer(i)).getAvgHigh() && newAction.equals("S")){
				actionList.add(new Action2(i,equityData.get(new Integer(i)).getName(),equityData.get(new Integer(i)).getDate(),equityData.get(new Integer(i)).getAvgHigh(),equityData.get(new Integer(i)).getAvgLow(),equityData.get(new Integer(i)).getLow(),equityData.get(new Integer(i)).getLow(),"B"));
				newAction="B";
				newPrice = equityData.get(new Integer(i)).getAvgHigh();
//				System.out.println(new Action(i,"B",equityData.get(new Integer(i)).getAvgHigh()));
				continue;
			}else if (equityData.get(new Integer(i)).getLow() < equityData.get(new Integer(i)).getAvgLow() && newAction.equals("B")){
				actionList.add(new Action2(i,equityData.get(new Integer(i)).getName(),equityData.get(new Integer(i)).getDate(),equityData.get(new Integer(i)).getAvgHigh(),equityData.get(new Integer(i)).getAvgLow(),equityData.get(new Integer(i)).getLow(),equityData.get(new Integer(i)).getLow(),"S"));
				newAction="S";
				newPrice = equityData.get(new Integer(i)).getAvgLow();
//				System.out.println(new Action(i,"S",equityData.get(new Integer(i)).getAvgLow()));
				continue;
			}

		}
 		
 		if(equityData.get(1) != null){
 			returnEquity.put(equityData.get(1).getName(), actionList);	
 		}
 		
		return returnEquity;
	}

	private static Map<Integer, Equity> calculateAverageHighLow(Map<Integer, Equity> equityData, int averageForDays) {
		
		// Calculate Average High and Low on basis of int averageForDays
		for (int i = averageForDays+1; i<=equityData.size();i++){
			
			float avgH = 0.0f;
			float avgL = 0.0f;
			int counter = 0;
			for(int j=i-1;; j--){
				avgH += equityData.get(new Integer(j)).getHigh();
				avgL += equityData.get(new Integer(j)).getLow();
				counter++;
				if(counter == averageForDays) break;
			}
			equityData.get(new Integer (i)).setAvgHigh(avgH/averageForDays); 
			equityData.get(new Integer (i)).setAvgLow(avgL/averageForDays); 
			
			System.out.println("High I= "+i+","+equityData.get(new Integer (i)).getAvgHigh());
			System.out.println("Low I= "+i+","+equityData.get(new Integer (i)).getAvgLow());
		}
		return equityData;
	}
	
	private static float getCurrentLtp(String equity) {

		URL url = null;
		try {
			url = new URL("https://www.google.com/finance?q=NSE:" + equity);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		URLConnection uc = null;
		try {
			uc = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("URL: "+url.toString());

		String inputLine;
		String value = null;

		try {
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("data-snapfield=\"range\">Range")) {
					int j = 0;
					do {
						inputLine = in.readLine();
						j++;
						// System.out.println(inputLine.substring(inputLine.indexOf(">")+1));
					} while (j < 17);
				}

				if (inputLine.contains("<meta itemprop=\"price\"")) {
//					System.out.println(inputLine);
					inputLine = in.readLine();
					value = inputLine.substring(inputLine.indexOf("\"") + 1, inputLine.lastIndexOf("\""))
							.replaceAll(",", "").replaceAll(" ", "");
//					System.out.println(value);
//					equityList.get(i).setTodayClose(Double.parseDouble(value));
//					System.out.println("Todays Close: "+Double.parseDouble(value));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Float.parseFloat(value);
	}
	
	private static void getTodaysOpenAndRange(String equity) {

		System.out.println("getTodaysOpenAndRange");
		URL url = null;
		try {
			url = new URL("https://www.google.com/finance?q=NSE:" + equity);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		URLConnection uc = null;
		try {
			uc = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("URL: "+url.toString());

		String inputLine;
		String value = null;

		try {
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("data-snapfield=\"range\">Range")) {
					int j = 0;
					do {
						inputLine = in.readLine();
						j++;
						// System.out.println(inputLine.substring(inputLine.indexOf(">")+1));
						if (j == 2) {
							System.out.println(inputLine);
							value = inputLine.substring(inputLine.indexOf(">") + 1).replaceAll(",", "").replaceAll(" ",
									"");
//							equityList.get(i).setTodayLow(Double.parseDouble(value.substring(0, value.indexOf("-"))));
//							equityList.get(i).setTodayHigh(Double.parseDouble(value.substring(value.indexOf("-") + 1)));
							System.out.println("Todays High: "+Double.parseDouble(value.substring(value.indexOf("-") + 1)));
							System.out.println("Todays Low: "+Double.parseDouble(value.substring(0, value.indexOf("-"))));
						}
						if (j == 16) {
							System.out.println(inputLine);
							value = inputLine.substring(inputLine.indexOf(">") + 1).replaceAll(",", "").replaceAll(" ",
									"");
//							equityList.get(i).setTodayOpen(Double.parseDouble(value));
							System.out.println("Todays Open: "+Double.parseDouble(value));
						}
					} while (j < 17);
				}

				if (inputLine.contains("<meta itemprop=\"price\"")) {
					System.out.println(inputLine);
					inputLine = in.readLine();
					value = inputLine.substring(inputLine.indexOf("\"") + 1, inputLine.lastIndexOf("\""))
							.replaceAll(",", "").replaceAll(" ", "");
					System.out.println(value);
//					equityList.get(i).setTodayClose(Double.parseDouble(value));
					System.out.println("Todays Close: "+Double.parseDouble(value));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void writeToFile(String fileName, String data){
		

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

//				String content = "This is the content to write into file\n";

				bw.write(data);

				// no need to close it.
				//bw.close();

				System.out.println("Done");

			} catch (IOException e) {

				e.printStackTrace();

			}

	}
	
	private static List<String> getEquityListTest() {
			
			List<String> equityList = new ArrayList<String>();
			equityList.add("LICHSGFIN");
//			equityList.add("ACC");
//			equityList.add("ARVIND");
//			equityList.add("BEL");
//			equityList.add("GODREJIND");
//			equityList.add("IDBI");
//			equityList.add("IDEA");
//			equityList.add("IDFC");
//			equityList.add("ITC");
//			equityList.add("LT");
//			equityList.add("MRF");
//			equityList.add("SRF");
//			equityList.add("STAR");
//			equityList.add("TCS");

			return equityList;
	}
	
	private static List<String> getEquityListNifty150() {
		
		List<String> equityList = new ArrayList<String>();
		
		equityList.add("ABB");
		equityList.add("ACC");
		equityList.add("ADANIENT");
		equityList.add("ADANIPORTS");
		equityList.add("ADANIPOWER");
		equityList.add("AJANTPHARM");
		equityList.add("AMARAJABAT");
		equityList.add("AMBUJACEM");
		equityList.add("APOLLOHOSP");
		equityList.add("APOLLOTYRE");
		equityList.add("ARVIND");
		equityList.add("ASHOKLEY");
		equityList.add("ASIANPAINT");
		equityList.add("AUROPHARMA");
		equityList.add("AXISBANK");
		equityList.add("BAJAJ-AUTO");
		equityList.add("BAJAJFINSV");
		equityList.add("BAJFINANCE");
		equityList.add("BANKBARODA");
		equityList.add("BANKINDIA");
		equityList.add("BATAINDIA");
		equityList.add("BEL");
		equityList.add("BHARATFORG");
		equityList.add("BHARTIARTL");
		equityList.add("BHEL");
		equityList.add("BIOCON");
		equityList.add("BOSCHLTD");
		equityList.add("BPCL");
		equityList.add("BRITANNIA");
		equityList.add("CADILAHC");
		equityList.add("CANBK");
		equityList.add("CASTROLIND");
		equityList.add("CENTURYTEX");
		equityList.add("CESC");
		equityList.add("CIPLA");
		equityList.add("COALINDIA");
		equityList.add("COLPAL");
		equityList.add("CONCOR");
		equityList.add("CUMMINSIND");
		equityList.add("DABUR");
		equityList.add("DALMIABHA");
		equityList.add("DISHTV");
		equityList.add("DIVISLAB");
		equityList.add("DLF");
		equityList.add("DRREDDY");
		equityList.add("EICHERMOT");
		equityList.add("EMAMILTD");
		equityList.add("ENGINERSIN");
		equityList.add("EXIDEIND");
		equityList.add("FEDERALBNK");
		equityList.add("GAIL");
		equityList.add("GLAXO");
		equityList.add("GLENMARK");
		equityList.add("GMRINFRA");
		equityList.add("GODREJCP");
		equityList.add("GODREJIND");
		equityList.add("GSKCONS");
		equityList.add("HAVELLS");
		equityList.add("HCLTECH");
		equityList.add("HDFC");
		equityList.add("HDFCBANK");
		equityList.add("HEROMOTOCO");
		equityList.add("HINDALCO");
		equityList.add("HINDPETRO");
		equityList.add("HINDUNILVR");
		equityList.add("HINDZINC");
		equityList.add("IBULHSGFIN");
		equityList.add("ICICIBANK");
		equityList.add("ICICIPRULI");
		equityList.add("IDBI");
		equityList.add("IDEA");
		equityList.add("IDFC");
		equityList.add("IDFCBANK");
		equityList.add("IGL");
		equityList.add("INDIGO");
		equityList.add("INDUSINDBK");
		equityList.add("INFRATEL");
		equityList.add("INFY");
		equityList.add("IOC");
		equityList.add("IRB");
		equityList.add("ITC");
		equityList.add("JINDALSTEL");
		equityList.add("JSWENERGY");
		equityList.add("JSWSTEEL");
		equityList.add("JUBLFOOD");
		equityList.add("KOTAKBANK");
		equityList.add("L&TFH");
		equityList.add("LICHSGFIN");
		equityList.add("LT");
		equityList.add("LUPIN");
		equityList.add("M&M");
		equityList.add("M&MFIN");
		equityList.add("MARICO");
		equityList.add("MARUTI");
		equityList.add("MCDOWELL-N");
		equityList.add("MINDTREE");
		equityList.add("MOTHERSUMI");
		equityList.add("MRF");
		equityList.add("MUTHOOTFIN");
		equityList.add("NHPC");
		equityList.add("NMDC");
		equityList.add("NTPC");
		equityList.add("OFSS");
		equityList.add("OIL");
		equityList.add("ONGC");
		equityList.add("PAGEIND");
		equityList.add("PCJEWELLER");
		equityList.add("PEL");
		equityList.add("PETRONET");
		equityList.add("PFC");
		equityList.add("PGHH");
		equityList.add("PIDILITIND");
		equityList.add("PNB");
		equityList.add("POWERGRID");
		equityList.add("RCOM");
		equityList.add("RECLTD");
		equityList.add("RELCAPITAL");
		equityList.add("RELIANCE");
		equityList.add("RELINFRA");
		equityList.add("RPOWER");
		equityList.add("SAIL");
		equityList.add("SBIN");
		equityList.add("SHREECEM");
		equityList.add("SIEMENS");
		equityList.add("SRF");
		equityList.add("SRTRANSFIN");
		equityList.add("STAR");
		equityList.add("SUNPHARMA");
		equityList.add("SUNTV");
		equityList.add("TATACHEM");
		equityList.add("TATACOMM");
		equityList.add("TATAGLOBAL");
		equityList.add("TATAMOTORS");
		equityList.add("TATAMTRDVR");
		equityList.add("TATAPOWER");
		equityList.add("TATASTEEL");
		equityList.add("TCS");
		equityList.add("TECHM");
		equityList.add("TITAN");
		equityList.add("TORNTPHARM");
		equityList.add("TVSMOTOR");
		equityList.add("UBL");
		equityList.add("ULTRACEMCO");
		equityList.add("UNIONBANK");
		equityList.add("UPL");
		equityList.add("VEDL");
		equityList.add("VOLTAS");
		equityList.add("WIPRO");
		equityList.add("WOCKPHARMA");
		equityList.add("YESBANK");
		equityList.add("ZEEL");

		return equityList;
	}
		
	private static List<String> getEquityListAllFNO() {
		
		List<String> equityList = new ArrayList<String>();
		equityList.add("ACC");
		equityList.add("ADANIENT");
		equityList.add("ADANIPORTS");
		equityList.add("ADANIPOWER");
		equityList.add("AJANTPHARM");
		equityList.add("ALBK");
		equityList.add("AMARAJABAT");
		equityList.add("AMBUJACEM");
		equityList.add("ANDHRABANK");
		equityList.add("APOLLOHOSP");
		equityList.add("APOLLOTYRE");
		equityList.add("ARVIND");
		equityList.add("ASHOKLEY");
		equityList.add("ASIANPAINT");
		equityList.add("AUROPHARMA");
		equityList.add("AXISBANK");
		equityList.add("BAJAJ-AUTO");
		equityList.add("BAJAJFINSV");
		equityList.add("BAJFINANCE");
		equityList.add("BALKRISIND");
		equityList.add("BALRAMCHIN");
		equityList.add("BANKBARODA");
		equityList.add("BANKINDIA");
		equityList.add("BATAINDIA");
		equityList.add("BEL");
		equityList.add("BEML");
		equityList.add("BERGEPAINT");
		equityList.add("BHARATFIN");
		equityList.add("BHARATFORG");
		equityList.add("BHARTIARTL");
		equityList.add("BHEL");
		equityList.add("BIOCON");
		equityList.add("BOSCHLTD");
		equityList.add("BPCL");
		equityList.add("BRITANNIA");
		equityList.add("CADILAHC");
		equityList.add("CANBK");
		equityList.add("CANFINHOME");
		equityList.add("CAPF");
		equityList.add("CASTROLIND");
		equityList.add("CEATLTD");
		equityList.add("CENTURYTEX");
		equityList.add("CESC");
		equityList.add("CGPOWER");
		equityList.add("CHENNPETRO");
		equityList.add("CHOLAFIN");
		equityList.add("CIPLA");
		equityList.add("COALINDIA");
		equityList.add("COLPAL");
		equityList.add("CONCOR");
		equityList.add("CUMMINSIND");
		equityList.add("DABUR");
		equityList.add("DALMIABHA");
		equityList.add("DCBBANK");
		equityList.add("DHFL");
		equityList.add("DISHTV");
		equityList.add("DIVISLAB");
		equityList.add("DLF");
		equityList.add("DRREDDY");
		equityList.add("EICHERMOT");
		equityList.add("ENGINERSIN");
		equityList.add("EQUITAS");
		equityList.add("ESCORTS");
		equityList.add("EXIDEIND");
		equityList.add("FEDERALBNK");
		equityList.add("FORTIS");
		equityList.add("GAIL");
		equityList.add("GLENMARK");
		equityList.add("GMRINFRA");
		equityList.add("GODFRYPHLP");
		equityList.add("GODREJCP");
		equityList.add("GODREJIND");
		equityList.add("GRANULES");
		equityList.add("GRASIM");
		equityList.add("GSFC");
		equityList.add("HAVELLS");
		equityList.add("HCC");
		equityList.add("HCLTECH");
		equityList.add("HDFC");
		equityList.add("HDFCBANK");
		equityList.add("HDIL");
		equityList.add("HEROMOTOCO");
		equityList.add("HEXAWARE");
		equityList.add("HINDALCO");
		equityList.add("HINDPETRO");
		equityList.add("HINDUNILVR");
		equityList.add("HINDZINC");
		equityList.add("IBREALEST");
		equityList.add("IBULHSGFIN");
		equityList.add("ICICIBANK");
		equityList.add("ICICIPRULI");
		equityList.add("ICIL");
		equityList.add("IDBI");
		equityList.add("IDEA");
		equityList.add("IDFC");
		equityList.add("IDFCBANK");
		equityList.add("IFCI");
		equityList.add("IGL");
		equityList.add("INDIACEM");
		equityList.add("INDIANB");
		equityList.add("INDIGO");
		equityList.add("INDUSINDBK");
		equityList.add("INFIBEAM");
		equityList.add("INFRATEL");
		equityList.add("INFY");
		equityList.add("IOC");
		equityList.add("IRB");
		equityList.add("ITC");
		equityList.add("JETAIRWAYS");
		equityList.add("JINDALSTEL");
		equityList.add("JISLJALEQS");
		equityList.add("JPASSOCIAT");
		equityList.add("JSWENERGY");
		equityList.add("JSWSTEEL");
		equityList.add("JUBLFOOD");
		equityList.add("JUSTDIAL");
		equityList.add("KAJARIACER");
		equityList.add("KOTAKBANK");
		equityList.add("KPIT");
		equityList.add("KSCL");
		equityList.add("KTKBANK");
		equityList.add("L&TFH");
		equityList.add("LICHSGFIN");
		equityList.add("LT");
		equityList.add("LUPIN");
		equityList.add("M&M");
		equityList.add("M&MFIN");
		equityList.add("MANAPPURAM");
		equityList.add("MARICO");
		equityList.add("MARUTI");
		equityList.add("MCDOWELL-N");
		equityList.add("MCX");
		equityList.add("MFSL");
		equityList.add("MGL");
		equityList.add("MINDTREE");
		equityList.add("MOTHERSUMI");
		equityList.add("MRF");
		equityList.add("MRPL");
		equityList.add("MUTHOOTFIN");
		equityList.add("NATIONALUM");
		equityList.add("NBCC");
		equityList.add("NCC");
		equityList.add("NESTLEIND");
		equityList.add("NHPC");
		equityList.add("NIITTECH");
		equityList.add("NMDC");
		equityList.add("NTPC");
		equityList.add("OFSS");
		equityList.add("OIL");
		equityList.add("ONGC");
		equityList.add("ORIENTBANK");
		equityList.add("PAGEIND");
		equityList.add("PCJEWELLER");
		equityList.add("PEL");
		equityList.add("PETRONET");
		equityList.add("PFC");
		equityList.add("PIDILITIND");
		equityList.add("PNB");
		equityList.add("POWERGRID");
		equityList.add("PTC");
		equityList.add("PVR");
		equityList.add("RAMCOCEM");
		equityList.add("RAYMOND");
		equityList.add("RBLBANK");
		equityList.add("RCOM");
		equityList.add("RDEL");
		equityList.add("RECLTD");
		equityList.add("RELCAPITAL");
		equityList.add("RELIANCE");
		equityList.add("RELINFRA");
		equityList.add("REPCOHOME");
		equityList.add("RPOWER");
		equityList.add("SAIL");
		equityList.add("SBIN");
		equityList.add("SHREECEM");
		equityList.add("SIEMENS");
		equityList.add("SINTEX");
		equityList.add("SOUTHBANK");
		equityList.add("SREINFRA");
		equityList.add("SRF");
		equityList.add("SRTRANSFIN");
		equityList.add("STAR");
		equityList.add("SUNPHARMA");
		equityList.add("SUNTV");
		equityList.add("SUZLON");
		equityList.add("SYNDIBANK");
		equityList.add("TATACHEM");
		equityList.add("TATACOMM");
		equityList.add("TATAELXSI");
		equityList.add("TATAGLOBAL");
		equityList.add("TATAMOTORS");
		equityList.add("TATAMTRDVR");
		equityList.add("TATAPOWER");
		equityList.add("TATASTEEL");
		equityList.add("TCS");
		equityList.add("TECHM");
		equityList.add("TITAN");
		equityList.add("TORNTPHARM");
		equityList.add("TORNTPOWER");
		equityList.add("TV18BRDCST");
		equityList.add("TVSMOTOR");
		equityList.add("UBL");
		equityList.add("UJJIVAN");
		equityList.add("ULTRACEMCO");
		equityList.add("UNIONBANK");
		equityList.add("UPL");
		equityList.add("VEDL");
		equityList.add("VGUARD");
		equityList.add("VOLTAS");
		equityList.add("WIPRO");
		equityList.add("WOCKPHARMA");
		equityList.add("YESBANK");
		equityList.add("ZEEL");
		
		return equityList;
	}
	
	private static void getNiftyStockWatch(Map<Integer, Equity> equityData) {
		URL url = null;
		URLConnection uc = null;
		BufferedReader in = null;
		String inputLine;
		String value = null;
		String [] urls = new String[] {"https://www.nseindia.com/live_market/dynaContent/live_watch/stock_watch/niftyStockWatch.json",
				"https://www.nseindia.com/live_market/dynaContent/live_watch/stock_watch/niftyMidcap50StockWatch.json",
				"https://www.nseindia.com/live_market/dynaContent/live_watch/stock_watch/juniorNiftyStockWatch.json"};
		for(int source = 0; source < urls.length; source++) {
			int index = source*50;
			try {
				url = new URL(urls[source]);
				uc = url.openConnection();
				in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				StringBuffer sb = new StringBuffer();
				// System.out.println("URL: "+url.toString());
				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
				}
				System.out.println(inputLine);
				JSONObject jsonObject = new JSONObject(sb.toString());
				System.out.println(jsonObject.get("declines"));
				System.out.println(jsonObject.get("advances"));
				JSONArray data = (JSONArray) jsonObject.get("data");
				System.out.println(data.get(0));
				
				for (int i=index; i<(index+data.length()); i++){
					System.out.print((data.getJSONObject(i).getString("symbol")));
					equityData.put(new Integer(i), new Equity(data.getJSONObject(i).getString("symbol")));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			source=1;
		}
		
		System.out.println(equityData.size());
	}

}
