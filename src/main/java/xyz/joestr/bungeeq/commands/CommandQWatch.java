/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.joestr.bungeeq.commands;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.User;
import xyz.joestr.bungeeq.configuration.Configuration;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import xyz.joestr.bungeeq.unlockmanager.UnlockManager;
import xyz.joestr.bungeeq.unlockmanager.UnlockSession;

/**
 *
 * @author Joel
 */
public class CommandQWatch extends Command {

    public CommandQWatch(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if (!(commandSender instanceof ProxiedPlayer)) {

            commandSender.sendMessage(
                Configuration.commandSenderIsNotAProxiedPlayer()
            );
        }

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (strings.length == 0) {

            UnlockSession unlockSession
                = UnlockManager
                    .getInstance()
                    .getUnlockByWatcher(player.getUniqueId());

            if (unlockSession == null) {

                player.sendMessage(
                    Configuration.transformForUnlocker(
                        "Du beobachtest zurzeit keine Freischaltung!"
                    )
                );

                return;
            }

            unlockSession.removeWatcher(player.getUniqueId());

            player.sendMessage(
                Configuration.transformForUnlocker(
                    "Du beobachtest die Freischaltung nicht mehr!"
                )
            );

            return;
        }

        if (strings.length == 1) {

            User user
                = LuckPerms
                    .getApi()
                    .getUser(strings[0]);

            if (user == null) {

                player.sendMessage(
                    Configuration.transformForUnlocker(
                        "Diese Freischaltung kann nicht beobachtet werden!"
                    )
                );

                return;
            }

            UnlockSession unlockSession
                = UnlockManager
                    .getInstance()
                    .getUnlockByTarget(
                        user
                            .getUuid()
                    );

            if (unlockSession == null) {

                player.sendMessage(
                    Configuration.transformForUnlocker(
                        "Diese Freischaltung kann nicht beobachtet werden!"
                    )
                );

                return;
            }

            unlockSession.addWatcher(player.getUniqueId());

            player.sendMessage(
                Configuration.transformForUnlocker(
                    "Du beobachtest nun die Freischaltung!"
                )
            );

            return;
        }

        player.sendMessage(
            Configuration.usage(
                Configuration.Q.Watch.command() + " [Spieler]"
            )
        );
    }
}
