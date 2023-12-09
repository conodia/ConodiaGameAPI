package fr.pandaguerrier.conodiagameapi.events;

import fr.pandaguerrier.conodiagameapi.objects.PlayerAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener  implements Listener {
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    PlayerAPI playerAPI = new PlayerAPI(player);

    if (!playerAPI.isExist()) {
      playerAPI.create();
    }
  }
}
