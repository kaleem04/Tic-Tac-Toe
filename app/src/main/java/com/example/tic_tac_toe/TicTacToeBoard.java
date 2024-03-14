package com.example.tic_tac_toe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {


    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winningLineColor;
    private int cellSize = getWidth()/3;
    private final Paint paint = new Paint();
    private final GameLogic game;



    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
       game = new GameLogic();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TicTacToeBoard, 0, 0);

   try {
       boardColor = a.getInteger(R.styleable.TicTacToeBoard_boardcolor, 0);
       XColor = a.getInteger(R.styleable.TicTacToeBoard_xcolor, 0);
         OColor = a.getInteger(R.styleable.TicTacToeBoard_ocolor, 0);
            winningLineColor = a.getInteger(R.styleable.TicTacToeBoard_winninglinecolor, 0);
   }finally {

       a.recycle();
   }
    }

    @Override
    protected void onMeasure(int width,int height){

        super.onMeasure(width,height);
        int dimension = Math.min(getMeasuredWidth(),getMeasuredHeight());
        cellSize = dimension/3;

        setMeasuredDimension(dimension,dimension);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
       float x = event.getX();
       float y = event.getY();

       int action = event.getAction();

       if (action == MotionEvent.ACTION_DOWN){

           int row = (int) Math.ceil(y/cellSize);
           int col = (int) Math.ceil(x/cellSize);

           if(game.updateGame(row,col)){
               invalidate();
               if (game.getPlayer() %2 == 0){
                   game.setPlayer(game.getPlayer()-1);
               }else{
                   game.setPlayer(game.getPlayer()+1);
               }
           }
           invalidate();
          return true;
       }

       return false;

    }

    private void drawGameBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for (int i = 1;i<3;i++){
            canvas.drawLine(cellSize*i,0,cellSize*i,canvas.getWidth(),paint);
        }
       for (int j = 1;j<3;j++){
           canvas.drawLine(0,cellSize*j,canvas.getWidth(),cellSize*j,paint);
       }
    }

    private void drawX(@NonNull Canvas canvas, int row , int col ){
         paint.setColor(XColor);
         canvas.drawLine((col+1)*cellSize - cellSize*0.2f,
                        row*cellSize + cellSize*0.2f,
                 col*cellSize + cellSize*0.2f,
                 (row+1)*cellSize - cellSize*0.2f,
                 paint);
         canvas.drawLine(col*cellSize + cellSize*0.2f,
                    row*cellSize + cellSize*0.2f,
                    (col+1)*cellSize - cellSize*0.2f,
                    (row+1)*cellSize - cellSize*0.2f,
                    paint);

    }

    private void drawO(Canvas canvas,int row ,int col ){
        paint.setColor(OColor);

        canvas.drawOval(col*cellSize + cellSize*0.2f,
                row*cellSize + cellSize*0.2f,
                (col+1)*cellSize - cellSize*0.2f,
                (row+1)*cellSize - cellSize*0.2f,
                paint);

    }

    private void drawMarkers(Canvas canvas){
        for (int r = 0 ; r<3;r++){

            for (int c = 0 ;c<3;c++){
               if(game.getGameBoard()[r][c] != 0){

                   if(game.getGameBoard()[r][c] == 1){
                       drawX(canvas,r,c);
                   }
                   else {
                       drawO(canvas,r,c);
                   }
               }
            }
        }

    }

}
