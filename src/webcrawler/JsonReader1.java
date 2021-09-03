package webcrawler;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader1 {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

//	public static void main(String[] args) throws IOException, JSONException, InterruptedException {
//
//		Boolean datafound = true;
//		int counter = 1;
//		while (datafound) {
//			String response = getPaytmDataFromPage1(counter);
//			counter++;
//			if (response == null) {
//				break;
//			}
//		}
//
//	}
	

	public static String getPaytmDataFromPage(String url1) throws IOException, JSONException, InterruptedException {
		String upurl =url1;
		String url2 = upurl.replace("mall", "");
		StringBuilder str = new StringBuilder(url2);
		StringBuilder url3 = str.insert(8, "catalog.");
		String url4=url3.toString();
		Boolean datafound = true;
		int counter = 1;
		String response1 = null;
		String previousResponse = null;
		while (datafound) {
			response1 = getPaytmDataFromUrl(url4,counter);
			if(response1 != null) {
				previousResponse = response1;
			}
			counter++;
			System.out.println(counter);
			if (response1 == null) {
				break;
			}
		}

		return previousResponse;
	}
	static String getPaytmDataFromUrl(String url,int pageNum) throws IOException, JSONException, InterruptedException {
		//String url99= request.getParameter("url");
		//String url1 = "https://paytmmall.com/shop/g/honor-smart-phones-bclpid-189002-66781?src=store";
//		String url2 = url.replace("mall", "");
//		StringBuilder str = new StringBuilder(url2);
//		StringBuilder url3 = str.insert(8, "catalog.");
		String upurl = (new StringBuilder()).append(url)
				.append("?channel=web&child_site_id=6&site_id=2&version=2&page_count=").append(pageNum)
				.append("&items_per_page=30").toString();
        
		JSONObject json = readJsonFromUrl(upurl);
		JSONArray productJsonArray = json.getJSONArray("grid_layout");

		if (productJsonArray.length() == 0) {
			System.out.println("end of products");
			
			return null;
		}
		
		for (int i = 0; i < productJsonArray.length(); i++) {
			
			JSONObject productJson = (JSONObject) productJsonArray.get(i);
			String productUrl = productJson.getString("newurl").split("&")[0];
			System.out.println(productUrl);
			new Particularpagecrawler().getAndSaveProductData(productUrl);
			
		}
		JSONObject lastObj = productJsonArray.getJSONObject(productJsonArray.length()-1);
		String lastUrl = lastObj.optString("newurl");
		System.out.println(lastUrl);
		String response= lastUrl;
		/*try {
			new DatabaseUtil().saveHistory(history);

		} catch (SQLException e) {
			e.printStackTrace();
		}*/

		try {

			System.out.println("Waiting for 1 seconds");
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	
	}
	
	
	
}
