package com.playerrenderboundary;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.WorldView;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;


public class PlayerRenderBoundaryOverlay extends Overlay
{
	private final Client client;
	private final PlayerRenderBoundaryConfig config;

	@Inject
	public PlayerRenderBoundaryOverlay(Client client, PlayerRenderBoundaryConfig config)
	{
		this.client = client;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		graphics.setColor(config.boundaryColor());
		graphics.setStroke(new BasicStroke(config.boundaryWidth()));

		final WorldPoint playerPos = client.getLocalPlayer().getWorldLocation();
		final WorldView wv = client.getLocalPlayer().getWorldView();
		
		if (playerPos == null)
		{
			return null;
		}
		final int plane = playerPos.getPlane();

		int currentPosX = playerPos.getX();
		int currentPosY = playerPos.getY();

		drawBoundary(wv, currentPosX, currentPosY, plane, graphics);

        return null;
	}

	private void drawBoundary(WorldView wv, int x, int y, int plane, Graphics2D graphics)
	{
		for( int i = -15; i <= 15; i++){
			drawBorderAtTile(wv, x + i, y - 15, plane, graphics, new int[]{-1,-1,+1,-1});
			drawBorderAtTile(wv, x + i, y + 15, plane, graphics, new int[]{-1,+1,+1,+1});
			drawBorderAtTile(wv, x - 15, y + i, plane, graphics, new int[]{-1,-1,-1,+1});
			drawBorderAtTile(wv, x + 15, y + i, plane, graphics, new int[]{+1,-1,+1,+1});
		}
	}

	private void drawBorderAtTile(WorldView wv, int x, int y, int plane, Graphics2D graphics, int[] f)
	{
		LocalPoint tile = LocalPoint.fromWorld(wv, x, y);
		Point start = Perspective.localToCanvas(client, new LocalPoint(
					tile.getX() + Perspective.LOCAL_HALF_TILE_SIZE * f[0],
					tile.getY() + Perspective.LOCAL_HALF_TILE_SIZE * f[1],
					wv),
				plane);
		Point end = Perspective.localToCanvas(client, new LocalPoint(
				tile.getX() + Perspective.LOCAL_HALF_TILE_SIZE * f[2],
				tile.getY() + Perspective.LOCAL_HALF_TILE_SIZE * f[3],
				wv),
			plane);
		drawLine(graphics, start, end);
	}

	private void drawLine(Graphics2D graphics, Point start, Point end)
	{
		if (start == null || end == null)
		{
			return;
		}
		graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
	}
}
