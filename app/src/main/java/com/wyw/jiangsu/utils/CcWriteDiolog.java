package com.wyw.jiangsu.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.interfac.DialogListener;

public class CcWriteDiolog extends Dialog {

    Context context;
    LayoutParams p;
    DialogListener dialogListener;
    private boolean beclear=false;//添加判断是否清除


    public CcWriteDiolog(Context context, DialogListener dialogListener) {
        super(context);
        this.context = context;
        this.dialogListener = dialogListener;

        // TODO Auto-generated constructor stub
    }

    static final int BACKGROUND_COLOR = Color.WHITE;

    static final int BRUSH_COLOR = Color.BLACK;

    PaintView mView;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.sign_activity);


        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();


        p = getWindow().getAttributes();
        p.height = (int) (h * 0.6);// (int) (d.getHeight() * 0.4);
        p.width = w;
        getWindow().setAttributes(p);


        mView = new PaintView(context);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.tablet_view);
        frameLayout.addView(mView);
        mView.requestFocus();

        TextView btnCancel = (TextView) findViewById(R.id.tablet_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mView.setEmpty(true);
                cancel();
            }

        });

        TextView btnClear = (TextView) findViewById(R.id.tablet_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mView.clear();
            }
        });

        TextView btnOk = (TextView) findViewById(R.id.tablet_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    if (mView.pathIsEmpty()) return;
                    dialogListener.refreshActivity(mView.getCachebBitmap());
                    CcWriteDiolog.this.dismiss();
                    mView.setEmpty(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void show() {
        super.show();
    }

    private class PaintView extends View {
        private Paint paint;
        private Canvas cacheCanvas;
        private Bitmap cachebBitmap;
        private Path path;

        public void setEmpty(boolean empty) {
            isEmpty = empty;
        }

        private boolean isEmpty=true;

        //判断路径是否为空
        public boolean pathIsEmpty(){
            return isEmpty;
        }

        public Bitmap getCachebBitmap() {
            return cachebBitmap;
        }

        public PaintView(Context context) {
            super(context);
            init();
        }

        private void init() {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(12);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            path = new Path();

            cachebBitmap = Bitmap.createBitmap(p.width, (int) (p.height * 0.8),
                    Config.ARGB_8888);

            cacheCanvas = new Canvas(cachebBitmap);
            cacheCanvas.drawColor(Color.WHITE);
        }


        public void clear() {
            if (cacheCanvas != null) {
                isEmpty = true;
                paint.setColor(BACKGROUND_COLOR);
                cacheCanvas.drawPaint(paint);
                paint.setColor(Color.BLACK);
                cacheCanvas.drawColor(Color.WHITE);
                invalidate();
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // canvas.drawColor(BRUSH_COLOR);
            canvas.drawBitmap(cachebBitmap, 0, 0, null);
            canvas.drawPath(path, paint);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {

            int curW = cachebBitmap != null ? cachebBitmap.getWidth() : 0;
            int curH = cachebBitmap != null ? cachebBitmap.getHeight() : 0;
            if (curW >= w && curH >= h) {
                return;
            }

            if (curW < w)
                curW = w;
            if (curH < h)
                curH = h;

            Bitmap newBitmap = Bitmap.createBitmap(curW, curH,
                    Config.ARGB_8888);
            Canvas newCanvas = new Canvas();
            newCanvas.setBitmap(newBitmap);
            if (cachebBitmap != null) {
                newCanvas.drawBitmap(cachebBitmap, 0, 0, null);
            }
            cachebBitmap = newBitmap;
            cacheCanvas = newCanvas;
        }

        private float cur_x, cur_y;

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    cur_x = x;
                    cur_y = y;
                    path.moveTo(cur_x, cur_y);
                    break;
                }

                case MotionEvent.ACTION_MOVE: {
                    isEmpty = false;
                    path.quadTo(cur_x, cur_y, x, y);
                    cur_x = x;
                    cur_y = y;
                    break;
                }

                case MotionEvent.ACTION_UP: {
                    cacheCanvas.drawPath(path, paint);
                    path.reset();
                    break;
                }
            }

            invalidate();

            return true;
        }
    }

}
