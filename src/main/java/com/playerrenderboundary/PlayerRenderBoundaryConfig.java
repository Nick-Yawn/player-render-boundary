package com.playerrenderboundary;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;
import java.awt.Color;

@ConfigGroup("playerrenderboundary")
public interface PlayerRenderBoundaryConfig extends Config
{
	@Alpha
	@ConfigItem(
		keyName = "boundaryColor",
		name = "Boundary Color",
		description = "The color of the render boundary"
	)
	default Color boundaryColor()
	{
		return new Color(255, 255, 255, 128);
	}

	@ConfigItem(
		keyName = "boundaryWidth",
		name = "Boundary Width",
		description = "The width of the boundary line in pixels"
	)
	@Range(min = 1, max = 4)
	default int boundaryWidth()
	{
		return 1;
	}

}

