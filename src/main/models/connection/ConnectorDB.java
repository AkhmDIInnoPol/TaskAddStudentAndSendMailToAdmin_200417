package main.models.connection;



import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;


/**
 * Created by Di on 18.04.2017.
 */
public class ConnectorDB
{


    private static ConnectorDB connectionDB;
    private BoneCP connectionPool;

    private ConnectorDB() throws IOException, SQLException, PropertyVetoException {
        try {
            // load the database driver (make sure this is in your classpath!)
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            // setup the connection pool using BoneCP Configuration
            BoneCPConfig config = new BoneCPConfig();
            // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/students");
            config.setUsername("student");
            config.setPassword("1234");
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            // setup the connection pool
            connectionPool = new BoneCP(config);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    public static ConnectorDB getInstance() throws IOException, SQLException, PropertyVetoException {
        if (connectionDB == null) {
            connectionDB = new ConnectorDB();
            return connectionDB;
        } else {
            return connectionDB;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }



}
