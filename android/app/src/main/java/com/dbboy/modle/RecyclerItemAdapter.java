package com.dbboy.modle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dbboy.R;
import com.dbboy.tools.PinyinUtils;
import com.dbboy.view.custom.GlideCircleTransform;
import com.dbboy.view.custom.SideLetterBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DB_BOY on 2019/6/13.</br>
 */
public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.Holder> {

    List<ItemBean.ExpertEntity> datas;

    private HashMap<String, Integer> letterIndexes;
    private OnItemClickListener listener;
    private Context context;
    private SideLetterBar letterBar;

    public RecyclerItemAdapter(SideLetterBar letterBar) {
        this.letterBar = letterBar;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_expert, null));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final ItemBean.ExpertEntity bean = datas.get(position);
        final String name = bean.getItem_name();
        holder.tvName.setText(name);
        boolean focus = bean.isFocus();
        holder.btnFocus.setSelected(focus);
        holder.tvFocus.setText(focus ? "已关注" : "关注");

        holder.ivHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.goExpert(bean);
                }
            }
        });
        holder.btnFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.doFocus(bean, v);
                }
            }
        });

        Glide.with(context).load(bean.getItem_cover())//
                .apply(new RequestOptions().transform(new GlideCircleTransform())).into(holder.ivHeader);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public int getLetterPosition(String name) {
        Integer integer = letterIndexes.get(name);
        return integer == null ? -1 : integer;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public void setData(List<ItemBean.ExpertEntity> data) {
        this.datas = data;
        int size = datas.size();
        letterIndexes = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String currentLetter = PinyinUtils.getUserFirstLetter(datas.get(i).getPinyinName());
            String previousLetter = i >= 1 ? PinyinUtils.getUserFirstLetter(datas.get(i - 1).getPinyinName()) : "";
            if (!TextUtils.equals(currentLetter, previousLetter)) {
                letterIndexes.put(currentLetter, i);
                list.add(currentLetter);
            }
        }
        letterBar.setLetter(list);

        notifyDataSetChanged();
    }

    public void addFocus(ItemBean.ExpertEntity bean, View view) {
        bean.setAttention_status("1");
        doFocus(true, view);
    }

    public void delFocus(ItemBean.ExpertEntity bean, View view) {
        bean.setAttention_status("0");
        doFocus(false, view);
    }

    void doFocus(boolean isFocus, View focusView) {
        focusView.setSelected(isFocus);
        TextView tv = (TextView) focusView.findViewById(R.id.tv_focus);
        tv.setText(isFocus ? "已关注" : "关注");
    }

    public void doFocus(String id, String attention) {
        for (int i = 0; i < datas.size(); i++) {
            if (id.equals(datas.get(i).getItem_id())) {
                Log.i("------", " attention: " + attention);
                datas.get(i).setAttention_status(attention);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public interface OnItemClickListener {
        void goExpert(ItemBean.ExpertEntity bean);

        void doFocus(ItemBean.ExpertEntity bean, View vFocus);
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView tvName, tvFocus;
        private View btnFocus;
        private ImageView ivHeader;

        public Holder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            btnFocus = itemView.findViewById(R.id.btn_focus);
            tvFocus = (TextView) itemView.findViewById(R.id.tv_focus);
            ivHeader = (ImageView) itemView.findViewById(R.id.iv_header);

        }
    }

}
