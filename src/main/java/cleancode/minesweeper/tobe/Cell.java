package cleancode.minesweeper.tobe;

public class Cell {
    //Flag도 close 기반에 깃발이 꽂힌다
    //FLAG_SIGN, CLOSED_CELL_SIG가 혼재된 상태
    
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private int nearbyLandMineCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;
    
    //Cell이 가진 속성 : 근처 지뢰 숫자, 지뢰 여부
    //Cell의 상태 : 깃발 유무, 열렸다/닫혔다, 사용자가 확인함
    //ex) 깃발이 있는 셀은 아직 열지 않은 셀, 사용자가 확인할 셀, 지뢰 있을거 같다 해서 사용자가 확인
    //닫힘과 동시에 사용자가 확인한 셀

    private Cell(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static Cell of(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearbyLandMineCount, isLandMine, false, false);
    }

    public static Cell create() {
        return of(0, false, false, false);
    }

    /*public static Cell ofFlag() {
        *//*
        셀에 깃발이 꽂히는 상황인데
        숫자를 가지면서 깃발이 꽂힐 수도
        숫자를 안가지면서 깃발이 꽂힐 수도

        지뢰셀인데 깃발이 꽂힐 수도
        지뢰셀이 아닌데 깃발이 꽂힐 수도
        *//*
        return of(FLAG_SIGN, 0, false);
    }

    public static Cell ofLaneMine() {
        return of(LAND_MINE_SIGN, 0, false);
    }

    public static Cell ofClosed() {
        *//*
        nearbylandMine이 0 또는 1일 수 있다
        닫혀 있는 셀이나 지뢰일 수도 지뢰가 아닐 수도 있다
        *//*
        return of(UNCHECKED_SIGN, 0, false);
    }

    public static Cell ofOpened() {
        return of(EMPTY_SIGN, 0, false);
    }

    public static Cell ofNearByLandMineCount(int count) {
        return of(String.valueOf(count), 0, false);
    }*/

    public void turnOnLandMine() {
        isLandMine = true;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        isOpened = true;
    }

    public void updateNearByLandMineCount(int count) {
        this.nearbyLandMineCount = count;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return nearbyLandMineCount != 0;
    }

    public String getSign() {
        if(isOpened){
            if(isLandMine){
                return LAND_MINE_SIGN;
            }

            if(hasLandMineCount()){
                return String.valueOf(nearbyLandMineCount);
            }
            return EMPTY_SIGN;
        }

        if(isFlagged){
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
