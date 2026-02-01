package com.example.navik;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.CareerViewHolder> {
    
    private Context context;
    private List<Career> careerList;
    private List<Career> careerListFull;
    
    public CareerAdapter(Context context, List<Career> careerList) {
        this.context = context;
        this.careerList = careerList;
        this.careerListFull = new ArrayList<>(careerList);
    }
    
    @NonNull
    @Override
    public CareerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_career, parent, false);
        return new CareerViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CareerViewHolder holder, int position) {
        Career career = careerList.get(position);
        
        holder.titleText.setText(career.getTitle());
        holder.descriptionText.setText(career.getDescription());
        holder.degreeText.setText(career.getDegreePreferred());
        holder.salaryText.setText(career.getSalary());
        holder.careerImage.setImageResource(career.getImageResource());
        
        // Set emoji based on career type
        String emoji = getCareerEmoji(career.getTitle());
        holder.emojiText.setText(emoji);
        
        // Set demand badge
        holder.demandText.setText("🔥 High Demand");
        
        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            // Check if this is from RoadmapActivity
            if (context instanceof RoadmapActivity) {
                // Open career roadmap detail
                Intent intent = new Intent(context, CareerRoadmapDetailActivity.class);
                intent.putExtra("career_name", career.getTitle());
                intent.putExtra("career_image", career.getImageResource());
                intent.putExtra("career_emoji", getCareerEmoji(career.getTitle()));
                context.startActivity(intent);
            } else if (career.hasLearnMoreUrl()) {
                // Open learn more URL
                openWebsite(career.getLearnMoreUrl());
            } else {
                Toast.makeText(context, "Learn more about " + career.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private String getCareerEmoji(String careerTitle) {
        String title = careerTitle.toLowerCase();
        if (title.contains("software") || title.contains("developer") || title.contains("engineer")) {
            return "💻";
        } else if (title.contains("data") || title.contains("analyst")) {
            return "📊";
        } else if (title.contains("marketing") || title.contains("manager")) {
            return "📢";
        } else if (title.contains("design") || title.contains("creative")) {
            return "🎨";
        } else if (title.contains("business") || title.contains("finance")) {
            return "💼";
        } else if (title.contains("doctor") || title.contains("medical")) {
            return "⚕️";
        } else if (title.contains("teacher") || title.contains("education")) {
            return "👨‍🏫";
        } else if (title.contains("law") || title.contains("legal")) {
            return "⚖️";
        } else {
            return "⭐";
        }
    }
    
    private void openWebsite(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Unable to open website", Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    public int getItemCount() {
        return careerList.size();
    }
    
    public void filter(String query) {
        careerList.clear();
        if (query.isEmpty()) {
            careerList.addAll(careerListFull);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Career career : careerListFull) {
                if (career.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                    career.getDescription().toLowerCase().contains(lowerCaseQuery)) {
                    careerList.add(career);
                }
            }
        }
        notifyDataSetChanged();
    }
    
    static class CareerViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, descriptionText, degreeText, salaryText, emojiText, demandText;
        ImageView careerImage;
        
        public CareerViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.careerTitle);
            descriptionText = itemView.findViewById(R.id.careerDescription);
            degreeText = itemView.findViewById(R.id.careerDegree);
            salaryText = itemView.findViewById(R.id.careerSalary);
            careerImage = itemView.findViewById(R.id.careerImage);
            emojiText = itemView.findViewById(R.id.careerEmoji);
            demandText = itemView.findViewById(R.id.careerDemand);
        }
    }
}
