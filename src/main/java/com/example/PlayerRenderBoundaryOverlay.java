package com.example;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class PlayerRenderBoundaryOverlay extends Overlay
{
	private final Client client;
	private final ExampleConfig config;

	@Inject
	public PlayerRenderBoundaryOverlay(Client client, ExampleConfig config)
	{
		this.client = client;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		Player localPlayer = client.getLocalPlayer();
		if (localPlayer == null)
		{
			return null;
		}

		LocalPoint playerLocation = localPlayer.getLocalLocation();
		if (playerLocation == null)
		{
			return null;
		}

		int boundarySize = config.boundarySize();
		int halfSize = boundarySize / 2;
		
		// Calculate the corners of the boundary square
		// Each tile is 128 units in local coordinates
		int tileSize = 128;
		int offset = halfSize * tileSize;

		// Get the four corners of the boundary square
		LocalPoint northWest = new LocalPoint(
			playerLocation.getX() - offset,
			playerLocation.getY() + offset
		);
		LocalPoint northEast = new LocalPoint(
			playerLocation.getX() + offset,
			playerLocation.getY() + offset
		);
		LocalPoint southWest = new LocalPoint(
			playerLocation.getX() - offset,
			playerLocation.getY() - offset
		);
		LocalPoint southEast = new LocalPoint(
			playerLocation.getX() + offset,
			playerLocation.getY() - offset
		);

		// Convert to canvas points
		Polygon northLine = getLine(northWest, northEast);
		Polygon eastLine = getLine(northEast, southEast);
		Polygon southLine = getLine(southEast, southWest);
		Polygon westLine = getLine(southWest, northWest);

		// Draw the boundary
		Color color = config.boundaryColor();
		int width = config.boundaryWidth();
		
		graphics.setColor(color);
		graphics.setStroke(new BasicStroke(width));

		if (northLine != null)
		{
			graphics.drawPolygon(northLine);
		}
		if (eastLine != null)
		{
			graphics.drawPolygon(eastLine);
		}
		if (southLine != null)
		{
			graphics.drawPolygon(southLine);
		}
		if (westLine != null)
		{
			graphics.drawPolygon(westLine);
		}

		return null;
	}

	private Polygon getLine(LocalPoint start, LocalPoint end)
	{
		int plane = client.getPlane();
		
		// Get canvas points for both endpoints
		net.runelite.api.Point startCanvas = Perspective.localToCanvas(client, start, plane);
		net.runelite.api.Point endCanvas = Perspective.localToCanvas(client, end, plane);

		if (startCanvas == null || endCanvas == null)
		{
			return null;
		}

		// Create a polygon representing the line
		Polygon polygon = new Polygon();
		polygon.addPoint(startCanvas.getX(), startCanvas.getY());
		polygon.addPoint(endCanvas.getX(), endCanvas.getY());

		return polygon;
	}
}

