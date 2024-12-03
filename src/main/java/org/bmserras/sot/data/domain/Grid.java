package org.bmserras.sot.data.domain;

public enum Grid {

    r2c2c2(2, 2, 2),
    r3c2c3c2(3, 2, 3, 2),
    r3c3c3c3(3, 3, 3, 3),
    r3c3c4c3(3, 3, 4, 3);

    private final int rows;
    private final int[] cols;

    Grid(int rows, int... cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int[] getCols() {
        return cols;
    }

    @Override
    public String toString() {
        // return a string in the format "X rows (y cols, z cols, ...)"
        StringBuilder sb = new StringBuilder();
        sb.append(rows).append(" rows (");
        for (int i = 0; i < cols.length; i++) {
            sb.append(cols[i]);
            if (i < cols.length - 1) {
                sb.append(", ");
            }
        }
        return sb.append(")").toString();
    }
}
