package test.historical.eq.nse.inparts.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class URLReader
{
  public static void main(String[] args)
  {
	int days = 30;
	String equityName = "ADANIPORTS";
	System.out.println("https://www.nseindia.com/products/dynaContent/common/productsSymbolMapping.jsp?symbol=sbin&segmentLink=3&symbolCount=1&series=EQ&dateRange=1month&fromDate=&toDate=&dataType=PRICEVOLUMEDELIVERABLE");
	Map<Integer, Equity> dayToPrices = getUrlContents("https://www.nseindia.com/products/dynaContent/common/productsSymbolMapping.jsp?symbol="+equityName+"&segmentLink=3&symbolCount=1&series=EQ&dateRange=1month&fromDate=&toDate=&dataType=PRICEVOLUMEDELIVERABLE",days, equityName);
	
	for(int i=1; i<=days;i++){
		System.out.println(dayToPrices.get(new Integer(i)));	
	}
    
  }

  
	public static Map<Integer, Equity> getUrlContents(final String theUrl, int days, String equityName) {

		System.out.println(theUrl);
		int i=1;
		// Read Data map.
		Map<Integer, Equity> dayToPrices = new HashMap<Integer, Equity>();

		URL url = null;
		URLConnection urlConnection = null;
		HttpURLConnection connection = null;
		BufferedReader br = null;
		InputStreamReader isr = null;
		try {
			String current = null;
			url = new URL(theUrl);
			urlConnection = url.openConnection();
			if (urlConnection instanceof HttpURLConnection) {
				System.out.println("Set connection");
				connection = (HttpURLConnection) urlConnection;
				connection.setRequestProperty("Accept", "*/*");
				connection.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
				connection.setRequestProperty("Connection", "keep-alive");
				connection.setRequestProperty("Host", "www.nseindia.com");
				connection.setRequestProperty("Referer",
						"https://www.nseindia.com/products/content/equities/equities/eq_security.htm");
				connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

				connection.setRequestMethod("GET");
			}

			isr = new InputStreamReader(connection.getInputStream());
			br = new BufferedReader(isr);

			  String line;
		      String date = null;
		      float open= 0.0f;
		      float high= 0.0f;
		      float low= 0.0f;
		      float close= 0.0f;
		      long volume= 0;
		      Equity eq = null;
		      
		      int column = 0;
			while ((current = br.readLine()) != null) {
				
//				if(current.contains("<td class=\"date\" nowrap>") || current.contains("<td class=\"number\" nowrap>")) {
					if(current.contains("<td class") && current.contains("</td>")){
						column++;
						if(current.contains("<td class=\"date\" nowrap>")){
							date = current.substring(current.indexOf("nowrap>")+"nowrap>".length(),current.indexOf("</td>"));
						}else if(current.contains("<td class=\"number\" nowrap>")){
							if(column == 5){
								open = Float.parseFloat(current.substring(current.indexOf("nowrap>")+"nowrap>".length(),current.indexOf("</td>")).replace(",", ""));
							}
							if(column == 6){
								high = Float.parseFloat(current.substring(current.indexOf("nowrap>")+"nowrap>".length(),current.indexOf("</td>")).replace(",", ""));
							}
							if(column == 7){
								low = Float.parseFloat(current.substring(current.indexOf("nowrap>")+"nowrap>".length(),current.indexOf("</td>")).replace(",", ""));
							}
							if(column == 9){
								close = Float.parseFloat(current.substring(current.indexOf("nowrap>")+"nowrap>".length(),current.indexOf("</td>")).replace(",", ""));
							}
							
							if(column == 11){
								eq =  new Equity();
								volume = Long.parseLong(current.substring(current.indexOf("nowrap>")+"nowrap>".length(),current.indexOf("</td>")).replace(",", ""));
								eq.setName(equityName.toUpperCase());
				    			eq.setDate(date);
				    			eq.setOpen(open);
				    			eq.setHigh(high);
				    			eq.setLow(low);
				    			eq.setClose(close);
				    			eq.setVolume(volume);
				    			
				    			dayToPrices.put(i, eq);
				    			
				    			date = null; open = 0.0f; high = 0.0f; low=0.0f; close =0.0f; volume = 0; 
				    			i++;
				    			
							}
							if(column == 15){
								column = 0;
							}
						}
					}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (isr != null) {
					isr.close();
				}

				if (br != null) {
					br.close();
				}

				if (connection != null) {
					connection.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return dayToPrices;
	}

}