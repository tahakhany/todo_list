package com.example.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

	private final ArrayList<String> toDoList;
	private final LayoutInflater mInflater;

	// data is passed into the constructor
	MyRecyclerViewAdapter(Context context, ArrayList<String> toDoList) {
		this.mInflater = LayoutInflater.from(context);
		this.toDoList = toDoList;
	}

	// inflates the row layout from xml when needed
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = mInflater.inflate(R.layout.recycler_view_cards, parent, false);
		return new ViewHolder(view);
	}

	// binds the data to the TextView in each row
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		String animal = toDoList.get(position);
		holder.myTextView.setText(animal);
		holder.myTextView.setOnClickListener(view -> {
			DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
				switch (which){
					case DialogInterface.BUTTON_POSITIVE:
						toDoList.remove(position);
						MainActivity.updateToDoList();
						break;

					case DialogInterface.BUTTON_NEGATIVE:
						//No button clicked
						break;
				}
			};
			AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
			builder.setMessage("Are you sure you want to remove this task?").setPositiveButton("Yes", dialogClickListener)
					.setNegativeButton("No", dialogClickListener).show();

		});
	}

	// total number of rows
	@Override
	public int getItemCount() {
		return toDoList.size();
	}


	// stores and recycles views as they are scrolled off screen
	public static class ViewHolder extends RecyclerView.ViewHolder{
		TextView myTextView;

		ViewHolder(View itemView) {
			super(itemView);
			myTextView = itemView.findViewById(R.id.recyclerView_txt);
		}

	}
}