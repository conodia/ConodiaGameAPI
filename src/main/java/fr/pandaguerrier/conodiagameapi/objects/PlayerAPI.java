package fr.pandaguerrier.conodiagameapi.objects;

import fr.pandaguerrier.conodiagameapi.ConodiaGameAPI;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.util.UUID;

public class PlayerAPI {
  private final UUID uuid;
  private final String name;
  private final String ip;

  public PlayerAPI(Player player) {
    this.uuid = player.getUniqueId();
    this.name = player.getName();
    this.ip = player.getAddress().getAddress().getHostAddress();
  }

  public boolean isExist() {
    JSONObject payload = ConodiaGameAPI.getInstance().getApiManager().get("/player/" + this.uuid.toString(), new JSONObject());
    return (boolean) payload.get("exists");
  }

  public void create() {
    JSONObject payload = ConodiaGameAPI.getInstance().getApiManager().post("/player", this.toJson());
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("minecraftUuid", this.uuid.toString());
    json.put("minecraftPlayername", this.name);
    json.put("ip", this.ip);
    return json;
  }
  public UUID getUuid() {
    return this.uuid;
  }
  public String getName() {
    return this.name;
  }
  public String getIp() {
    return this.ip;
  }
}
