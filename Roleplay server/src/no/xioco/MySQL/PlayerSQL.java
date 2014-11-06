package no.xioco.MySQL;

import no.xioco.Roleplay.Main.Main;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

/**
 * Created by jimbo8 on 26.10.2014.
 */
public class PlayerSQL {
    String user = "root";
    String pass = "";
    String url = "jdbc:mysql://localhost:3306/xioco_rp";

    Main plugin;

    public PlayerSQL(Main instance){
        plugin = instance;
    }

    public void addUser(Player player){
        UUID uuid = player.getUniqueId();
        String name = player.getName();

        int goldOnJoin = plugin.getConfig().getInt("gold-on-first-join");

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO userdata (uuid, username, firstJoin, gold) VALUES ('" + uuid + "', '" + name + "', CURRENT_TIMESTAMP, " + goldOnJoin +")");
            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO userinfo (uuid, username, bans, kicks, warns, gold) VALUES ('" + uuid + "', '" + name + "', 0, 0, 0, " + goldOnJoin +")");
            stmt.executeUpdate();
            stmt2.executeUpdate();
            stmt.close();
            stmt2.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean inDatabase(Player player){
        UUID uuid = player.getUniqueId();
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT uuid FROM userdata WHERE uuid='" + uuid + "'");
            if(rs.next()){
                return true;
            }
            rs.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void banPlayer(Player player, boolean tempban, String time){
        UUID uuid = player.getUniqueId();
        String name = player.getName();

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String sql;
            if(tempban) {
                if(time.endsWith("m")) {
                    sql = "UPDATE userinfo SET tempbanTime=TIMESTAMP";
                }
            }
            PreparedStatement stmt = conn.prepareStatement("");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
