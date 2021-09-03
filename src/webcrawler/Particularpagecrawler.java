package webcrawler;


import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Particularpagecrawler {
	public void getAndSaveProductData(String pageUrl) throws IOException, InterruptedException, JSONException {
		String url10 = pageUrl.replace("catalog.", "");
		StringBuilder str1 = new StringBuilder(url10);
		StringBuilder productUrl2 = str1.insert(13, "mall");
		String upurl = productUrl2.toString();
		Document doc=null;
		try{
			doc = Jsoup.connect(upurl).ignoreHttpErrors(true).get();
		}catch(NullPointerException e) {e.printStackTrace();}
		
		Element body = doc.getElementById("app");

		Elements cl7 = body.getElementsByClass("NZJI");
		{
			System.out.println("Product Name:" + cl7.text());

		}
		Elements cl5 = body.getElementsByClass("_2b_6");
		{
			System.out.println("List Price:" + cl5.text());

		}
		Elements cl6 = body.getElementsByClass("_1V3w");
		{
			System.out.println("Selling Price:" + cl6.text());

		}

		Elements cl3 = body.getElementsByClass("FqsW");
		{
			System.out.println("Product Details:\n" + cl3.text());

			System.out.println("---------------------");
		}
		StringBuilder str2 = new StringBuilder(upurl);
		StringBuilder str3 = str2.insert(8, "middleware.");
		String upurl2 = str3.toString();

		Product productObj = new Product();
		productObj.setProductName(cl7.text());
		productObj.setListPrice(cl5.text());
		productObj.setSellingPrice(cl6.text());
		productObj.setPaytmUrl(upurl2);

		JsonReader2.getPaytmDataFromProduct(productObj);

		try {
			new DatabaseUtil().saveProduct(productObj);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}