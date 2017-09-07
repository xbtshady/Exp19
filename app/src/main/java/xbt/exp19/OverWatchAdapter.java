package xbt.exp19;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by xbt on 2017/9/3.
 */

public class OverWatchAdapter extends RecyclerView.Adapter<OverWatchAdapter.ViewHolder> {

    private Context mContext;

    private List<OverWatch> mOverWatch;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView overwatchImage;
        TextView overwatchName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            overwatchImage = (ImageView) view.findViewById(R.id.overwatch_image);
            overwatchName = (TextView) view.findViewById(R.id.overwatch_name);
        }
    }

    public OverWatchAdapter(List<OverWatch> OverWatchList){
        mOverWatch = OverWatchList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.overwatch_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                OverWatch overWatch = mOverWatch.get(position);
                Intent intent = new Intent(mContext,Main2Activity.class);
                intent.putExtra(Main2Activity.OVERWATCH_NAME, overWatch);
                mContext.startActivity(intent);
            }
        });
        return  holder;
    }
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        OverWatch overWatch = mOverWatch.get(position);
        viewHolder.overwatchName.setText(overWatch.getName());
        Glide.with(mContext).load(overWatch.getImageID()).into(viewHolder.overwatchImage);
    }

    public int getItemCount() {
        return mOverWatch.size();
    }
}
