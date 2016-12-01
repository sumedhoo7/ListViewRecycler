package com.dudupoo.listview.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dudupoo.listview.DescriptionFragment;
import com.dudupoo.listview.FlowerPOJO;
import com.dudupoo.listview.MainActivity;
import com.dudupoo.listview.R;

import java.util.ArrayList;

/**
 * Created by DuDuPoo on 30/11/16.
 */

class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ListViewHolder>
{
    ArrayList<FlowerPOJO> flowerList;
    private Context context;
    static class ListViewHolder extends RecyclerView.ViewHolder
    {
        private CardView cardView;
        private ImageView imageView;
        private TextView textView;
        public ListViewHolder(CardView itemView)
        {
            super(itemView);
            this.cardView = itemView;
            imageView = (ImageView) cardView.findViewById(R.id.cardview_image);
            textView = (TextView) cardView.findViewById(R.id.cardview_title);
        }
    }//end of ListViewHolder class

    public ListRecyclerAdapter(ArrayList<FlowerPOJO> list)
    {
        this.flowerList = list;
    }

    @Override
    public int getItemCount()
    {
        return flowerList.size();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_card, parent, false);
        return new ListViewHolder((CardView) v);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position)
    {
        context = holder.cardView.getContext();

        final FlowerPOJO flower = flowerList.get(holder.getAdapterPosition());
        holder.imageView.setImageResource(flower.getImageID());
        holder.textView.setText(flower.getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DescriptionFragment fragment = new DescriptionFragment();
                Bundle args = new Bundle();
                if(flower != null)
                    args.putString("title",flower.getTitle());
                fragment.setArguments(args);

                FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment, "description_fragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
