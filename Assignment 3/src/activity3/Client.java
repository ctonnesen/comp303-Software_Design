package activity3;

import java.util.ArrayList;
import java.util.Arrays;

public class Client
{
	public static void main(String[] args)
	{
		System.out.println("\n***START OF TESTS FOR ARTIST CLASS***\n");
		
		System.out.println("Get an artist:");
		Artist artist1 = Artist.get("Beyonce");
		Artist.get("Beyonce");
		Artist.get("Beyonce");
		System.out.println(Artist.get("Beyonce"));

		System.out.println("\nGet an artist with alt names:");
		ArrayList<String> altNames = new ArrayList<String>(Arrays.asList("The What", "The Where", "The Where"));
		try
		{
			Artist.get("The Who", altNames);
		}
		catch (Exception NameAlreadyBoundException)
		{
			System.out.println("NameAlreadyBoundException");
		}
		
		System.out.println("\nGet an existing artist by name or alt name:");
		System.out.println(Artist.get("The Who"));
		System.out.println(Artist.get("The What"));
		try
		{
			Artist.get(Arrays.asList("The Why", "The What"));
		}
		catch (Exception NameNotFoundException)
		{
			System.out.println(NameNotFoundException);
		}
		
		System.out.println("\nTry changing alt name to The Why:");
		altNames.set(0, "The Why");
		System.out.println(Artist.get("The Who"));
		
		System.out.println("\nTry adding artist with used name:");
		try
		{
			Artist.get("Beyonce", Arrays.asList("Bey"));
		}
		catch (Exception NameAlreadyBoundException)
		{
			System.out.println(NameAlreadyBoundException);
		}
		
		System.out.println("\nTry fetching artists that are not present:");
		try
		{
			Artist.get(Arrays.asList("Justin Bieber", "U2"));
		}
		catch (Exception NameNotFoundException)
		{
			System.out.println(NameNotFoundException);
		}
		
		System.out.println("\nGet artist names and try changing names:");
		System.out.println(Artist.get("The Who").getName());
		System.out.println(Artist.get("The Who").getAllNames());
		System.out.println(Artist.get("Beyonce").getAllNames());
		Artist.get("The Who").getAllNames().set(1, "new");
		Artist.get("Beyonce").getAllNames().set(0, "new");
		
		System.out.println("\nPrint all artist names:");
		Artist.print();
		
		System.out.println("\nTry messing with nullness:");
		System.out.println(Artist.NULL);
		System.out.println(Artist.NULL.isNull());
		System.out.println(Artist.get("Bey").isNull());
		//System.out.println(Artist.NULL.getName()); -- assertion error		
		//System.out.println(Artist.get(null)); -- assertion error

		
		System.out.println("\n***END OF TESTS FOR ARTIST CLASS***\n");
		
		Album a1 = new Album("a1",artist1);
		Album a2 = new Album("a1",artist1);
		DupeChecker<Album> ap = Album.createContentDupeChecker(Song.getDupeCheckerByFile());
		boolean result = ap.isDupe(a1, a2);
		System.out.println(result);
		
		System.out.println("Create a genre:");
		System.out.println(Genre.get("Pop").getAllNames());
		
		ArrayList<String> syno = new ArrayList<String>(Arrays.asList("Hip pop", "R&B", "music"));
	
		System.out.println(	Genre.get("Pop", syno).getAllNames());
		
		System.out.println(Genre.get("Pop").getAllNames());
		
		System.out.println(	Genre.get("Hip pop", syno).getAllNames());
		
		

		
		


		//testing library
		System.out.println("\ntest library");
		Album alb = new Album("test album");
		
		Playlist pl = new Playlist("test playlist");
		Library l = Library.instance();
		Library j = Library.instance();
		
		if (l==j) {
			System.out.println("This object is a singleton!!!");
		}
		
	
		l.addAlbum(alb);
		l.addPlaylist(pl);
		
		

		Song s = new Song("file");
		Song p = new Song("file2");
		//Song q = new Song("file3");
		
		s.setaTitle("title1");
		s.setaArtist(Artist.get("Beyonce"));

		l.addSong(s);
		l.addSong(p);
		//l.addSong(q);

		/*
		try{
			l.addAlbum(alb);
		}
		catch (Exception AssertionError) {
			System.out.println("Please assign values");
		}
		*/
		
		
		alb.addTrack(1, s);
		alb.addTrack(1, p);
		
		alb.setaArtist(Artist.get("Taylor"));
		
		l.addAlbum(alb);
		
		System.out.println(l.isEmpty());
		pl.add(s);

		l.addPlaylist(pl);
		l.andRemoveSongs(s);
		
		

		Album b = new Album("test album");

		
		//l.removePlaylistAndSongs(pl);

	}


}
