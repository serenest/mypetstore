package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.LineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {
    private static final String getLineItemsByOrderIdString = "SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String insertLineItemString = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItemList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getLineItemsByOrderIdString);
            pStatement.setInt(1, orderId);

            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                LineItem lineItem = new LineItem();
                lineItem.setLineNumber(resultSet.getInt(1));
                lineItem.setItemId(resultSet.getString(2));
                lineItem.setQuantity(resultSet.getInt(3));
                lineItem.setUnitPrice(resultSet.getBigDecimal(4));

                lineItemList.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineItemList;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(insertLineItemString);
            pStatement.setInt(1, lineItem.getOrderId());
            pStatement.setInt(2,lineItem.getLineNumber());
            pStatement.setString(3, lineItem.getItemId());
            pStatement.setInt(4, lineItem.getQuantity());
            pStatement.setBigDecimal(5,lineItem.getUnitPrice());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
