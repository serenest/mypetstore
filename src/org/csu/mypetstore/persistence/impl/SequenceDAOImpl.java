package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.SequenceDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDAOImpl implements SequenceDAO {

    private static final String getSequenceString = "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";

    private static final String updateSequenceString = "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    @Override

    public Sequence getSequence(Sequence sequence) {
        Sequence newSequence = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getSequenceString);
            pStatement.setString(1, sequence.getName());
            ResultSet resultSet = pStatement.executeQuery();

            if (resultSet.next()) {
                newSequence = new Sequence();
                newSequence.setName(resultSet.getString(1));
                newSequence.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newSequence;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(updateSequenceString);
            pStatement.setInt(1, sequence.getNextId());
            pStatement.setString(2, sequence.getName());
            pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
