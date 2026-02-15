package com.example.navik;

import android.content.Context;
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

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);

        holder.nameText.setText(student.getName());
        holder.interestText.setText("Interested in: " + student.getInterest());
        holder.educationText.setText(student.getEducation());
        holder.lookingForText.setText("Looking for: " + student.getLookingFor());
        holder.studentImage.setImageResource(student.getImageResource());

        holder.btnConnect.setOnClickListener(v ->
            Toast.makeText(context, "Connection request sent to " + student.getName(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, interestText, educationText, lookingForText;
        ImageView studentImage;
        Button btnConnect;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.studentName);
            interestText = itemView.findViewById(R.id.studentInterest);
            educationText = itemView.findViewById(R.id.studentEducation);
            lookingForText = itemView.findViewById(R.id.studentLookingFor);
            studentImage = itemView.findViewById(R.id.studentImage);
            btnConnect = itemView.findViewById(R.id.btnConnect);
        }
    }
}
