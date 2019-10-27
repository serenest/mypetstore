package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOImpl implements AccountDAO {
    @Override
    public Account getAccountByUsername(String username) {
        String sql = "SELECT\n" +
                "          SIGNON.USERNAME,\n" +//1
                "          ACCOUNT.EMAIL,\n" +//2
                "          ACCOUNT.FIRSTNAME,\n" +//3
                "          ACCOUNT.LASTNAME,\n" +//4
                "          ACCOUNT.STATUS,\n" +//5
                "          ACCOUNT.ADDR1 AS address1,\n" +//6
                "          ACCOUNT.ADDR2 AS address2,\n" +//7
                "          ACCOUNT.CITY,\n" +//8
                "          ACCOUNT.STATE,\n" +//9
                "          ACCOUNT.ZIP,\n" +//10
                "          ACCOUNT.COUNTRY,\n" +//11
                "          ACCOUNT.PHONE,\n" +//12
                "          PROFILE.LANGPREF AS languagePreference,\n" +//13
                "          PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +//14
                "          PROFILE.MYLISTOPT AS listOption,\n" +//15
                "          PROFILE.BANNEROPT AS bannerOption,\n" +//16
                "          BANNERDATA.BANNERNAME\n" +//17
                "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
                "    WHERE ACCOUNT.USERID = ?\n" +
                "      AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
                "      AND PROFILE.USERID = ACCOUNT.USERID\n" +
                "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

        Account account = null;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setUsername(rs.getString(1));
                account.setEmail(rs.getString(2));
                account.setFirstName(rs.getString(3));
                account.setLastName(rs.getString(4));
                account.setStatus(rs.getString(5));
                account.setAddress1(rs.getString(6));
                account.setAddress2(rs.getString(7));
                account.setCity(rs.getString(8));
                account.setState(rs.getString(9));
                account.setZip(rs.getString(10));
                account.setCountry(rs.getString(11));
                account.setPhone(rs.getString(12));
                account.setLanguagePreference(rs.getString(13));
                account.setFavouriteCategoryId(rs.getString(14));
                account.setListOption(rs.getBoolean(15));
                account.setBannerOption(rs.getBoolean(16));
                account.setBannerName(rs.getString(17));
            }

            DBUtil.closeAll(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account theAccount) {
        String sql = "    SELECT\n" +
                "      SIGNON.USERNAME,\n" +
                "      ACCOUNT.EMAIL,\n" +
                "      ACCOUNT.FIRSTNAME,\n" +
                "      ACCOUNT.LASTNAME,\n" +
                "      ACCOUNT.STATUS,\n" +
                "      ACCOUNT.ADDR1 AS address1,\n" +
                "      ACCOUNT.ADDR2 AS address2,\n" +
                "      ACCOUNT.CITY,\n" +
                "      ACCOUNT.STATE,\n" +
                "      ACCOUNT.ZIP,\n" +
                "      ACCOUNT.COUNTRY,\n" +
                "      ACCOUNT.PHONE,\n" +
                "      PROFILE.LANGPREF AS languagePreference,\n" +
                "      PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +
                "      PROFILE.MYLISTOPT AS listOption,\n" +
                "      PROFILE.BANNEROPT AS bannerOption,\n" +
                "      BANNERDATA.BANNERNAME\n" +
                "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
                "    WHERE ACCOUNT.USERID = ?\n" +
                "      AND SIGNON.PASSWORD = ?\n" +
                "      AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
                "      AND PROFILE.USERID = ACCOUNT.USERID\n" +
                "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY"
                ;

        Account account = null;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, theAccount.getUsername());
            ps.setString(2, theAccount.getPassword());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setUsername(rs.getString(1));
                account.setEmail(rs.getString(2));
                account.setFirstName(rs.getString(3));
                account.setLastName(rs.getString(4));
                account.setStatus(rs.getString(5));
                account.setAddress1(rs.getString(6));
                account.setAddress2(rs.getString(7));
                account.setCity(rs.getString(8));
                account.setState(rs.getString(9));
                account.setZip(rs.getString(10));
                account.setCountry(rs.getString(11));
                account.setPhone(rs.getString(12));
                account.setLanguagePreference(rs.getString(13));
                account.setFavouriteCategoryId(rs.getString(14));
                account.setListOption(rs.getBoolean(15));
                account.setBannerOption(rs.getBoolean(16));
                account.setBannerName(rs.getString(17));
            }

            DBUtil.closeAll(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public void insertAccount(Account account) {
        String sql = "INSERT INTO ACCOUNT\n" +
                "      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)\n" +
                "    VALUES\n" +
                "      (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?)";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, account.getEmail());
            pStatement.setString(2,account.getFirstName());
            pStatement.setString(3, account.getLastName());
            pStatement.setString(4, account.getStatus());
            pStatement.setString(5,account.getAddress1());
            pStatement.setString(6, account.getAddress2());
            pStatement.setString(7,account.getCity());
            pStatement.setString(8, account.getState());
            pStatement.setString(9, account.getZip());
            pStatement.setString(10,account.getCountry());
            pStatement.setString(11, account.getPhone());
            pStatement.setString(12,account.getUsername());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        String sql = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID)\n" +
                "    VALUES (?, ?, ?)";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, account.getLanguagePreference());
            pStatement.setString(2,account.getFavouriteCategoryId());
            pStatement.setString(3, account.getUsername());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        String sql = "INSERT INTO SIGNON (PASSWORD,USERNAME)\n" +
                "    VALUES (?, ?)";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, account.getPassword());
            pStatement.setString(2,account.getUsername());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(updateAccountString);
            pStatement.setString(1, account.getEmail());
            pStatement.setString(2,account.getFirstName());
            pStatement.setString(3, account.getLastName());
            pStatement.setString(4, account.getStatus());
            pStatement.setString(5,account.getAddress1());
            pStatement.setString(6, account.getAddress2());
            pStatement.setString(7,account.getCity());
            pStatement.setString(8, account.getState());
            pStatement.setString(9, account.getZip());
            pStatement.setString(10,account.getCountry());
            pStatement.setString(11, account.getPhone());
            pStatement.setString(12,account.getUsername());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(updateProfileString);
            pStatement.setString(1, account.getLanguagePreference());
            pStatement.setString(2,account.getFavouriteCategoryId());
            pStatement.setString(3, account.getUsername());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(updateSignonString);
            pStatement.setString(1, account.getPassword());
            pStatement.setString(2,account.getUsername());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void doAccount(Account account) {
//        try {
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(insertAccountString);
//            pStatement.setString(1, account.getEmail());
//            pStatement.setString(2,account.getFirstName());
//            pStatement.setString(3, account.getLastName());
//            pStatement.setString(4, account.getStatus());
//            pStatement.setString(5,account.getAddress1());
//            pStatement.setString(6, account.getAddress2());
//            pStatement.setString(7,account.getCity());
//            pStatement.setString(8, account.getState());
//            pStatement.setString(9, account.getZip());
//            pStatement.setString(10,account.getCountry());
//            pStatement.setString(11, account.getPhone());
//            pStatement.setString(12,account.getUsername());
//            pStatement.executeUpdate();
//
//            DBUtil.closePreparedStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void doProfile(Account account) {
//        try {
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(insertProfileString);
//            pStatement.setString(1, account.getLanguagePreference());
//            pStatement.setString(2,account.getFavouriteCategoryId());
//            pStatement.setString(3, account.getUsername());
//            pStatement.executeUpdate();
//
//            DBUtil.closePreparedStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void doSignon(Account account) {
//        try {
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(insertSignonString);
//            pStatement.setString(1, account.getPassword());
//            pStatement.setString(2,account.getUsername());
//            pStatement.executeUpdate();
//
//            DBUtil.closePreparedStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static final String insertAccountString = "INSERT INTO ACCOUNT\n" +
//            "      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)\n" +
//            "    VALUES\n" +
//            "      (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";
//
//    private static final String insertProfileString = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID)\n" +
//            "    VALUES (?, ?, ?)";
//
//    private static final String insertSignonString = "INSERT INTO SIGNON (PASSWORD,USERNAME)\n" +
//            "    VALUES (?, ?)";

    private static final String updateAccountString = "UPDATE ACCOUNT SET\n" +
            "      EMAIL = ?,\n" +
            "      FIRSTNAME = ?,\n" +
            "      LASTNAME = ?,\n" +
            "      STATUS = ?,\n" +
            "      ADDR1 = ?,\n" +
            "      ADDR2 = ?,\n" +
            "      CITY = ?,\n" +
            "      STATE = ?,\n" +
            "      ZIP = ?,\n" +
            "      COUNTRY = ?,\n" +
            "      PHONE = ?\n" +
            "    WHERE USERID = ?";

    private static final String updateProfileString = "UPDATE PROFILE SET\n" +
            "      LANGPREF = ?,\n" +
            "      FAVCATEGORY = ?\n" +
            "    WHERE USERID = ?";

    private static final String updateSignonString = "UPDATE SIGNON SET PASSWORD = ?\n" +
            "    WHERE USERNAME = ?";
}
