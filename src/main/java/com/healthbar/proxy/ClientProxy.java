package com.healthbar.proxy;

import com.healthbar.client.events.HealthBarRenderer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) 
	{
		MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());
		super.preInit(e);
	}
}
