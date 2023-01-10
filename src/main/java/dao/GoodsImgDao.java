package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.filechooser.FileNameExtensionFilter;

import vo.GoodsImg;

public class GoodsImgDao {
	
	// RemoveGoodsImg
	public int removeGoodsImg(Connection conn, GoodsImg goodsImg) throws Exception {
		int row = 0;
		String sql = "DELETE FROM goods_img WHERE goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsImg.getGoodsCode());
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	
	// AddGoddsImg
	public int addGoodsImg(Connection conn, GoodsImg goodsImg) throws Exception {
		int row = 0;
		String sql ="INSERT INTO goods_img(goods_code, filename, origin_name, content_type, createdate) VALUES(?, ?, ?, ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsImg.getGoodsCode());
		stmt.setString(2, goodsImg.getFileName());
		stmt.setString(3, goodsImg.getOriginName());
		stmt.setString(4, goodsImg.getContentType());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
}
