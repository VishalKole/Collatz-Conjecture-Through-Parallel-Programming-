import edu.rit.pj2.Vbl;

public class CollatzReductionVbl implements Vbl {

    int mincn, maxcn;
    int mini, maxi;

    // use two separate classes for max and min. use two reduction functions with same logic



    CollatzReductionVbl() {
        mincn = Integer.MAX_VALUE;
        mini = Integer.MAX_VALUE;
    }

    CollatzReductionVbl(int a, int b) {

        this.mincn = this.maxcn = a;
        this.mini = this.maxi = b;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException("clone error");
        }
    }

    public void reduce(Vbl vbl) {
        CollatzReductionVbl cc = (CollatzReductionVbl) vbl;

        if (mincn > cc.mincn || (mincn == cc.mincn && mini > cc.mini)) {
            this.mincn = cc.mincn;
            this.mini = cc.mini;
        }
        if (maxcn < cc.maxcn || (maxcn == cc.maxcn && maxi < cc.maxi)) {
            this.maxcn = cc.maxcn;
            this.maxi = cc.maxi;
        }

    }

    public void set(Vbl vbl) {
        CollatzReductionVbl cc = (CollatzReductionVbl) vbl;
        this.mincn = cc.mincn;
        this.mini = cc.mini;
        this.maxcn = cc.maxcn;
        this.maxi = cc.maxi;
    }

}
