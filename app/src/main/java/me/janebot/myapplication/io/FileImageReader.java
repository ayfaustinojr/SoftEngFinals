/**
 * 
 */
package me.janebot.myapplication.io;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Log;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Reads images from external dir
 * @author NeilDG
 *
 */
public class FileImageReader {
	private final static String TAG = "SR_ImageReader";
	
	private static FileImageReader sharedInstance = null;
	public static FileImageReader getInstance() {
		return sharedInstance;
	}
	
	private Context context;
	
	private FileImageReader(Context context) {
		this.context = context;
	}
	
	public static void initialize(Context context) {
		sharedInstance = new FileImageReader(context);
	}
	
	public static void destroy() {
		sharedInstance = null;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	/**
	 * Loads the specified image and returns its byte data
	 */
	public byte[] getBytesFromFile(String fileName, me.janebot.myapplication.io.ImageFileAttribute.FileType fileType) {
		File file = new File(me.janebot.myapplication.io.FileImageWriter.getInstance().getFilePath() + "/" +fileName + me.janebot.myapplication.io.ImageFileAttribute.getFileExtension(fileType));
		
		try {
			if(file.exists()) {
				FileInputStream inputStream = new FileInputStream(file);
				
				byte[] readBytes = new byte[(int) file.length()];
				inputStream.read(readBytes);
				inputStream.close();
				
				return readBytes;
			}
			else {
				Log.e(TAG, fileName + " does not exist in " +file.getAbsolutePath()+ " !");
				return null;
			}
		} catch(IOException e) {
			Log.e(TAG, "Error reading file " +e.getMessage());
			return null;
		}
	}

	/**
	 * Reads an image from file and returns its matrix form represented by openCV
	 * @param fileName
	 * @return
	 */
	public Mat imReadOpenCV(String fileName, me.janebot.myapplication.io.ImageFileAttribute.FileType fileType) {
		if(fileName.toLowerCase().contains(".jpg") == true) {
			Log.d(TAG, "Filepath for imread: " + fileName);
			return Imgcodecs.imread(fileName);
		}
		else {
			String completeFilePath = me.janebot.myapplication.io.FileImageWriter.getInstance().getFilePath() + "/" + fileName + me.janebot.myapplication.io.ImageFileAttribute.getFileExtension(fileType);
			Log.d(TAG, "Filepath for imread: " + completeFilePath);
			return Imgcodecs.imread(completeFilePath);
		}
	}

	public Mat imReadFullPath(String fullPath) {
		Log.d(TAG, "Filepath for imread: " + fullPath);
		return Imgcodecs.imread(fullPath);
	}

	public Mat imReadColor(String fileName, me.janebot.myapplication.io.ImageFileAttribute.FileType fileType) {
		String completeFilePath = me.janebot.myapplication.io.FileImageWriter.getInstance().getFilePath() + "/" + fileName + me.janebot.myapplication.io.ImageFileAttribute.getFileExtension(fileType);

		Log.d(TAG, "Filepath for imread: " + completeFilePath);
		return Imgcodecs.imread(completeFilePath, Imgcodecs.CV_LOAD_IMAGE_COLOR);
	}

	public boolean doesImageExists(String fileName, me.janebot.myapplication.io.ImageFileAttribute.FileType fileType) {
		File file = new File(me.janebot.myapplication.io.FileImageWriter.getInstance().getFilePath() + "/" +fileName + me.janebot.myapplication.io.ImageFileAttribute.getFileExtension(fileType));
		return file.exists();
	}

	public boolean doesImageExists(String fileName) {
		File file = new File(me.janebot.myapplication.io.FileImageWriter.getInstance().getFilePath() + "/" +fileName);
		return file.exists();
	}

	public Bitmap loadBitmapFromFile(String fileName, me.janebot.myapplication.io.ImageFileAttribute.FileType fileType) {
		String completeFilePath = me.janebot.myapplication.io.FileImageWriter.getInstance().getFilePath() + "/" + fileName + me.janebot.myapplication.io.ImageFileAttribute.getFileExtension(fileType);
		Log.d(TAG, "Filepath for loading bitmap: " +completeFilePath);
		return BitmapFactory.decodeFile(completeFilePath);
	}

	public Bitmap loadBitmapThumbnail(String fileName, me.janebot.myapplication.io.ImageFileAttribute.FileType fileType, int width, int height) {
		Bitmap resized = ThumbnailUtils.extractThumbnail(this.loadBitmapFromFile(fileName, fileType), width, height);
		return resized;
	}

	public Bitmap loadAbsoluteBitmapThumbnail(String absolutePath, int width, int height) {
		Bitmap resized = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(absolutePath), width, height);
		return resized;
	}

	public String getDecodedFilePath(String fileName, me.janebot.myapplication.io.ImageFileAttribute.FileType fileType) {
		String completeFilePath = me.janebot.myapplication.io.FileImageWriter.getInstance().getFilePath() + "/" + fileName + me.janebot.myapplication.io.ImageFileAttribute.getFileExtension(fileType);
		return completeFilePath;
	}
}
