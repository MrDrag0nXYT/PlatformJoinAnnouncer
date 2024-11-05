package zxc.mrdrag0nxyt.platformJoinAnnouncer.handler

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import zxc.mrdrag0nxyt.platformJoinAnnouncer.PlatformJoinAnnouncer
import zxc.mrdrag0nxyt.platformJoinAnnouncer.util.Config

class JoinHandler(private val plugin: PlatformJoinAnnouncer, private val config: Config) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val platform = when (PlatformJoinAnnouncer.floodgateApi.isFloodgatePlayer(player.uniqueId)) {
            true -> "bedrock"
            false -> "java"
        }
        val message: List<String> = config.getConfig().getStringList("join-messages.${platform}.strings")

        if (!config.getConfig().getBoolean("join-messages.${platform}.enabled", false)) {
            return
        }

        Bukkit.getScheduler().runTaskLater(plugin, Runnable {
            for (string in message) {
                player.sendMessage(
                    PlatformJoinAnnouncer.miniMessage.deserialize(string)
                )
            }
        }, config.getConfig().getLong("join-messages.send-after", 3) * 20)
    }

}