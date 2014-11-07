package no.xioco.Listeners;

import com.comphenix.protocol.Packets;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.reflect.StructureModifier;
import no.xioco.MySQL.PlayerSQL;
import no.xioco.Roleplay.Main.Main;
import no.xioco.Roleplay.commands.FreezeCommand;
import no.xioco.Roleplay.commands.HideCommand;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Thomas on 26.10.2014.
 */
public class PlayerListeners implements Listener {


    Main plugin;

    ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();

    public PlayerListeners(Main instance){
        plugin = instance;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(FreezeCommand.frozenPlayers.containsValue(player.getUniqueId())){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(FreezeCommand.frozenPlayers.containsValue(player.getUniqueId())){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            Block clickedBlock = event.getClickedBlock();
            if(clickedBlock.getType() == Material.CHEST) {
                if(HideCommand.hiddenPlayers.contains(player.getUniqueId())) {
                    int x = clickedBlock.getX();
                    int y = clickedBlock.getY();
                    int z = clickedBlock.getZ();
                    BlockState state = clickedBlock.getState();
                    Chest chest = (Chest) state;

                    Inventory chestInv = chest.getInventory();
                    player.openInventory(chestInv);
                    closeChest();
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerSQL pSQL = new PlayerSQL(plugin);
        if(!pSQL.inDatabase(player)){
            pSQL.addUser(player);
        }
    }

    @EventHandler
    public void onMount(PlayerInteractEntityEvent event) {
        Entity e = event.getRightClicked();
        Player player = event.getPlayer();
        if (e instanceof Horse) {
            Horse h = (Horse) e;
            if (h.isTamed()) {
                ItemStack is = new ItemStack(Material.SADDLE);
                if(h.getInventory().contains(is)) {
                    if (h.getOwner().getUniqueId() != player.getUniqueId()) {
                        String owner = h.getOwner().getName();
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "Du eier ikke denne hesten! Den eies av " + ChatColor.WHITE + owner);
                    }
                }else{
                    if(h.getOwner() != null){
                        h.setOwner(player);
                        h.setTamed(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onUnleash(PlayerUnleashEntityEvent event){
        Entity e = event.getEntity();
        Player player = event.getPlayer();
        if(event.getReason() == EntityUnleashEvent.UnleashReason.PLAYER_UNLEASH){
            if(e instanceof Horse){
                Horse h = (Horse) e;
                if(h.isTamed()){
                    if(h.getOwner().getUniqueId() != player.getUniqueId()){
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "Du eier ikke denne hesten. Den eies av " + ChatColor.RESET + h.getOwner().getName());
                    }
                }
            }else if(e instanceof Wolf){
                Wolf w = (Wolf) e;
                if(w.getOwner().getUniqueId() != player.getUniqueId()){
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "Du eier ikke denne ulven. Den eies av " + ChatColor.RESET + w.getOwner().getName());
                }
            }
        }
    }

    @EventHandler
    public void onEntityHurt(EntityDamageByEntityEvent event){
        Entity hurt = event.getEntity();
        Entity damager = event.getDamager();
        if(hurt instanceof Horse){
            Horse h = (Horse) hurt;
            if(damager instanceof Player){
                ItemStack is = new ItemStack(Material.SADDLE);
                if(h.getInventory().contains(is) && h.isTamed()) {
                    if (damager.getUniqueId() != h.getOwner().getUniqueId()) {
                        event.setCancelled(true);
                        ((Player) damager).sendMessage(ChatColor.RED + "Du kan ikke skade denne hesten! Den eies av " + ChatColor.RESET + h.getOwner().getName());
                    }
                }
            }
        }else if(hurt instanceof Wolf){
            Wolf w = (Wolf) hurt;
            if(damager instanceof Player){
                if(w.isTamed()) {
                    if (damager.getUniqueId() != w.getOwner().getUniqueId()) {
                        event.setCancelled(true);
                        ((Player) damager).sendMessage(ChatColor.RED + "Du kan ikke skade denne ulven! Den eies av " + ChatColor.RESET + w.getOwner().getName());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onVehicleLeave(VehicleExitEvent event){
        event.getVehicle().remove();
        if(event.getExited() instanceof Player) {
            Player player = (Player) event.getExited();
            player.getInventory().addItem(event.getVehicle() instanceof Boat ? new ItemStack(Material.BOAT) : new ItemStack(Material.MINECART));
        }
    }

    @EventHandler
    public void onVehicleDestroy(VehicleDestroyEvent event){
        if(event.getVehicle().getPassenger() != null) {
            if(event.getVehicle().getPassenger() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onVehicleDamage(VehicleDamageEvent event){
        if(event.getVehicle().getPassenger() != null) {
            if(event.getVehicle().getPassenger() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(player.isInsideVehicle()){
            Entity e = player.getVehicle();
            player.getInventory().addItem(new ItemStack(e instanceof Minecart ? Material.MINECART : Material.BOAT));
            e.remove();
        }
        if(player.hasPermission("xioco.frys")){
            if(FreezeCommand.frozenPlayers.containsKey(player.getUniqueId())){
                ArrayList<UUID> list = new ArrayList<UUID>();
                list.add(FreezeCommand.frozenPlayers.get(player.getUniqueId()));
                for(UUID uuid : list) {
                    if (Bukkit.getPlayer(uuid) != null) {
                        Bukkit.getPlayer(uuid).sendMessage(ChatColor.RED + "Du er ikke lenger fryst.");
                    }
                }
                FreezeCommand.frozenPlayers.remove(player.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(FreezeCommand.frozenPlayers.containsValue(player.getUniqueId())){
            Location from = event.getFrom();
            Location to = event.getTo();

            int fromX = from.getBlockX();
            int fromY = from.getBlockY();
            int fromZ = from.getBlockZ();

            int toX = to.getBlockX();
            int toY = to.getBlockY();
            int toZ = to.getBlockZ();
            Location loc = player.getLocation();
            float pitch = loc.getPitch();
            float yaw = loc.getYaw();
            World w = loc.getWorld();
            Location newLoc = new Location(w, fromX + 0.5, fromY, fromZ + 0.5, yaw, pitch);
            if(fromX != toX || fromZ != toZ){
                player.teleport(newLoc);
            }
        }
    }

    public void closeChest(){
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        manager.addPacketListener(new PacketAdapter(plugin, ConnectionSide.SERVER_SIDE, Packets.Server.PLAY_NOTE_BLOCK, Packets.Server.NAMED_SOUND_EFFECT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                try{
                    switch(event.getPacketID()){
                        case Packets.Server.PLAY_NOTE_BLOCK:
                            World world = event.getPlayer().getWorld();

                            StructureModifier<Integer> ints = event.getPacket().getSpecificModifier(int.class);
                            int blockX = ints.read(0);
                            int blockY = ints.read(1);
                            int blockZ = ints.read(2);

                            if(world.getBlockTypeIdAt(blockX, blockY, blockZ) == 54){
                                if(HideCommand.hiddenPlayers.contains(event.getPlayer().getUniqueId())) {
                                    event.setCancelled(true);
                                }
                            }

                            break;

                        case Packets.Server.NAMED_SOUND_EFFECT:

                            String soundEffectName = event.getPacket().getSpecificModifier(String.class).read(0);

                            if(soundEffectName.contains("chest")){
                                if(HideCommand.hiddenPlayers.contains(event.getPlayer().getUniqueId())) {
                                    event.setCancelled(true);
                                }
                            }

                            break;
                    }
                }catch(FieldAccessException e){
                    e.printStackTrace();
                }
            }
        });
    }

}
