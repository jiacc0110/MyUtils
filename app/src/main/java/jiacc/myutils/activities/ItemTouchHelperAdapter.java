package jiacc.myutils.activities;

/**
 * Created by jiacc on 2018/2/8.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition,int toPosition);
    void onItemDissmiss(int position);
}
