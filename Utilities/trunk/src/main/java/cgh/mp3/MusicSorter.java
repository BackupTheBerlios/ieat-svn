package cgh.mp3;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.DirectoryWalker;

import entagged.audioformats.*;
import entagged.audioformats.exceptions.CannotReadException;

public class MusicSorter extends DirectoryWalker
{
    private static File SINGLES_HOME = new File("D:\\My Music\\Singles");
    private static File UNSORTED_HOME = new File("D:\\My Music\\Unsorted");
    
    public static void main(String[] args) throws CannotReadException, IOException
    {
       MusicSorter s = new MusicSorter();
       s.process(UNSORTED_HOME);
       
        
        AudioFile audioFile = AudioFileIO.read(new File("D:\\My Eclipse\\Utilities\\Theme - Macguyver.mp3")); //Reads the given file.
        int bitrate = audioFile.getBitrate(); //Retreives the bitrate of the file.
        String artist = audioFile.getTag().getFirstTitle();
        System.out.println(artist);
    }

    private int count;

    public void process(File dir) throws IOException
    {
        walk(dir, null);
        System.out.println(this.count);
    }

    @Override
    protected void handleFile(File file1, int i, Collection collection) {
        ++count;
    }
}
