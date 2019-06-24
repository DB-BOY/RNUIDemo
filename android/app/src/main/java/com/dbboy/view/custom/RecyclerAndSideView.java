package com.dbboy.view.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dbboy.R;
import com.dbboy.modle.ItemBean;
import com.dbboy.modle.RecyclerItemAdapter;
import com.github.promeg.pinyinhelper.Pinyin;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by DB_BOY on 2019/6/19.</br>
 */
public class RecyclerAndSideView extends RelativeLayout {
    private final TextView mOverLay;
    private RecyclerView recyclerView;
    private SideLetterBar sideLetterBar;
    private RecyclerItemAdapter adapter;
    private LinearLayoutManager manager;

    public RecyclerAndSideView(Context context) {
        this(context, null);
    }

    public RecyclerAndSideView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerAndSideView(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_expert, this);
        manager = new LinearLayoutManager(context);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        sideLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mOverLay = (TextView) findViewById(R.id.tv_letter_overlay);
        sideLetterBar.setOverlay(mOverLay);
        RecyclerViewDivider divider = new RecyclerViewDivider(context, LinearLayoutManager.HORIZONTAL, R.drawable.item_recycler_divider);
        divider.setSpace(25, 0, 0, 0);
        recyclerView.addItemDecoration(divider);
        this.adapter = new RecyclerItemAdapter(sideLetterBar);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        sideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int index = adapter.getLetterPosition(letter);
                if (index > -1) {
                    recyclerView.scrollToPosition(index);
                    LinearLayoutManager mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(index, 0);
                    invalidate();
                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    public RecyclerItemAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public SideLetterBar getSideLetterBar() {
        return sideLetterBar;
    }

    public RecyclerItemAdapter setData(String json) {

        ItemBean bean = new Gson().fromJson(json, ItemBean.class);
        List<ItemBean.ExpertEntity> data = bean.getData();

        return setData(data);
    }

    public RecyclerItemAdapter setData(List<ItemBean.ExpertEntity> data) {

        for (ItemBean.ExpertEntity expertBean : data) {
            expertBean.setPinyinName(Pinyin.toPinyin(expertBean.getItem_name(), ""));
        }

        Collections.sort(data, new PinComparator());
        adapter.setData(data);
        return adapter;
    }

    public void setListener(SideLetterBar.OnLetterChangedListener onLetterChangedListener) {
        sideLetterBar.setOnLetterChangedListener(onLetterChangedListener);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        reLayout();
    }

    public void reLayout() {
        if (getWidth() > 0 && getHeight() > 0) {
            int w = MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY);
            int h = MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY);
            measure(w, h);
            layout(getPaddingLeft() + getLeft(), getPaddingTop() + getTop(), getWidth() + getPaddingLeft() + getLeft(), getHeight() + getPaddingTop() + getTop());
        }
    }

    /**
     * 自定义拼音比较器
     */
    private class PinComparator implements Comparator<ItemBean.ExpertEntity> {
        @Override
        public int compare(ItemBean.ExpertEntity o1, ItemBean.ExpertEntity o2) {
            String a = o1.getPinyinName();
            String b = o2.getPinyinName();
            int a_ascii = a.toUpperCase().charAt(0);
            int b_ascii = b.toUpperCase().charAt(0);
            // 判断若不是字母，则排在字母之后
            if (a_ascii < 65 || a_ascii > 90) {
                return 1;
            } else if (b_ascii < 65 || b_ascii > 90) {
                return -1;
            } else {
                return a.compareTo(b);
            }
        }
    }
}
