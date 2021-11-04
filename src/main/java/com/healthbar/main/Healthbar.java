package com.healthbar.main;

import com.healthbar.client.events.HealthBarRenderer;
import com.healthbar.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Healthbar.MODID, version = Healthbar.VERSION)
public class Healthbar {

	public static final String MODID = "healthbar";
    public static final String VERSION = "1.0";
    
    @SidedProxy(clientSide = "com.healthbar.proxy.ClientProxy", serverSide = "com.healthbar.proxy.CommonProxy")
	public static CommonProxy proxy;

    private static Configuration config;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	config = new Configuration(event.getSuggestedConfigurationFile(), "1.0", true);
    	config.addCustomCategoryComment("Settings", "Scale healthbar");
    	HealthBarRenderer.setXMul(config.getFloat("Width multiply", "Settings", 1, 0, Float.MAX_VALUE, "Multiplying the width of healthbar"));
    	HealthBarRenderer.setYMul(config.getFloat("Height multiply", "Settings", 1, 0, Float.MAX_VALUE, "Multiplying the height of healthbar"));
    	config.save();
    	proxy.preInit(event);
    }
    
    public static Configuration getConfig()
    {
    	return config;
    }
}
