package com.dbboy.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.dbboy.R;

import java.util.List;

public class SideLetterBar extends View {
    private String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"/*,"#"*/};
    private int choose = -1;
    private Paint paint = new Paint();
    //点击改变背景色
    private boolean showBg = false;
    private OnLetterChangedListener onLetterChangedListener;
    private TextView overlay;
    private List<String> letters;
    private float letterHeight;

    public SideLetterBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideLetterBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideLetterBar(Context context) {
        this(context, null);
    }

    /**
     * 设置悬浮的textview
     *
     * @param overlay
     */
    public void setOverlay(TextView overlay) {
        this.overlay = overlay;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (showBg) {
            //点侧边改背景颜色
            canvas.drawColor(Color.TRANSPARENT);
        }
        if (letters == null || letters.size() == 0) {
            return;
        }
        int width = getWidth();
        if (letterHeight == 0) {
            letterHeight = getHeight() / (26 * 1.0f);
        }

        for (int i = 0; i < letters.size(); i++) {

            paint.setAntiAlias(true);
            paint.setFakeBoldText(i == choose);  //加粗
            if (i == choose) {
                paint.setColor(getResources().getColor(R.color.black));
                paint.setFakeBoldText(true);  //加粗
                paint.setTextSize(getResources().getDimension(R.dimen.side_letter_bar_letter_size) * 1.5f);
            } else {
                paint.setColor(getResources().getColor(R.color.gray));
                paint.setTextSize(getResources().getDimension(R.dimen.side_letter_bar_letter_size));
            }
            float xPos = width / 2 - paint.measureText(letters.get(i)) / 2;
            float yPos = letterHeight * i + letterHeight;
            canvas.drawText(letters.get(i), xPos, yPos, paint);
            paint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnLetterChangedListener listener = onLetterChangedListener;

        if (letters == null || letters.size() == 0) {
            return false;
        }
        if (letterHeight == 0) {
            letterHeight = getHeight() / (26 * 1.0f);
        }
        final int c = (int) (y / letterHeight);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBg = true;
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < letters.size()) {
                        listener.onLetterChanged(letters.get(c));
                        choose = c;
                        invalidate();
                        if (overlay != null) {
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(letters.get(c));
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < letters.size()) {
                        listener.onLetterChanged(letters.get(c));
                        choose = c;
                        invalidate();
                        if (overlay != null) {
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(letters.get(c));
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBg = false;
                choose = -1;
                invalidate();
                if (overlay != null) {
                    overlay.setVisibility(GONE);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnLetterChangedListener(OnLetterChangedListener onLetterChangedListener) {
        this.onLetterChangedListener = onLetterChangedListener;
    }

    public void setLetter(List<String> b) {
        this.letters = b;
        invalidate();
    }

    public interface OnLetterChangedListener {
        void onLetterChanged(String letter);
    }

}
