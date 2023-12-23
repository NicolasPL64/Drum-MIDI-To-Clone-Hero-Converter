package com.fureniku.miditochdrums;

import com.fureniku.miditochdrums.panels.PanelOutput;

public class DrumObject {

    private int id;
    private long tick;
    private boolean isCymbal;
    private boolean isGhost;
    private boolean isKick;

    public DrumObject(int id, long tick, boolean isCymbal, boolean isGhost) {
        this.id = id;
        this.tick = tick;
        this.isCymbal = isCymbal;
        this.isGhost = isGhost;

        this.isKick = id == Drums.KICK.getId() || id == Drums.KICK_DOUBLE.getId();

        //MIDIToCHDrums.log(tick, "Adding drum %s (is cymbal: %s)", ""+id, ""+isCymbal);
    }

    public PanelOutput addToList(PanelOutput panelIn) {
        if (isCymbal) {
            panelIn.addToList("  " + tick + " = N " + (id + 64) + " 0");
        }
        panelIn.addToList("  " + tick + " = N " + id + " 0");
        if (isGhost) {
            panelIn.addToList("  " + tick + " = N " + (id + 39) + " 0");
        }

        return panelIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTick() {
        return tick;
    }

    public boolean isKick() {
        return isKick;
    }

    public boolean isCymbal() {
        return isCymbal;
    }
}