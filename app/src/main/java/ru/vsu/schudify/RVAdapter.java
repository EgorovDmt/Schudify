package ru.vsu.schudify;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder>{

    List cards;

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        int currentCardPosition;
        Context mContext;

        CardViewHolder(CardView cv, Context context) { //добавили Context context
            super(cv);
            cardView = cv;

            /* Показываем Snackbar */
            mContext=context;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(mContext instanceof ShowScheduleActivity){
                        ((ShowScheduleActivity)mContext).showSnackbar(currentCardPosition);
                    }
                }
            });
        }
    }

    RVAdapter(List cards){
        this.cards = cards;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new CardViewHolder(cv, cv.getContext());
    }

    @NonNull


    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        CardView cardView = cardViewHolder.cardView;

        cardViewHolder.currentCardPosition = position;

        SubjectCard card = new SubjectCard("Программирование", "Лекция", "9:45", "11:30", "404п", "Скоробогатова М.А.");

        card = (SubjectCard) cards.get(position);

        TextView title = (TextView)cardView.findViewById(R.id.title);
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

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
