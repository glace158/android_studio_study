package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class LotoView extends View {
    private Paint paint;
    private boolean state;
    private boolean moving;
    private Random random;
    private boolean winRloss;

    public LotoView(Context context){
        super(context);
        init();
    }
    public LotoView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }
    public LotoView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
    }

    public boolean isState(){ return state; }

    private void init(){//초기화

        paint = new Paint();
        random = new Random();
        moving = false;
        state =false;
        paint.setStrokeWidth(20f);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        sufflelotto();
    }

    public boolean sufflelotto(){//로또 섞기
        state =false;
        paint.setColor(Color.GRAY);
        invalidate();
        int num = random.nextInt(2);
        winRloss = num == 1;
        return winRloss;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getMeasuredWidth();
        float height = getMeasuredHeight();

        if (!state){//아직 긁어보지 않은 로또
            canvas.drawRect(0, 0, width, height, paint);
        }
        else if(winRloss){//당첨일때 초록색 원 그리기
            canvas.drawCircle(width / 2,height / 2,height / 2, paint);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(width/ 2,height / 2,(height / 2 )* 0.7f, paint);
        }
        else{//꽝일때 빨간색 X자 그리기
            canvas.drawLine(0,0,width,height,paint);
            canvas.drawLine(width,0,0,height,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE://드래그시 노란색으로 표시
                paint.setColor(Color.YELLOW);
                moving = true;
                invalidate();
                break;
            case MotionEvent.ACTION_UP://드래그를 하고 난 뒤에 상태 변경
                if(!moving){break;}
                paint.setColor(winRloss ? Color.GREEN : Color.RED);
                state = true;
                invalidate();
                break;
        }
        return true;
    }
}
