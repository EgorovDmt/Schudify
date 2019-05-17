package ru.vsu.schudify;
import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder>{

    List cards;
    List cardsWrong;
    String season;

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        int currentCardPosition;
        Context mContext;

        CardViewHolder(CardView cv, Context context) {
            super(cv);
            cardView = cv;

            mContext=context;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(mContext instanceof MainActivity){

                    }
                }
            });
        }
    }

    public RVAdapter(List cards, String season){
        this.cards = cards;
        this.season = season;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Subject card = new Subject();
        card = (Subject) cards.get(0);


            CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview, parent, false);
            return new CardViewHolder(cv, cv.getContext());

    }

    @NonNull


    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        CardView cardView = cardViewHolder.cardView;

        cardViewHolder.currentCardPosition = position;

        Subject card = new Subject();


        card = (Subject) cards.get(position);

        TextView title = (TextView)cardView.findViewById(R.id.title1);
        title.setText(card.title);
        TextView type = (TextView)cardView.findViewById(R.id.type);
        type.setText(card.type);
        TextView timeStart = (TextView)cardView.findViewById(R.id.timeStart);
        timeStart.setText(card.timeStart);
        TextView timeEnd = (TextView)cardView.findViewById(R.id.timeEnd);
        timeEnd.setText(card.timeEnd);
        TextView classroom = (TextView)cardView.findViewById(R.id.classroom);
        classroom.setText(card.classroom);
        TextView teacher = (TextView)cardView.findViewById(R.id.teacher);
        teacher.setText(card.teacher);
        TextView subgroup = (TextView)cardView.findViewById(R.id.subgroup);
        if (card.subgroup != "") {
            subgroup.setText(card.subgroup + " подгр");
        }
        else {
            subgroup.setText(card.subgroup);
        }

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
