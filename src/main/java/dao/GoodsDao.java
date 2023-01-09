package dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Goods;

public class GoodsDao {
	
	// GoodsOne
	public ArrayList<HashMap<String, Object>> goodsOne(Connection conn, int goodsCode) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT g.goods_code goodsCode, g.goods_name goodsName, g.goods_price goodsPrice, g.goods_memo goodsMemo, gi.filename fileName FROM goods g INNER JOIN goods_img gi ON g.goods_code = gi.goods_code WHERE g.goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getString("goodsPrice"));
			m.put("goodsMemo", rs.getString("goodsMemo"));
			m.put("fileName", rs.getString("fileName"));
			list.add(m);
		}
		rs.close();
		stmt.close();
		
		return list;
	}
	// 상품 전체 수 구하기
	public int goodsCount(Connection conn) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt FROM goods";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		rs.close();
		stmt.close();

		return cnt;
	}
	
	
	// GoodsList 검색기능 추가해야 됨.. -
	public ArrayList<HashMap<String, Object>> selectGoodsList(Connection conn, int beginRow, int endRow, String word) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();		
		PreparedStatement stmt = null;
		if(word == null || word.equals("")) {
			String sql = "SELECT g.goods_code goodsCode, g.goods_name goodsName, g.goods_price goodsPrice, g.goods_memo goodsMemo, gi.filename fileName FROM goods g INNER JOIN goods_img gi ON g.goods_code = gi.goods_code";
			stmt = conn.prepareStatement(sql);
		} else {
			String sql = "SELECT g.goods_code goodsCode, g.goods_name goodsName, g.goods_price goodsPrice, g.goods_memo goodsMemo, gi.filename fileName FROM goods g INNER JOIN goods_img gi ON g.goods_code = gi.goods_code WHERE goods_name LIKE ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
		}

		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getString("goodsPrice"));
			m.put("goodsMemo", rs.getString("goodsMemo"));
			m.put("fileName", rs.getString("fileName"));
			list.add(m);
		}
		rs.close();
		stmt.close();
		
		return list;
	}
	
	
	// AddGoods
	public HashMap<String, Integer> addGoods(Connection conn, Goods goods) throws Exception {
		String sql ="INSERT INTO goods(goods_name, goods_price, goods_memo, soldout, emp_id, hit, createdate) VALUES(?, ?, ?, ?, ?, ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		// PreparedStatement.RETURN_GENERATED_KEYS 쿼리실행 후 생성된 auto_increment값을 ResultSet에 반환
		stmt.setString(1, goods.getGoodsName());
		stmt.setInt(2, goods.getGoodsPrice());
		stmt.setString(3, goods.getGoodsMemo());
		stmt.setString(4, goods.getSoldout());
		stmt.setString(5, goods.getEmpId());
		stmt.setInt(6, goods.getHit());
		
		int row = stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		int autoKey = 0;
		if(rs.next()) {
			autoKey = rs.getInt(1);
		}
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("row", row);
		map.put("autoKey", autoKey);
		
		rs.close();
		stmt.close();
		return map;
	}
		
}
