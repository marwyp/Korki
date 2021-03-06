package com.example.korki.ui.appointments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.korki.R;
import engine.Teacher;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DayInfoAdapter extends ArrayAdapter<DayOfWeek> {
    // vars
    HashMap<DayOfWeek, NumberPicker> hourMap;
    HashMap<DayOfWeek, NumberPicker> minuteMap;
    HashMap<DayOfWeek, NumberPicker> durationMap;

    // constructor
    public DayInfoAdapter(@NonNull @NotNull Context context, ArrayList<DayOfWeek> days) {
        super(context, 0, days);
        hourMap = new HashMap<>();
        minuteMap = new HashMap<>();
        durationMap = new HashMap<>();
    }

    // override getView method
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DayOfWeek dayOfWeek = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.day_info,
                    parent, false);
        }
        // Lookup view for data population
        TextView day = convertView.findViewById(R.id.day);
        NumberPicker hour = convertView.findViewById(R.id.hour);
        NumberPicker minutes = convertView.findViewById(R.id.minutes);
        NumberPicker duration = convertView.findViewById(R.id.duration);
        hourMap.put(dayOfWeek, hour);
        minuteMap.put(dayOfWeek, minutes);
        durationMap.put(dayOfWeek, duration);
        // Populate the data into the template view using the data object
        day.setText(dayOfWeek.toString());
        hour.setMinValue(0);
        hour.setMaxValue(23);
        minutes.setMinValue(0);
        minutes.setMaxValue(59);
        duration.setMinValue(Teacher.MIN_DURATION);
        duration.setMaxValue(Teacher.MAX_DURATION);

        // Return the completed view to render on screen
        return convertView;
    }

    // return hour
    public int getHour(DayOfWeek dayOfWeek){
        return Objects.requireNonNull(hourMap.get(dayOfWeek)).getValue();
    }

    // get minutes
    public int getMinute(DayOfWeek dayOfWeek){
        return Objects.requireNonNull(minuteMap.get(dayOfWeek)).getValue();
    }

    // get duration
    public int getDuration(DayOfWeek dayOfWeek){
        return Objects.requireNonNull(durationMap.get(dayOfWeek)).getValue();
    }
}
