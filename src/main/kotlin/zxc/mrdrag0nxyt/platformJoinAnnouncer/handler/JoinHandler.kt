package zxc.mrdrag0nxyt.bedrockJoinAnnouncer.handler

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import zxc.mrdrag0nxyt.bedrockJoinAnnouncer.BedrockJoinAnnouncer
import zxc.mrdrag0nxyt.bedrockJoinAnnouncer.util.Config

class JoinHandler(private val plugin: BedrockJoinAnnouncer, private val config: Config) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {

        val player = event.player

        if (BedrockJoinAnnouncer.floodgateApi.isFloodgatePlayer(player.uniqueId)) {
            Bukkit.getScheduler().runTaskLater(plugin, Runnable {
                for (string in config.getConfig().getStringList("message")) {
                    player.sendMessage(
                        BedrockJoinAnnouncer.miniMessage.deserialize(string)
                    )
                }
            }, config.getConfig().getLong("send-after", 3) * 20)
        }
    }

}