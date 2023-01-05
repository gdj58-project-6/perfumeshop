package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.GoodsImg;

public class GoodsImgDao {
	public int addGoodsImg(Connection conn, GoodsImg goodsImg) throws Exception {
		int row = 0;
		String sql ="INSERT INTO goods_img(filename, origin_name, content_type, createdate) VALUES(?, ?, ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, goodsImg.getFileName());
		stmt.setString(2, goodsImg.getOriginName());
		stmt.setString(3, goodsImg.getContentType());
		row = stmt.executeUpdate();
		return row;
	}
}
