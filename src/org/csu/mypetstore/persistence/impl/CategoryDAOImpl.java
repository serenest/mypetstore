package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.persistence.CategoryDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final String GET_CATEGORY_LIST = "SELECT catid AS categoryId, name, descn AS description FROM category;";
    private static final String GET_CATEGORY = "SELECT catid AS categoryId, name, descn AS description FROM category WHERE catid=?";

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<Category>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_CATEGORY_LIST);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getString("categoryId"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));

                categoryList.add(category);
            }

            DBUtil.closeAll(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_CATEGORY);
            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getString("categoryId"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
            }

            DBUtil.closeAll(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

//    public static void main(String[] args) throws Exception{
//        try {
//            Connection conn = DBUtil.getConnection();
//            PreparedStatement ps = conn.prepareStatement(GET_CATEGORY_LIST);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
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
