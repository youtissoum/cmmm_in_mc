package me.youtissoum.cmmm.blocks;

import me.youtissoum.cmmm.utils.ICellTickFunction;

public class CellBuilder extends Cell {
    protected CellBuilder() {
        super();
    }

    public CellBuilder cellId(String cellId) {
        this.cellId = cellId;
        return this;
    }

    public CellBuilder subtickId(int id) {
        this.subtickId = id;
        return this;
    }

    public CellBuilder onTick(ICellTickFunction func) {
        onTickFunc = func;
        return this;
    }
}
