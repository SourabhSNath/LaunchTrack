package dev.codecathode.LaunchTrack.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.codecathode.LaunchTrack.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView Url;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        Url = itemView.findViewById(R.id.stringItem);
    }
}
