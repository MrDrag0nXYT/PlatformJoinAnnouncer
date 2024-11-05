package zxc.mrdrag0nxyt.platformJoinAnnouncer.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import zxc.mrdrag0nxyt.platformJoinAnnouncer.PlatformJoinAnnouncer
import zxc.mrdrag0nxyt.platformJoinAnnouncer.sendColoredMessage
import zxc.mrdrag0nxyt.platformJoinAnnouncer.util.Config

class MainCommand(private val plugin: PlatformJoinAnnouncer, private val config: Config) : CommandExecutor, TabCompleter {

    override fun onCommand(
        commandSender: CommandSender,
        command: Command,
        string: String,
        strings: Array<out String>?
    ): Boolean {
        val yamlConfiguration = config.getConfig()

        if (strings == null || strings.isEmpty()) {
            for (str in yamlConfiguration.getStringList("messages.usage")) {
                commandSender.sendColoredMessage(str)
            }
            return true
        }

        if (!commandSender.hasPermission("pja.reload")) {
            for (str in yamlConfiguration.getStringList("messages.no-permission")) {
                commandSender.sendColoredMessage(str)
            }
            return true
        }

        if (strings[0].equals("reload", ignoreCase = true)) {
            for (str in yamlConfiguration.getStringList("messages.reloaded")) {
                commandSender.sendColoredMessage(str)
            }
            plugin.reload()
        }

        return true
    }

    override fun onTabComplete(
        commandSender: CommandSender,
        command: Command,
        string: String,
        strings: Array<out String>?
    ): MutableList<String>? {

        if (strings == null || strings.isEmpty()) {
            return null
        }

        if (strings.size == 1) {
            return mutableListOf("reload")
        }

        return null
    }
}