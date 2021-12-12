package main.bomberman.entities.character.enermy.ai;

public class Percolation {
    private final boolean[] a; // The model
    private final int n; // Size of the model
    private final int m;
    private int remaining; // Number of blocked sites
    private final WeightedQuickUnionUF solve;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n, int m) {
        if (n < 1) throw new IllegalArgumentException();
        a = new boolean[n * m];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                a[validate(i, j)] = false;
        this.n = n;
        this.m = m;
        remaining = n * m;
        solve = new WeightedQuickUnionUF(n * m);
    }

    // validate the row and column indices or to map from 2D to 1D
    private int validate(int row, int col) {
        return m * (row - 1) + col - 1;
    }

    // opens the site(row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > m) throw new IllegalArgumentException();
        else {
            if (!isOpen(row, col)) {
                a[validate(row, col)] = true;
                remaining--;
                connectFrom(validate(row, col));
            }
        }
    }

    // is the site (row,col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > m) throw new IllegalArgumentException();
        else return a[validate(row, col)];
    }

    private void connectFrom(int position) {
        int x = (int) Math.ceil((double) (position + 1) / m);
        int y = (int) ((double) position % m) + 1;
        {
            int temp = validate(x - 1, y);
            if (x >= 2 && a[temp] && solve.find(temp) != solve.find(position))
                solve.union(temp, position);
        }
        {
            if (y <= n - 1 && a[position + 1] && solve.find(position + 1) != solve.find(position))
                solve.union(position + 1, position);
        }
        {
            int temp = validate(x + 1, y);
            if (x <= n - 1 && a[temp] && solve.find(temp) != solve.find(position))
                solve.union(temp, position);
        }
        {
            if (y >= 2 && a[position - 1] && solve.find(position - 1) != solve.find(position))
                solve.union(position - 1, position);
        }
    }

    public boolean isConnected(int x1, int y1, int x2, int y2){
        int a = validate(x1, y1);
        int b = validate(x2, y2);
        return solve.find(a) == solve.find(b);
    }
}