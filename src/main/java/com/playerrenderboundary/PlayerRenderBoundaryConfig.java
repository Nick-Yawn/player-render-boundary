package com.playerrenderboundary;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;
import java.awt.Color;

@ConfigGroup("playerrenderboundary")
public interface PlayerRenderBoundaryConfig extends Config
{
	@ConfigItem(
		keyName = "boundaryColor",
		name = "Boundary Color",
		description = "The color of the render boundary"
	)
	default Color boundaryColor()
	{
		return Color.WHITE;
	}

	@ConfigItem(
		keyName = "boundaryWidth",
		name = "Boundary Width",
		description = "The width of the boundary line in pixels"
	)
	@Range(min = 1, max = 10)
	default int boundaryWidth()
	{
		return 2;
	}

	@ConfigItem(
		keyName = "boundarySize",
		name = "Boundary Size",
		description = "The size of the boundary in tiles (width/height)"
	)
	@Range(min = 1, max = 100)
	default int boundarySize()
	{
		return 31;
	}
}

