 package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CartDao;
import dao.CustomerDao;
import dao.OrderDao;
import dao.OrderGoodsDao;
import dao.PointHistoryDao;
import util.DBUtil;
import vo.Customer;
import vo.OrderGoods;
import vo.Orders;
import vo.PointHistory;

public class OrderService {
	private OrderDao orderDao;
	private OrderGoodsDao orderGoodsDao;
	private CartDao cartDao;
	private CustomerDao customerDao;
	private PointHistoryDao pointHistoryDao;
	// 카트에서 주문하기 : 주문코드 생성 -> 주문코드에 상품 넣기 -> 카트 비우기 -> 누적금액 insert
	public int getInsertOrderByCart(Orders orders, ArrayList<HashMap<String, Object>> list) {
		int row = 0;
		int orderCode = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			HashMap<String, Integer> map = orderDao.insertOrderByCustomer(conn, orders);
			orderCode = (int)map.get("autoKey");
			if(map == null || map.equals("")) {
				throw new Exception();
			} else {
				this.orderGoodsDao = new OrderGoodsDao();
				int result1 = orderGoodsDao.insertOrderGoodsByCart(conn, list, orderCode);
				if(result1 == 0) {
					throw new Exception();
				} else {
					this.cartDao = new CartDao();
					int result2 = cartDao.deleteCartList(conn, orders.getCustomerId());
					if(result2 == 0) {
						throw new Exception();
					} else {
						this.customerDao = new CustomerDao();
						row = customerDao.updateSaveTotalPrice(conn, orders);
						if(row == 0) {
							throw new Exception();
						}
					}
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				orderCode = 0;
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return orderCode;
	}
	
	// 바로구매하기
	public int getInsertOrder(Orders orders, HashMap<String, Object> goodsOne) {
		int orderCode = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			HashMap<String, Integer> map = orderDao.insertOrderByCustomer(conn, orders);
			orderCode = (int)map.get("autoKey");
			if(map == null || map.equals("")) {
				throw new Exception();
			} else {
				this.orderGoodsDao = new OrderGoodsDao();
				int result = orderGoodsDao.insertOrderGoods(conn, orderCode, goodsOne);
				if(result == 0) {
					throw new Exception();
				} else {
					this.customerDao = new CustomerDao();
					int row = customerDao.updateSaveTotalPrice(conn, orders);
					if(row == 0) {
						throw new Exception();
					}
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				orderCode = 0;
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return orderCode;
	}
	
	// orderOne
	// orderCode에 대한 주문자 정보 출력
	public HashMap<String, Object> getSelectCustomerByOrderList(int orderCode) {
		HashMap<String, Object> customerByOrder = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			customerByOrder = orderDao.selectCustomerByOrder(conn, orderCode);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return customerByOrder;
	}
	
	// orderCode에 대한 구매 굿즈 출력
	public ArrayList<HashMap<String, Object>> getSelectOrderByGoodsList(int orderCode) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			list = orderDao.selectOrderByGoodsList(conn, orderCode);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 리뷰페이지에서 보여줄 상품 구매 내역
	public HashMap<String, Object> getSelectOrderGoodsOne(OrderGoods orderGoods) {
		HashMap<String, Object> orderGoodsOne = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			orderGoodsOne = orderDao.selectOrderGoodsOne(conn, orderGoods);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return orderGoodsOne;
	}
	
	// 관리자용 모든 주문 리스트
	public ArrayList<HashMap<String, Object>> getSelectAllOrderList(String stateSearch, String customerId, String createdate, int beginRow, int rowPerPage) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		
		 try {
			 conn = DBUtil.getConnection();
			 this.orderDao = new OrderDao();
			 list = orderDao.selectAllOrderList(conn, stateSearch, customerId, createdate, beginRow, rowPerPage);
			 conn.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 try {
				 conn.close();
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }
		 }
		 
		 return list;
	}
	
	// 관리자 주문리스트 페이징용 카운트
	public int getOrderListCount(String stateSearch, String customerId, String sort) {
		int cnt = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			cnt = orderDao.orderListCount(conn, stateSearch, customerId, sort);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	// 고객용 주문 리스트
	public ArrayList<HashMap<String, Object>> getSelectOrderByCustomerLIst(String id, String stateSearch, String sort, int beginRow, int rowPerPage) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			list = orderDao.selectOrderByCustomerList(conn, id, stateSearch, sort, beginRow, rowPerPage);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 고객용 주문리스트 페이징용 카운트
	public int getCustomerOrderListCnt(String id, String stateSearch) {
		int cnt = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			cnt = orderDao.customerOrderListCnt(conn, id, stateSearch);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	// 관리자가 주문상태(배송) 변경
	public int getUpdateOrderState(Orders orders) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			row = orderDao.updateOrderState(conn, orders);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	
	// 주문 취소시 사용포인트없으면 주문만 취소하고 누적 주문 금액에서 마이너스
	public int getCancelOrderState(Orders orders) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			int result = orderDao.updateOrderState(conn, orders);
			if(result == 0) {
				throw new Exception();
			} else {
				this.customerDao = new CustomerDao();
				row = customerDao.updateUseTotalPrice(conn, orders);
				if(row == 0) {
					throw new Exception();
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	
	// 주문 취소시 사용포인트 리턴, pointHistory에 저장 흐 누적 주문 금액에서 마이너스
	public int getCancelOrderStatePoint(Orders orders, PointHistory pointHistory, Customer customer) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			int result1 = orderDao.updateOrderState(conn, orders);
			if(result1 == 0) {
				throw new Exception();
			} else {
				this.customerDao = new CustomerDao();
				int result2 = customerDao.updateSavePoint(conn, customer);
				if(result2 == 0) {
					throw new Exception();
				} else {
					this.pointHistoryDao = new PointHistoryDao();
					int result3 = pointHistoryDao.insertPoint(conn, pointHistory);
					if(result3 == 0) {
						throw new Exception();
					} else {
						row = customerDao.updateUseTotalPrice(conn, orders);
						if(row == 0) {
							throw new Exception();
						}
					}
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
}
