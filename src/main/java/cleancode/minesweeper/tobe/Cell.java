package cleancode.minesweeper.tobe;

import com.sun.source.tree.ReturnTree;

public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String CLOSED_CELL_SIGN = "□";
    private static final String OPENED_CELL_SIGN = "■";

    private final String sign;

    private int nearbyLandMineCount;
    private boolean isLandMine;
    
    //Cell이 가진 속성 : 근처 지뢰 숫자, 지뢰 여부
    //Cell의 상태 : 깃발 유무, 열렸다/닫혔다, 사용자가 확인함
    //ex) 깃발이 있는 셀은 아직 열지 않은 셀, 사용자가 확인할 셀, 지뢰 있을거 같다 해서 사용자가 확인
    //닫힘과 동시에 사용자가 확인한 셀

    private Cell(String sign, int nearbyLandMineCount, boolean isLandMine) {
        this.sign = sign;
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
    }

    public static Cell of(String sign, int nearbyLandMineCount, boolean isLandMine) {
        return new Cell(sign, nearbyLandMineCount, isLandMine);
    }

    public static Cell ofFlag() {
        /*
        셀에 깃발이 꽂히는 상황인데
        숫자를 가지면서 깃발이 꽂힐 수도
        숫자를 안가지면서 깃발이 꽂힐 수도

        지뢰셀인데 깃발이 꽂힐 수도
        지뢰셀이 아닌데 깃발이 꽂힐 수도
        */
        return of(FLAG_SIGN, 0, false);
    }

    public static Cell ofLaneMine() {
        return of(LAND_MINE_SIGN, 0, false);
    }

    public static Cell ofClosed() {
        /*
        nearbylandMine이 0 또는 1일 수 있다
        닫혀 있는 셀이나 지뢰일 수도 지뢰가 아닐 수도 있다
        */
        return of(CLOSED_CELL_SIGN, 0, false);
    }

    public static Cell ofOpened() {
        return of(OPENED_CELL_SIGN, 0, false);
    }

    public static Cell ofNearByLandMineCount(int count) {
        return of(String.valueOf(count), 0, false);
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
