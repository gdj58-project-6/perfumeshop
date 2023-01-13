package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Customer;
import vo.CustomerAddress;

public class CustomerAddressDao {
	public int insertCustomerAddress(Connection conn, CustomerAddress customerAddress) throws Exception {
		int row = 0;
		String sql = "INSERT INTO customer_address(customer_id, address, createdate) VALUES(?, ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerAddress.getCutomerId());
		stmt.setString(2, customerAddress.getAddress());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
}
