package com.example.todolist;

import android.content.Context;
import android.view.AttachedSurfaceControl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class FileHelper {

	public static final String FILE_NAME = "todolist.dat";

	public static void writeData(ArrayList<String> toDoList, Context context) {

		try {

			FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(toDoList);
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> readData(Context context) {
		ObjectInputStream ois;
		try (FileInputStream fis = context.openFileInput(FILE_NAME)) {
			ois = new ObjectInputStream(fis);
			ArrayList<String> output = (ArrayList<String>) ois.readObject();
			ois.close();
			return output;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
