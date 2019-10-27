package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ProductDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String GET_PRODUCT = "SELECT productid AS productId, category AS categoryId, name, descn AS description FROM product WHERE productid=?;";
    private static final String GET_PRODUCT_LIST_BY_CATEGORY = "SELECT productid AS productId, category AS categoryId, name, descn AS description FROM product WHERE category=?;";
    private static final String SEARCH_PRODUCT_LIST = "SELECT productid AS productId, category AS categoryId, name, descn AS description FROM product WHERE lower(name) LIKE \"%\"?\"%\"";

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_PRODUCT);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getString("productId"));
                product.setCategoryId(rs.getString("categoryId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
            }

            DBUtil.closeAll(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> productList = new ArrayList<Product>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_PRODUCT_LIST_BY_CATEGORY);
            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("productId"));
                product.setCategoryId(rs.getString("categoryId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));

                productList.add(product);
            }

            DBUtil.closeAll(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(SEARCH_PRODUCT_LIST);
            ps.setString(1, keywords);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("productId"));
                product.setCategoryId(rs.getString("categoryId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));

                productList.add(product);
            }

            DBUtil.closeAll(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

//    public static void main(String[] args) throws Exception{
//        try {
//            Connection conn = DBUtil.getConnection();
//            PreparedStatement ps = conn.prepareStatement(SEARCH_PRODUCT_LIST);
//            ps.setString(1, "s");
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                System.out.println(rs.getString("productId"));
//                System.out.println(rs.getString("categoryId"));
//                System.out.println(rs.getString("name"));
//                System.out.println(rs.getString("description"));
//
//            }
//
//            DBUtil.closeAll(conn, ps, rs);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
