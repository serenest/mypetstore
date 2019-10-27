package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.OrderDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static final String getOrdersByUsername = "SELECT\n" +
            "      BILLADDR1 AS billAddress1,\n" +
            "      BILLADDR2 AS billAddress2,\n" +
            "      BILLCITY,\n" +
            "      BILLCOUNTRY,\n" +
            "      BILLSTATE,\n" +
            "      BILLTOFIRSTNAME,\n" +
            "      BILLTOLASTNAME,\n" +
            "      BILLZIP,\n" +
            "      SHIPADDR1 AS shipAddress1,\n" +
            "      SHIPADDR2 AS shipAddress2,\n" +
            "      SHIPCITY,\n" +
            "      SHIPCOUNTRY,\n" +
            "      SHIPSTATE,\n" +
            "      SHIPTOFIRSTNAME,\n" +
            "      SHIPTOLASTNAME,\n" +
            "      SHIPZIP,\n" +
            "      CARDTYPE,\n" +
            "      COURIER,\n" +
            "      CREDITCARD,\n" +
            "      EXPRDATE AS expiryDate,\n" +
            "      LOCALE,\n" +
            "      ORDERDATE,\n" +
            "      ORDERS.ORDERID,\n" +
            "      TOTALPRICE,\n" +
            "      USERID AS username,\n" +
            "      STATUS\n" +
            "    FROM ORDERS, ORDERSTATUS\n" +
            "    WHERE ORDERS.USERID = #{value}\n" +
            "      AND ORDERS.ORDERID = ORDERSTATUS.ORDERID\n" +
            "    ORDER BY ORDERDATE";

    private static final String getOrderString = "select BILLADDR1 AS billAddress1,BILLADDR2 AS billAddress2,BILLCITY,BILLCOUNTRY,BILLSTATE,BILLTOFIRSTNAME,BILLTOLASTNAME,BILLZIP,SHIPADDR1 AS shipAddress1,SHIPADDR2 AS shipAddress2,SHIPCITY,SHIPCOUNTRY,SHIPSTATE,SHIPTOFIRSTNAME,SHIPTOLASTNAME,"+
    "SHIPZIP,CARDTYPE,COURIER,CREDITCARD,EXPRDATE AS expiryDate,LOCALE,ORDERDATE,ORDERS.ORDERID,TOTALPRICE,USERID AS username,STATUS FROM ORDERS, ORDERSTATUS WHERE ORDERS.ORDERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";

    private static final String insertOrderString = "INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE,\n" +
            "      SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY,\n" +
            "      COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME,\n" +
            "      CREDITCARD, EXPRDATE, CARDTYPE, LOCALE)\n" +
            "    VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String insertOrderStatusString = "INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS)\n" +
            "    VALUES (?, ?, ?, ?)";

    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orderList = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getOrderString);
            pStatement.setString(1, username);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                getSingleOrder(resultSet, order);
                orderList.add(order);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getOrderString);
            pStatement.setInt(1, orderId);
            ResultSet resultSet = pStatement.executeQuery();

            if(resultSet.next()) {
                order = new Order();
                getSingleOrder(resultSet, order);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return order;
    }

    private void getSingleOrder(ResultSet resultSet, Order order) throws SQLException {
        order.setBillAddress1(resultSet.getString(1));
        order.setBillAddress2(resultSet.getString(2));
        order.setBillCity(resultSet.getString(3));
        order.setBillCountry(resultSet.getString(4));
        order.setBillState(resultSet.getString(5));
        order.setBillToFirstName(resultSet.getString(6));
        order.setBillToLastName(resultSet.getString(7));
        order.setBillZip(resultSet.getString(8));
        order.setShipAddress1(resultSet.getString(9));
        order.setShipAddress2(resultSet.getString(10));
        order.setShipCity(resultSet.getString(11));
        order.setShipCountry(resultSet.getString(12));
        order.setShipState(resultSet.getString(13));
        order.setShipToFirstName(resultSet.getString(14));
        order.setShipToLastName(resultSet.getString(15));
        order.setShipZip(resultSet.getString(16));
        order.setCardType(resultSet.getString(17));
        order.setCourier(resultSet.getString(18));
        order.setCreditCard(resultSet.getString(19));
        order.setExpiryDate(resultSet.getString(20));
        order.setLocale(resultSet.getString(21));
        order.setOrderDate(resultSet.getDate(22));
        order.setOrderId(resultSet.getInt(23));
        order.setTotalPrice(resultSet.getBigDecimal(24));
        order.setUsername(resultSet.getString(25));
        order.setStatus(resultSet.getString(26));
    }

    @Override
    public void insertOrder(Order order) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(insertOrderString);
            pStatement.setInt(1, order.getOrderId());
            pStatement.setString(2,order.getUsername());
            pStatement.setDate(3, (Date) order.getOrderDate());
            pStatement.setString(4, order.getShipAddress1());
            pStatement.setString(5,order.getShipAddress2());
            pStatement.setString(6, order.getShipCity());
            pStatement.setString(7,order.getShipState());
            pStatement.setString(8, order.getShipZip());
            pStatement.setString(9, order.getShipCountry());
            pStatement.setString(10,order.getBillAddress1());
            pStatement.setString(11, order.getBillAddress2());
            pStatement.setString(12, order.getBillCity());
            pStatement.setString(13, order.getBillState());
            pStatement.setString(14,order.getBillZip());
            pStatement.setString(15, order.getBillCountry());
            pStatement.setString(16, order.getCourier());
            pStatement.setBigDecimal(17,order.getTotalPrice());
            pStatement.setString(18, order.getBillToFirstName());
            pStatement.setString(19,order.getBillToLastName());
            pStatement.setString(20, order.getShipToFirstName());
            pStatement.setString(21, order.getShipToLastName());
            pStatement.setString(22,order.getCreditCard());
            pStatement.setString(23, order.getExpiryDate());
            pStatement.setString(24, order.getCardType());
            pStatement.setString(24, order.getLocale());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrderStatus(Order order) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(insertOrderStatusString);
            pStatement.setInt(1, order.getOrderId());
            pStatement.setInt(2,order.getOrderId());
            pStatement.setDate(3, (Date) order.getOrderDate());
            pStatement.setString(4, order.getStatus());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
