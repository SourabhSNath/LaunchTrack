package dev.codecathode.LaunchTrack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import dev.codecathode.LaunchTrack.Database.Model.Payload;
import dev.codecathode.LaunchTrack.R;


/**
 * for payload details inside Details fragment
 */
public class PayloadAdapter extends ListAdapter<Payload, PayloadAdapter.ViewHolder> {

    public PayloadAdapter() {
        super(Payload.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_payloads, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Payload payload = getItem(position);
        holder.payloadName.setText(payload.getName());

        if (payload.getWeight()!=null){
            holder.weight.setVisibility(View.VISIBLE);
            holder.payloadWeight.setVisibility(View.VISIBLE);
            holder.payloadWeight.setText(payload.getWeight().toString());
        }

        if (payload.getDimensions()!=null){
            holder.dimen.setVisibility(View.VISIBLE);
            holder.payloadDimension.setVisibility(View.VISIBLE);
            holder.payloadDimension.setText(payload.getDimensions().toString());
        }

        if (payload.getCountryCodes()!=null){
            holder.countryCode.setVisibility(View.VISIBLE);
            holder.countryCodePayload.setVisibility(View.VISIBLE);
            holder.countryCodePayload.setText(payload.getCountryCodes());
        }

        if (payload.getDescription()!=null){
            holder.description.setVisibility(View.VISIBLE);
            holder.descriptionPayload.setVisibility(View.VISIBLE);
            holder.descriptionPayload.setText(payload.getDescription());
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView payloadName, weight, payloadWeight, dimen, payloadDimension, countryCode, countryCodePayload, description, descriptionPayload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            payloadName = itemView.findViewById(R.id.payloadName);
            weight = itemView.findViewById(R.id.weight);
            payloadWeight = itemView.findViewById(R.id.payloadWeight);
            dimen = itemView.findViewById(R.id.dimen);
            payloadDimension = itemView.findViewById(R.id.payloadDimension);
            countryCode = itemView.findViewById(R.id.countryCode);
            countryCodePayload = itemView.findViewById(R.id.countryCodePayload);
            description = itemView.findViewById(R.id.description);
            descriptionPayload = itemView.findViewById(R.id.descriptionPayload);
        }
    }
}
