package mongodbpractice.mongodbpractice;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MongoDBpractice extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        new Thread(() -> {
            MongoDBManager mongo = new MongoDBManager(this, "collName");
            Document doc = new Document();
            doc.append("mcid", e.getPlayer().getName());
            doc.append("uuid", e.getPlayer().getUniqueId().toString());
            mongo.queryInsertOne(doc);
            mongo.close();
        }).start();
    }
}
