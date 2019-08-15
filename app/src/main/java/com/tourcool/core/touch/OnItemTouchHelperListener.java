package com.tourcool.core.touch;

/**
 * @Author: JenkinsZhou on 2018/8/10 10:42
 * @E-Mail: 971613168@qq.com
 * Function: ItemTouchHelper Item移动监听
 * Description:
 */
public interface OnItemTouchHelperListener {

    /**
     * 选中开始Item
     *
     * @param start 选中position
     */
    void onStart(int start);

    /**
     * Item 移动中
     *
     * @param from 开始position
     * @param to   目标position
     */
    void onMove(int from, int to);

    /**
     * Item 移动结束
     *
     * @param from 开始position
     * @param to   移动后position
     */
    void onMoved(int from, int to);

    /**
     * Item移动结束
     *
     * @param star 最初开始position
     * @param end  最终结束position
     */
    void onEnd(int star, int end);
}
