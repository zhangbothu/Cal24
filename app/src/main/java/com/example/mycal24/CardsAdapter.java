package com.example.mycal24;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder>{

    private final CardsHitAdapter adapter2;
    private final List<Card> hitCards;
    private List<Card> cardList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        // View cardView;
        ImageView cardImage;
        TextView cardName;

        public ViewHolder(View view) {
            super(view);
            //cardView = view;
            cardImage = (ImageView) view.findViewById(R.id.card_image);
            cardName = (TextView) view.findViewById(R.id.card_name);
        }
    }

    public CardsAdapter(List<Card> cardList1,CardsHitAdapter adapter2,List<Card> hitsCards) {
        cardList = cardList1;
        this.adapter2=adapter2;
        this.hitCards=hitsCards;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Card card = cardList.get(position);
                if (hitCards.size()<4) {
                    hitCards.add(card);
                    adapter2.notifyDataSetChanged();
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.cardImage.setImageResource(card.getImageId());
        holder.cardName.setText(card.getName());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

}