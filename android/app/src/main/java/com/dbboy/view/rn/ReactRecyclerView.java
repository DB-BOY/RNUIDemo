package com.dbboy.view.rn;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dbboy.modle.ItemBean;
import com.dbboy.modle.RecyclerItemAdapter;
import com.dbboy.view.custom.RecyclerAndSideView;
import com.dbboy.view.custom.SideLetterBar;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by DB_BOY on 2019/6/19.</br>
 */
public class ReactRecyclerView extends ViewGroupManager<RecyclerAndSideView> {
    private static final String JS_METHOD_NAME = "js2native";
    private static final int JS_METHOD_ID = 1;
    private final String NATIVE_CLICK_JS_HEADER = "onItemHeader";
    private final String NATIVE_CLICK_JS_FOCUS = "onItemFocus";

    @Override
    public String getName() {
        return "RNRecyclerView";
    }

    @Override
    protected RecyclerAndSideView createViewInstance(final ThemedReactContext reactContext) {
        //构造测试数据
        //        List<ItemBean.ExpertEntity> datas = new ArrayList<>();
        //        ItemBean.ExpertEntity obj;
        //        for (int i = 0, len = Constants.HOT_NAMES.length; i < len; i++) {
        //            obj = new ItemBean.ExpertEntity();
        //            obj.setItem_name(Constants.HOT_NAMES[i]);
        //            obj.setItem_cover(Constants.IMAGES[i % Constants.IMAGES.length]);
        //            obj.setAttention_status(i % 20 % 7 % 5 + "");
        //            obj.setItem_id("item_id_xx_" + i);
        //            datas.add(obj);
        //        }
        //        Log.i("--ddd---",new Gson().toJson(datas));

        final RecyclerAndSideView expertsView = new RecyclerAndSideView(reactContext);
        expertsView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        expertsView.setListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                RecyclerItemAdapter adapter = expertsView.getAdapter();
                int index = adapter.getLetterPosition(letter);
                if (index > -1) {
                    RecyclerView recyclerView = expertsView.getRecyclerView();
                    LinearLayoutManager mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(index, 0);
                }
            }
        });

        return expertsView;
    }

    @ReactProp(name = "dataJson")
    public void setJson(final RecyclerAndSideView expertsView, String json) {
        List<ItemBean.ExpertEntity> data = new Gson().fromJson(json, new TypeToken<List<ItemBean.ExpertEntity>>() {}.getType());
        final RecyclerItemAdapter adapter = expertsView.setData(data);
        adapter.setOnItemClickListener(new RecyclerItemAdapter.OnItemClickListener() {
            @Override
            public void goExpert(ItemBean.ExpertEntity bean) {
                WritableMap nativeEvent = Arguments.createMap();
                nativeEvent.putString("attention_status", bean.getAttention_status());
                nativeEvent.putString("item_cover", bean.getItem_cover());
                nativeEvent.putString("item_id", bean.getItem_id());
                nativeEvent.putString("item_name", bean.getItem_name());

                ((ReactContext) expertsView.getContext()).getJSModule(RCTEventEmitter.class).receiveEvent(expertsView.getId(), NATIVE_CLICK_JS_HEADER, nativeEvent);
            }

            @Override
            public void doFocus(ItemBean.ExpertEntity bean, View vFocus) {
                WritableMap nativeEvent = Arguments.createMap();
                nativeEvent.putString("attention_status", bean.getAttention_status());
                nativeEvent.putString("item_cover", bean.getItem_cover());
                nativeEvent.putString("item_id", bean.getItem_id());
                nativeEvent.putString("item_name", bean.getItem_name());
                ((ReactContext) expertsView.getContext()).getJSModule(RCTEventEmitter.class).receiveEvent(expertsView.getId(), NATIVE_CLICK_JS_FOCUS, nativeEvent);
            }
        });
    }

    @android.support.annotation.Nullable
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(NATIVE_CLICK_JS_FOCUS, MapBuilder.of("registrationName", NATIVE_CLICK_JS_FOCUS)//
                , NATIVE_CLICK_JS_HEADER, MapBuilder.of("registrationName", NATIVE_CLICK_JS_HEADER));
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(JS_METHOD_NAME, JS_METHOD_ID);
    }

    @Override
    public void receiveCommand(RecyclerAndSideView root, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case JS_METHOD_ID:
                if (args != null) {
                    String id = args.getString(0);//获取第一个位置的数据
                    String attention = args.getString(1);
                    RecyclerItemAdapter adapter = root.getAdapter();
                    adapter.doFocus(id, attention);
                }
                break;
        }
    }
}
