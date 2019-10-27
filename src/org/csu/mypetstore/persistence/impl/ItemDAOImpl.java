package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        String sql = "UPDATE inventory SET qty = qty - ? WHERE itemid = ?;";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            String itemId = param.keySet().iterator().next();
            Integer increment = (Integer) param.get(itemId);
            ps.setInt(1, increment.intValue());
            ps.setString(2, itemId);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        String sql ="SELECT qty AS quantity FROM inventory WHERE itemid = ?;";
        int quantity = 0;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, itemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                quantity = rs.getInt(1);
            }

            DBUtil.closeAll(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        String sql ="SELECT item.itemid, listprice, unitcost, supplier AS supplierId, item.productid AS \"product.productId\", name AS \"product.name\", descn AS \"product.description\", category AS \"product.categoryId\", STATUS, attr1 AS attribute1, attr2 AS attribute2, attr3 AS attribute3, attr4 AS attribute4, attr5 AS attribute5 FROM item, product WHERE product.productid = item.productid AND item.productid = ?;";
        List<Item> itemList = new ArrayList<Item>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getString(1));
                item.setListPrice(rs.getBigDecimal(2));
                item.setUnitCost(rs.getBigDecimal(3));
                item.setSupplierId(rs.getInt(4));
                Product product = new Product();
                product.setProductId (rs.getString(5));
                product.setName(rs.getString(6));
                product.setDescription (rs.getString(7));
                product.setCategoryId (rs.getString(8));
                item.setProduct(product);
                item.setStatus(rs.getString(9));
                item.setAttribute1(rs.getString(10));
                item.setAttribute2(rs.getString(11));
                item.setAttribute3(rs.getString(12));
                item.setAttribute4(rs.getString(13));
                item.setAttribute5(rs.getString(14));
                itemList.add(item);
            }

            DBUtil.closeAll(conn, ps, rs);
            } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList ;
    }

    @Override
    public Item getItem(String itemId) {
        String sql = "SELECT item.itemid, listprice, unitcost, supplier AS supplierId, item.productid AS \"product.productId\", name AS \"product.name\", descn AS \"product.description\", category AS \"product.categoryId\", status, attr1 AS attribute1, attr2 AS attribute2, attr3 AS attribute3, attr4 AS attribute4, attr5 AS attribute5, qty AS quantity FROM item, inventory, product WHERE product.productid = item.productid AND item.itemid = inventory.itemid AND item.itemid = ?;";
        Item item = null;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, itemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new Item();
                item.setItemId(rs.getString(1));
                item.setListPrice(rs.getBigDecimal(2));
                item.setUnitCost(rs.getBigDecimal(3));
                item.setSupplierId(rs.getInt(4));
                Product product = new Product();
                product.setProductId (rs.getString(5));
                product.setName(rs.getString(6));
                product.setDescription (rs.getString(7));
                product.setCategoryId (rs.getString(8));
                item.setProduct(product);
                item.setStatus(rs.getString(9));
                item.setAttribute1(rs.getString(10));
                item.setAttribute2(rs.getString(11));
                item.setAttribute3(rs.getString(12));
                item.setAttribute4(rs.getString(13));
                item.setAttribute5(rs.getString(14));
                item.setQuantity(rs.getInt(15));
            }

            DBUtil.closeAll(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
