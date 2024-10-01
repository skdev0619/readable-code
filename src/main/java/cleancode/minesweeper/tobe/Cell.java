package cleancode.minesweeper.tobe;

import com.sun.source.tree.ReturnTree;

public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String CLOSED_CELL_SIGN = "□";
    private static final String OPENED_CELL_SIGN = "■";

    private final String sign;

    private Cell(String sign) {
        this.sign = sign;
    }

    public static Cell of(String sign) {
        return new Cell(sign);
    }

    public static Cell ofFlag() {
        return of(FLAG_SIGN);
    }

    public static Cell ofLaneMine() {
        return of(LAND_MINE_SIGN);
    }

    public static Cell ofClosed() {
        return of(CLOSED_CELL_SIGN);
    }

    public static Cell ofOpened() {
        return of(OPENED_CELL_SIGN);
    }

    public static Cell ofNearByLandMineCount(int count) {
        return of(String.valueOf(count));
    }

    public String getSign() {
        return sign;
    }

    public boolean isClosed() {
        //NPE 방지를 위한 상수와 equals 비교
        return CLOSED_CELL_SIGN.equals(sign);
    }

    public boolean doesNotClosed() {
        return !isClosed();
    }
}
