package dev.codecathode.LaunchTrack.Adapters;

import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This is a generic adapter, used only for loading the URLs in Details fragment
 * Generic because it would prevent creating multiple adapters
 *
 * (This could be an overkill, is a recyclerView necessary for an array of few strings? )
 *
 * @param <T> is an Object
 */
public abstract class StringAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<T> items;

    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent);

    public abstract void onBindData(RecyclerView.ViewHolder holder, int pos);

    protected StringAdapter(List<T> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return setViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindData(holder, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
