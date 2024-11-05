package zxc.mrdrag0nxyt.platformJoinAnnouncer.util

import org.bukkit.configuration.file.YamlConfiguration
import zxc.mrdrag0nxyt.platformJoinAnnouncer.PlatformJoinAnnouncer
import org.bukkit.configuration.file.FileConfiguration
import java.io.File

class Config(private val plugin: PlatformJoinAnnouncer) {

    private lateinit var yamlConfiguration: YamlConfiguration
    private val file: File = File(plugin.dataFolder, "config.yml")

    init {
        if (!file.exists()) {
            plugin.saveResource("config.yml", false)
        }
        init()
    }

    private fun init() {
        yamlConfiguration = YamlConfiguration.loadConfiguration(file)
    }

    fun reload() {
        if (!file.exists()) {
            plugin.saveResource("config.yml", false)
        }
        init()
    }

    fun save() {
        try {
            yamlConfiguration.save(file)
        } catch (e: Exception) {
            plugin.logger.severe("Could not save config.yml: ${e.message}")
        }
    }

    fun getConfig(): FileConfiguration {
        return yamlConfiguration
    }
}
