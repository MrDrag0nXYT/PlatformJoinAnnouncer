package zxc.mrdrag0nxyt.bedrockJoinAnnouncer.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import zxc.mrdrag0nxyt.bedrockJoinAnnouncer.BedrockJoinAnnouncer

class MainCommand(private val plugin: BedrockJoinAnnouncer) : CommandExecutor, TabCompleter {

    override fun onCommand(
        commandSender: CommandSender,
        command: Command,
        string: String,
        strings: Array<out String>?
    ): Boolean {

        if (strings == null || strings.isEmpty()) {
            return true
        }

        if (strings[0].equals("reload", ignoreCase = true)) {
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