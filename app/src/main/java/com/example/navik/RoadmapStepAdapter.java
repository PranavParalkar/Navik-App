package com.example.navik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RoadmapStepAdapter extends RecyclerView.Adapter<RoadmapStepAdapter.StepViewHolder> {
    
    private Context context;
    private List<RoadmapStep> steps;
    
    public RoadmapStepAdapter(Context context, List<RoadmapStep> steps) {
        this.context = context;
        this.steps = steps;
    }
    
    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_roadmap_step, parent, false);
        return new StepViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        RoadmapStep step = steps.get(position);
        
        holder.stepNumber.setText(String.valueOf(step.getStepNumber()));
        holder.stepTitle.setText(step.getTitle());
        holder.stepDescription.setText(step.getDescription());
        holder.stepExams.setText("📝 Exams: " + step.getExams());
        holder.stepBranches.setText("🎓 Branches: " + step.getBranches());
        holder.stepDuration.setText("⏱️ Duration: " + step.getDuration());
        
        // Hide connecting line for last item
        if (position == steps.size() - 1) {
            holder.connectingLine.setVisibility(View.INVISIBLE);
        } else {
            holder.connectingLine.setVisibility(View.VISIBLE);
        }
        
        // Toggle details visibility
        holder.btnExpand.setOnClickListener(v -> {
            if (holder.detailsContainer.getVisibility() == View.GONE) {
                holder.detailsContainer.setVisibility(View.VISIBLE);
                holder.btnExpand.setText("Hide Details ▲");
            } else {
                holder.detailsContainer.setVisibility(View.GONE);
                holder.btnExpand.setText("View Details ▼");
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return steps.size();
    }
    
    static class StepViewHolder extends RecyclerView.ViewHolder {
        TextView stepNumber, stepTitle, stepDescription, stepExams, stepBranches, stepDuration, btnExpand;
        View connectingLine;
        LinearLayout detailsContainer;
        
        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNumber = itemView.findViewById(R.id.stepNumber);
            stepTitle = itemView.findViewById(R.id.stepTitle);
            stepDescription = itemView.findViewById(R.id.stepDescription);
            stepExams = itemView.findViewById(R.id.stepExams);
            stepBranches = itemView.findViewById(R.id.stepBranches);
            stepDuration = itemView.findViewById(R.id.stepDuration);
            btnExpand = itemView.findViewById(R.id.btnExpand);
            connectingLine = itemView.findViewById(R.id.connectingLine);
            detailsContainer = itemView.findViewById(R.id.detailsContainer);
        }
    }
}
