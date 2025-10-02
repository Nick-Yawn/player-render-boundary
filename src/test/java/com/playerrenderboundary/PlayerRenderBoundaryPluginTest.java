package com.playerrenderboundary;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PlayerRenderBoundaryPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PlayerRenderBoundaryPlugin.class);
		RuneLite.main(args);
	}
}

