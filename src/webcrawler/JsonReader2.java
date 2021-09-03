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

public class JsonReader2 {

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

	public static void main(String[] args) throws IOException, JSONException, InterruptedException {

		getPaytmDataFromProduct(null);

	}

	static String getPaytmDataFromProduct(Product product) {
		try {
			String url1 = product.getPaytmUrl();
			

			JSONObject json = readJsonFromUrl(url1);
			JSONObject jsonObject = (JSONObject) json;
			JSONObject productJsonAtrributes = (JSONObject) jsonObject.get("attributes");
			
			product.setOs(productJsonAtrributes.getString("os"));
			product.setCamera(productJsonAtrributes.getString("camera"));
			product.setColor(productJsonAtrributes.getString("color"));
			product.setScreen_size(productJsonAtrributes.getString("screen_size"));
			product.setSim_type(productJsonAtrributes.getString("sim_type"));
			product.setInternal_memory(productJsonAtrributes.getString("internal_memory"));
			product.setRam(productJsonAtrributes.getString("ram"));
			
			System.out.println("OS:" + productJsonAtrributes.getString("os"));
			System.out.println("Camera:" + productJsonAtrributes.getString("camera"));
			System.out.println("Color:" + productJsonAtrributes.getString("color"));
			System.out.println("Screen Size:" + productJsonAtrributes.getString("screen_size"));
			System.out.println("Sim Type:" + productJsonAtrributes.getString("sim_type"));
			System.out.println("Internal Memory:" + productJsonAtrributes.getString("internal_memory"));
			System.out.println("Ram:" + productJsonAtrributes.getString("ram"));
			/*try {
				new DatabaseUtil().saveProductPage2(productJsonAtrributes.getString("os"),productJsonAtrributes.getString("camera"),productJsonAtrributes.getString("color"),productJsonAtrributes.getString("screen_size"),productJsonAtrributes.getString("sim_type"),productJsonAtrributes.getString("internal_memory"),productJsonAtrributes.getString("ram"));

			} catch (SQLException e) {
				e.printStackTrace();
			}*/
						
			
			JSONArray productJsonAtrributes1 = jsonObject.getJSONArray("long_rich_desc");
			
			for (int i = 0; i < productJsonAtrributes1.length(); i++) {
				JSONObject jsonObject1 = productJsonAtrributes1.getJSONObject(i);

				String jsonTitle = jsonObject1.getString("title");
				if ("Warranty Details".equals(jsonTitle)) {
					
					JSONObject attributes = (JSONObject) jsonObject1.get("attributes");
				
					String pro = attributes.getString("Warranty Summary");
					System.out.println("Warranty:" + pro);
					product.setWarranty(attributes.getString("Warranty Summary"));
					/*try {
						new DatabaseUtil().saveProductPage3(pro);
					} catch (SQLException e) {
						e.printStackTrace();
					}*/
					
				} else if ("General".equals(jsonTitle)) {
					JSONObject attributes = (JSONObject) jsonObject1.get("attributes");
					if (attributes.has("Brands")) {
						
						String pro1 = attributes.getString("Brand");
						System.out.println("Brand:" + pro1);
						product.setBrand(attributes.getString("Brand"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro1);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}
					if (attributes.has("Processor")) {
						
						String pro7 = attributes.getString("Processor");
						System.out.println("Processor:" + pro7);
						product.setProcessor(attributes.getString("Processor"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro7);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}
					if (attributes.has("Sim Type")) {
						
						String pro4 = attributes.getString("Sim Type");
						System.out.println("Sim Type:" + pro4);
						product.setSim(attributes.getString("Sim Type"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro4);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}

				} else if ("Camera & Video Features".equals(jsonTitle)) {
					JSONObject attributes = (JSONObject) jsonObject1.get("attributes");
					if (attributes.has("Primary Camera")) {
						
						String pro5 = attributes.getString("Primary Camera");
						System.out.println("Primary Camera:" + pro5);
						product.setPrimarycamera(attributes.getString("Primary Camera"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro5);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}
					if (attributes.has("Secondary Camera")) {
						
						String pro6 = attributes.getString("Secondary Camera");
						System.out.println("Secondary Camera:" + pro6);
						product.setSecondarycamera(attributes.getString("Secondary Camera"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro6);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}

				} else if ("Battery & Power Features".equals(jsonTitle)) {
					JSONObject attributes = (JSONObject) jsonObject1.get("attributes");
					if (attributes.has("Battery Type")) {
						
						String pro8 = attributes.getString("Battery Type");
						System.out.println("Battery Type:" + pro8);
						product.setBatterytype(attributes.getString("Battery Type"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro8);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}
					if (attributes.has("Battery Capacity")) {
						
						String pro9 = attributes.getString("Battery Capacity");
						System.out.println("Battery Capacity:" + pro9);
						product.setBatterycapacity(attributes.getString("Battery Capacity"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro9);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}
					if (attributes.has("Talk Time")) {
						
						String pro10 = attributes.getString("Talk Time");
						System.out.println("Talk Time:" + pro10);
						product.setTalktime(attributes.getString("Talk Time"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro10);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}
				} else if ("Network Compatibility".equals(jsonTitle)) {
					JSONObject attributes = (JSONObject) jsonObject1.get("attributes");
					if (attributes.has("2G Bands")) {
						String pro11 = attributes.getString("2G Bands");
						System.out.println("2G:" + pro11);
					}
					if (attributes.has("3G Bands")) {
						String pro12 = attributes.getString("3G Bands");
						System.out.println("3G:" + pro12);
					}
					if (attributes.has("4G Bands")) {
						String pro13 = attributes.getString("4G Bands");
						System.out.println("4G:" + pro13);
					}

				} else if ("Additional Features".equals(jsonTitle)) {
					JSONObject attributes = (JSONObject) jsonObject1.get("attributes");
					if (attributes.has("Sensors")) {
						
						String pro17 = attributes.getString("Sensors");
						System.out.println("Sensors:" + pro17);
						product.setSensors(attributes.getString("Sensors"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro17);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}else if (attributes.has("Important Apps")) {
						
						String pro18 = attributes.getString("Important Apps");
						System.out.println("Important Apps:" + pro18);
						product.setImportantapps(attributes.getString("Important Apps"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro18);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}else if (attributes.has("Other Features")) {
						
						String pro19 = attributes.getString("Other Features");
						System.out.println("Other Features:" + pro19);
						product.setOtherfeatures(attributes.getString("Other Features"));
						/*try {
							new DatabaseUtil().saveProductPage3(pro19);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}
				} else if ("In The Box".equals(jsonTitle)) {
					
					JSONObject attributes = (JSONObject) jsonObject1.get("attributes");
					product.setInsalespackage(attributes.getString("In Sales Package"));
					String pro20 = attributes.getString("In Sales Package");
					System.out.println("In The Box:" + pro20);
					/*try {
						new DatabaseUtil().saveProductPage3(pro20);
					} catch (SQLException e) {
						e.printStackTrace();
					}*/
				} else if ("Return Policy".equals(jsonTitle)) {
					JSONObject attributes = (JSONObject) jsonObject1.get("attributes");
					product.setReturnpolicy(attributes.getString("Return Policy"));
					String pro21 = attributes.getString("Return Policy");
					System.out.println("Return Policy:" + pro21);
					/*try {
						new DatabaseUtil().saveProductPage3(pro21);
					} catch (SQLException e) {
						e.printStackTrace();
					}*/
				}

			}
			
			String productJsonOffers  = jsonObject.getString("offer_url");
			
			String offerurl=(new StringBuilder()).append("https://paytmmall.com/papi").append(productJsonOffers).toString();
			System.out.println(offerurl);
			JSONObject json1 = readJsonFromUrl(offerurl);
			
			JSONObject jsonObjecturl = (JSONObject) json1;
			
			{
				JSONArray productJsoncodes = jsonObjecturl.getJSONArray("codes");

				for (int i = 0; i < productJsoncodes.length(); i++) {
					JSONObject jsonObject1 = productJsoncodes.getJSONObject(i);
					
				String jsonCouponCode=jsonObject1.getString("code");	
				System.out.println("Coupon Code:"+jsonCouponCode);
				product.setCouponcode(jsonObject1.getString("code"));
				String jsonCodes = jsonObject1.getString("offerText");
				System.out.println("Product Offers:"+jsonCodes);
				product.setOffer(jsonObject1.getString("offerText"));
			}

											
				
				
			
			}		
		}catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		return "success";
	}
	
	
}
