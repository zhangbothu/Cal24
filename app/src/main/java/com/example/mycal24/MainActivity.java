package com.example.mycal24;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycal24.util.Compute24Poker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Card> cardList = new ArrayList<Card>();
    private List<Card>  hitCardsList=new ArrayList<>();
    private static final String TAG = "MainActivity";
    Button  btnClear;
    TextView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCards();
        final RecyclerView hitCards = (RecyclerView) findViewById(R.id.hitCards);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 4);
        hitCards.setLayoutManager(layoutManager2);

        final CardsHitAdapter adapter2 = new CardsHitAdapter(hitCardsList);
        hitCards.setAdapter(adapter2);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.allcards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.addItemDecoration(new SpacesItemDecoration(0));
        recyclerView.setLayoutManager(layoutManager);
        CardsAdapter adapter = new CardsAdapter(cardList,adapter2,hitCardsList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                Toast.makeText(MainActivity.this,"触摸事件",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });


        resultView=findViewById(R.id.results);


        Button startCal=findViewById(R.id.startCal);
        startCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hitCardsList.size()!=4){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("温馨提示");
                    dialog.setMessage("请选择4张扑克！");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    dialog.show();
                    return;
                }
                Integer[] cards4=new Integer[4];
                for(int i=0;i<hitCardsList.size();i++)
                {
                    cards4[i]=Integer.parseInt(hitCardsList.get(i).getName());
                }
                Compute24Poker test = new Compute24Poker();
                test.compute(cards4);
                List<String> results=test.getResults();
                String mline="";
                for(String s:results)
                {
                    mline+=s+"\n";
                }
                resultView.setText(mline);

            }
        });
        btnClear=findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitCardsList.clear();
                resultView.setText("");
                adapter2.notifyDataSetChanged();

            }
        });



    }
    private void initCards() {
        for (int i = 1; i <= 13; i++) {
            int id = getResources().getIdentifier("a1"+i, "drawable", this.getPackageName());
            Card c = new Card(""+i, id,"黑桃");
            cardList.add(c);

        }
        for (int i = 1; i <= 13; i++) {
            int id = getResources().getIdentifier("a2"+i, "drawable", this.getPackageName());
            Card c = new Card(""+i, id,"红桃");
            cardList.add(c);

        }
        for (int i = 1; i <= 13; i++) {
            int id = getResources().getIdentifier("a3"+i, "drawable", this.getPackageName());
            Card c = new Card(""+i, id,"梅花");
            cardList.add(c);

        }
        for (int i = 1; i <= 13; i++) {
            int id = getResources().getIdentifier("a4"+i, "drawable", this.getPackageName());
            Card c = new Card(""+i, id,"方块");
            cardList.add(c);

        }
    }


}