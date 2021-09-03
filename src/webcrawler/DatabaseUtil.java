package webcrawler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseUtil {

	public void saveProduct(Product product)throws SQLException {
		String query = "insert into paytmproducts(productname,listprice,sellingprice,os,camera,color,screen,simtype,intmemory,ram,warranty,brand,processor,sim,primarycamera,seccamera,batterytype,batterycapacity,talktime,sensors,importantapps,otherfeatures,inthebox,returnpolicy,couponcode,offer) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt = conn.prepareStatement(query);
		stmt.setString(1, product.getProductName());
		stmt.setString(2, product.getListPrice());
		stmt.setString(3, product.getSellingPrice());
		stmt.setString(4, product.getOs());
		stmt.setString(5, product.getCamera());
		stmt.setString(6, product.getColor());
		stmt.setString(7, product.getScreen_size());
		stmt.setString(8, product.getSim_type());
		stmt.setString(9, product.getInternal_memory());
		stmt.setString(10, product.getRam());
		stmt.setString(11, product.getWarranty());
		stmt.setString(12, product.getBrand());
		stmt.setString(13, product.getProcessor());
		stmt.setString(14, product.getSim());
		stmt.setString(15, product.getPrimarycamera());
		stmt.setString(16, product.getSecondarycamera());
		stmt.setString(17, product.getBatterytype());
		stmt.setString(18, product.getBatterycapacity());
		stmt.setString(19, product.getTalktime());
		stmt.setString(20, product.getSensors());
		stmt.setString(21, product.getImportantapps());
		stmt.setString(22, product.getOtherfeatures());
		stmt.setString(23, product.getInsalespackage());
		stmt.setString(24, product.getReturnpolicy());
		stmt.setString(25, product.getCouponcode());
		stmt.setString(26, product.getOffer());
		
		stmt.execute();
		stmt.close();
		conn.close();
		
	}
	
	public void saveHistory(History history)throws SQLException {
		String query = "insert into history(starttime,endtime,lasturl) values(?,?,?)";
		
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt = conn.prepareStatement(query);
		
		stmt.setString(1, history.getStarttime());
		stmt.setString(2, history.getEndtime());
		stmt.setString(3, history.getLasturl());
				
		stmt.execute();
		stmt.close();
		conn.close();
	}
	
//	public void updateHistory(Product product)throws SQLException {
//		
//		String query = "update history set endtime = ?";
//		
//		Connection conn = getConnection();
//		PreparedStatement stmt = conn.prepareStatement(query);
//		stmt = conn.prepareStatement(query);
//		
//		stmt.setString(1, product.getEndtime());
//		stmt.setString(2, product.getLasturl());
////		ResultSet rs=stmt.getGeneratedKeys();
////		if(rs.next())
////		{
////		 id = rs.getInt(1);
////		 System.out.println(id);
////		}rs.close();
////		
//		
//		stmt.executeUpdate();
//		stmt.close();
//		conn.close();
//	}
	
	/*public void saveProductPage1(String productname, String listprice, String sellingprice, String specs)throws SQLException {
		String query = "insert into paytmproducts(productname,listprice,sellingprice,specs) + values(?,?,?,?)";
		
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt = conn.prepareStatement(query);
		stmt.setString(1, productname);
		stmt.setString(2, listprice);
		stmt.setString(3, sellingprice);
		stmt.setString(4, specs);
		
		stmt.execute();
		stmt.close();
		conn.close();
		
		
	}

	public void saveProductPage2(String os, String camera, String color, String screen, String simtype,
			String intmemory, String ram) throws SQLException {
		String query = "update paytmproducts os=?,camera=?,color=?,screen=?,simtype=?,intmemory=?,ram=? where id=?";
		try {
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt = conn.prepareStatement(query);
		stmt.setString(1, os);
		stmt.setString(2, camera);
		stmt.setString(3, color);
		stmt.setString(4, screen);
		stmt.setString(5, simtype);
		stmt.setString(6, intmemory);
		stmt.setString(7, ram);
		stmt.executeUpdate();
		stmt.close();
		conn.close();
		
	}catch(Exception ex){
        ex.printStackTrace();
    }
}

	public void saveProductPage3(String warranty) throws SQLException {
		String query = "update paytmproducts warranty=? where id=?";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);

		stmt.setString(1, warranty);
		stmt.executeUpdate();
		stmt.close();
		conn.close();
	}

	public void saveProductPage4(String processor) throws SQLException {
		String query = "update paytmproducts set processor=? where id=?";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, processor);

		stmt.executeUpdate();
		stmt.close();
		conn.close();
	}

	public void saveProductPage5(String primarycamera) throws SQLException {
		String query = "update paytmproducts set primarycamera=? where id=?";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, primarycamera);

		stmt.execute();
		stmt.close();
		conn.close();
	}

	public void saveProductPage6(String seccamera) throws SQLException {
		String query = "update paytmproducts set seccamera=? + where id=?";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, seccamera);

		stmt.execute();
		stmt.close();
		conn.close();
	}

	public void saveProductPage7(String camerafeatures) throws SQLException {
		String query = "insert into paytmproducts(camerafeatures) + value(?)";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, camerafeatures);

		stmt.execute();
		stmt.close();
		conn.close();
	}

	public void saveProductPage8(String batterytype) throws SQLException {
		String query = "insert into paytmproducts(batterytype) + value(?)";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, batterytype);

		stmt.execute();
		stmt.close();
		conn.close();
	}

	public void saveProductPage9(String batterycapacity) throws SQLException {
		String query = "insert into paytmproducts(batterycapacity) + value(?)";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, batterycapacity);

		stmt.execute();
		stmt.close();
		conn.close();
	}

	public void saveProductPage10(String talktime) throws SQLException {
		String query = "insert into paytmproducts(talktime)+ value(?)";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, talktime);

		stmt.execute();
		stmt.close();
		conn.close();
	}

	public void saveProductPage11(String musicplayer) throws SQLException {
		String query = "insert into paytmproducts(musicplayer)+ value(?)";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, musicplayer);

		stmt.execute();
		stmt.close();
		conn.close();
	}

	public void saveProductPage12(String importantapps) throws SQLException {
		String query = "insert into paytmproducts(importantapps)+ value(?)";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, importantapps);
		stmt.execute();
		stmt.close();
		conn.close();
	}

	public void saveProductPage13(String inthebox) throws SQLException {
		String query = "insert into paytmproducts(inthebox)+ value(?)";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, inthebox);

		stmt.execute();
		stmt.close();
		conn.close();
	}
	public void saveProductPage14(String returnpolicy) throws SQLException {
		String query = "insert into paytmproducts(returnpolicy)+ value(?)";
		PreparedStatement stmt = null;
		Connection conn = getConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, returnpolicy);

		stmt.execute();
		stmt.close();
		conn.close();
	}*/
	private Connection getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/producturlpaytm";
		String username = "root";
		String password = "root";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
//	private Connection getConnection1() {
//		String driver = "com.mysql.jdbc.Driver";
//		String url = "jdbc:mysql://localhost:3306/crawlinghistory";
//		String username = "root";
//		String password = "root";
//		Connection conn = null;
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, username, password);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return conn;
//	}
}


