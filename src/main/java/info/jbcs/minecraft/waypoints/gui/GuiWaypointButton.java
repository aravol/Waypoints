package info.jbcs.minecraft.waypoints.gui;

import info.jbcs.minecraft.waypoints.Waypoint;
import info.jbcs.minecraft.waypoints.Waypoints;
import net.minecraftforge.common.DimensionManager;

public class GuiWaypointButton extends GuiExButton {
    Waypoint waypoint;
    boolean selected = false;

    public GuiWaypointButton(int x, int y, int w, int h, Waypoint wp) {
        super(x, y, w, h, wp.name, "waypoints:textures/widgets.png");

        waypoint = wp;
    }

    @Override
    public void render() {
        TexturedBox box;

        if (selected) {
            box = boxDisabled;
        } else {
            box = boxNormal;
        }

        box.render(gui, x, y, w, h);
        if (waypoint == null) {
            gui.drawStringWithShadow("Deleted", x + 4, y + 3, 0xff404040);
        } else {
            String dimName = "" + waypoint.dimension;

            try {
                dimName = DimensionManager.getProvider(waypoint.dimension).getDimensionType().getName();
            } catch (Throwable e) {
            }

            gui.drawStringWithShadow(caption, x + 4, y + 3, 0xffffffff);

            if (!Waypoints.compactView) {
                gui.drawStringWithShadow("Dimension: " + dimName, x + 4, y + 14, 0xff808080);
                gui.drawStringWithShadow("Coordinates: (" + waypoint.pos.getX() + ", " + waypoint.pos.getY() + ", " + waypoint.pos.getZ() + ")", x + 4, y + 25, 0xff808080);
            }
        }
    }

}
