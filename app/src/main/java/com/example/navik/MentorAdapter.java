package com.example.navik;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.MentorViewHolder> {
    
    private Context context;
    private List<Mentor> mentorList;
    
    public MentorAdapter(Context context, List<Mentor> mentorList) {
        this.context = context;
        this.mentorList = mentorList;
    }
    
    @NonNull
    @Override
    public MentorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mentor, parent, false);
        return new MentorViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull MentorViewHolder holder, int position) {
        Mentor mentor = mentorList.get(position);
        
        holder.nameText.setText(mentor.getName());
        holder.descriptionText.setText(mentor.getDescription());
        holder.availabilityText.setText(mentor.getAvailability());
        holder.mentorImage.setImageResource(mentor.getImageResource());
        
        // Set click listener for the profile button
        holder.btnViewProfile.setOnClickListener(v -> {
            if (mentor.hasWebsite()) {
                openWebsite(mentor.getWebsiteUrl());
            } else if (mentor.hasLinkedIn()) {
                openWebsite(mentor.getLinkedInUrl());
            } else {
                Toast.makeText(context, "Connect with " + mentor.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        
        // Set click listener for the entire card
        holder.itemView.setOnClickListener(v -> {
            if (mentor.hasWebsite()) {
                openWebsite(mentor.getWebsiteUrl());
            } else if (mentor.hasLinkedIn()) {
                openWebsite(mentor.getLinkedInUrl());
            } else {
                Toast.makeText(context, "Connect with " + mentor.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return mentorList.size();
    }
    
    private void openWebsite(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Unable to open website", Toast.LENGTH_SHORT).show();
        }
    }
    
    static class MentorViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, descriptionText, availabilityText;
        ImageView mentorImage;
        Button btnViewProfile;
        
        public MentorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.mentorName);
            descriptionText = itemView.findViewById(R.id.mentorDescription);
            availabilityText = itemView.findViewById(R.id.mentorAvailability);
            mentorImage = itemView.findViewById(R.id.mentorImage);
            btnViewProfile = itemView.findViewById(R.id.btnViewProfile);
        }
    }
}
