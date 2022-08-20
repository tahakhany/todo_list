package com.example.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

	private ArrayList<String> toDoList;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	// data is passed into the constructor
	MyRecyclerViewAdapter(Context context, ArrayList<String> toDoList) {
		this.mInflater = LayoutInflater.from(context);
		this.toDoList = toDoList;
	}

	// inflates the row layout from xml when needed
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView myTextView;

		ViewHolder(View itemView) {
			super(itemView);
			myTextView = itemView.findViewById(R.id.recyclerView_txt);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
		}
	}

	// convenience method for getting data at click position
	String getItem(int id) {
		return toDoList.get(id);
	}

	// allows clicks events to be caught
	void setClickListener(ItemClickListener itemClickListener) {
		this.mClickListener = itemClickListener;
	}

	// parent activity will implement this method to respond to click events
	public interface ItemClickListener {
		void onItemClick(View view, int position);
	}
}