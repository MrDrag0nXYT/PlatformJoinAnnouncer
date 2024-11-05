package zxc.mrdrag0nxyt.bedrockJoinAnnouncer

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin
import org.geysermc.floodgate.api.FloodgateApi
import zxc.mrdrag0nxyt.bedrockJoinAnnouncer.handler.JoinHandler
import zxc.mrdrag0nxyt.bedrockJoinAnnouncer.util.Config

class BedrockJoinAnnouncer : JavaPlugin() {

    companion object {
        val floodgateApi: FloodgateApi = FloodgateApi.getInstance()
        val miniMessage = MiniMessage.miniMessage()
    }

    private val config: Config by lazy { Config(this) }

    override fun onEnable() {
        server.pluginManager.registerEvents(JoinHandler(this, config), this)
    }

    fun reload() {
        config.reload()
    }
}
