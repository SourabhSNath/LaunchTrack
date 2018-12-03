package dev.codecathode.LaunchTrack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import dev.codecathode.LaunchTrack.Database.Model.Launch;
import dev.codecathode.LaunchTrack.R;

public class LaunchAdapter extends PagedListAdapter<Launch, LaunchAdapter.ViewHolder> {
    private SetOnClick setOnClick;

    public interface SetOnClick {
        void setOnClickListener(int pos);
    }

    public void setClick(SetOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }

    public LaunchAdapter() {
        super(Launch.DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public LaunchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchAdapter.ViewHolder holder, int position) {
        Launch launch = getItem(position);
        if (launch != null) {
            holder.bindView(launch);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView lName, timeView, lAgency, lLocation;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            lName = itemView.findViewById(R.id.lName);
            timeView = itemView.findViewById(R.id.dateText);
            lAgency = itemView.findViewById(R.id.lAgency);
            lLocation = itemView.findViewById(R.id.lLocation);
            itemView.setOnClickListener(this);

        }

        void bindView(Launch launch) {
            lName.setText(launch.getName());

            String date = launch.getWindowstart();
            date = date.substring(0, date.indexOf(" U"));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm:ss", Locale.ENGLISH);
            String localDateTime = LocalDateTime.parse(date, dateTimeFormatter)
                    .atOffset(ZoneOffset.UTC)
                    .atZoneSameInstant(ZoneId.systemDefault())
                    .format(dateTimeFormatter);

            int tbdTime = launch.getTbdtime();
            int tbdDate = launch.getTbddate();

            int year = LocalDateTime.parse(localDateTime,dateTimeFormatter).getYear();

            String ampm = localDateTime.substring(localDateTime.length() - 8);
            ampm = LocalTime.parse(ampm, DateTimeFormatter.ofPattern("HH:mm:ss")).format(DateTimeFormatter.ofPattern("hh:mm a"));

            if (tbdTime == 1 && tbdDate < 1) {
                localDateTime = year +", "+
                        localDateTime.substring(0, localDateTime.indexOf(",")) + " | Time TBD";

            } else if (tbdTime < 1 && tbdDate == 1) {
                localDateTime = year + ", "+
                        localDateTime.substring(0, localDateTime.indexOf(",")) + " | Date TBD";

            } else if (tbdDate == 1 && tbdTime == 1) {
                localDateTime = year +", "+
                        localDateTime.substring(0, localDateTime.indexOf(" ")) + " | Date & Time TBD";

            } else {
                localDateTime = year + ", "+
                        localDateTime.substring(0, localDateTime.indexOf(",")) + " at " + ampm;

            }

            timeView.setText(localDateTime);


            if (launch.getLsp() != null) {

                String agencyName = launch.getLsp().getName();
                if (agencyName.length() > 12) {
                    lAgency.setText(launch.getLsp().getAbbrev());
                } else {
                    lAgency.setText(agencyName);
                }

            }

            lLocation.setText(launch.getLocation().getName());

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (setOnClick != null) {
                if (position != RecyclerView.NO_POSITION) {
                    setOnClick.setOnClickListener(position);
                }
            }
        }
    }

}


//            switch (status) {
//                case 1:
//                    lStatus.setText("Launch Confirmed");
//                    break;
//                case 2:
//                    lStatus.setText("Launch Isn't Confirmed");
//                    break;
//                case 3:
//                    lStatus.setText("Launch Was A Success");
//                    break;
//                case 4:
//                    lStatus.setText("Launch Failed");
//                    break;
//                case 5:
//                    lStatus.setText("Unplanned Hold");
//                    break;
//                case 6:
//                    lStatus.setText("Vehicle In FLight");
//                    break;
//                case 7:
//                    lStatus.setText("Partial Failure During Launch");
//                    break;
//                    default:
//                        lStatus.setText("Status Unknown");
//                        break;
//            }