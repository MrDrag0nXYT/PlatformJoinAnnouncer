package zxc.mrdrag0nxyt.platformJoinAnnouncer

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import org.geysermc.floodgate.api.FloodgateApi
import zxc.mrdrag0nxyt.platformJoinAnnouncer.PlatformJoinAnnouncer.Companion.miniMessage
import zxc.mrdrag0nxyt.platformJoinAnnouncer.command.MainCommand
import zxc.mrdrag0nxyt.platformJoinAnnouncer.handler.JoinHandler
import zxc.mrdrag0nxyt.platformJoinAnnouncer.util.Config

class PlatformJoinAnnouncer : JavaPlugin() {

    companion object {
        lateinit var floodgateApi: FloodgateApi
        val miniMessage = MiniMessage.miniMessage()
        val consoleCommandSender = Bukkit.getConsoleSender()
        lateinit var metrics: Metrics
    }

    private val config: Config by lazy { Config(this) }

    override fun onEnable() {
        val floodgate = server.pluginManager.getPlugin("floodgate")
        if (floodgate == null || !floodgate.isEnabled) {
            consoleCommandSender.sendColoredMessage("<#dc143c>Floodgate is required to use ${description.name}! Disabling...</#dc143c>")
            server.pluginManager.disablePlugin(this)
        }

        floodgateApi = FloodgateApi.getInstance()

        if (config.getConfig().getBoolean("enable-metrics", true)) {
            metrics = Metrics(this, 23816)
        }

        server.pluginManager.registerEvents(JoinHandler(this, config), this)
        getCommand("platformjoinannouncer")?.setExecutor(MainCommand(this, config))
        sendLogo()
    }

    fun reload() {
        config.reload()
    }

    private fun sendLogo() {
        consoleCommandSender.sendColoredMessage(" ")
        consoleCommandSender.sendColoredMessage("<#a880ff>█▀█ ░░█ ▄▀█ █▄░█ █▄░█ █▀█ █░█ █▄░█ █▀▀ █▀▀ █▀█</#a880ff>    <#696969>|</#696969>    <#fcfcfc>Version: <#a880ff>${description.version}</#a880ff>")
        consoleCommandSender.sendColoredMessage("<#a880ff>█▀▀ █▄█ █▀█ █░▀█ █░▀█ █▄█ █▄█ █░▀█ █▄▄ ██▄ █▀▄</#a880ff>    <#696969>|</#696969>    <#fcfcfc>Author: <#a880ff>MrDrag0nXYT (https://drakoshaslv.ru)</#a880ff>")
        consoleCommandSender.sendColoredMessage(" ")
    }
}

fun CommandSender.sendColoredMessage(text: String) {
    sendMessage(miniMessage.deserialize(text))
}
