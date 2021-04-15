package code;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        Library lib = new Library();
        ArrayList<String> songKeys = new ArrayList<>();
        songKeys = tryImportFiles(lib,songKeys); /*prompt user and import songs to lib*/
        actionCaller(lib,songKeys);
    }
    /* Helper methods.*/
    private static String promptUser(){
        printBorder();
        System.out.println("Welcome to Library. Please input the audio file folder path to allow import to library.\n" +
                "This application won't start if the Library is empty.");
        Scanner scanner = new Scanner(System.in);
        String returnString = scanner.nextLine();
        return returnString;
    }
    
    private static void FilterLibrary(Library lib) {
    	printBorder();
    	System.out.println("To filter library please provide the Name of the Tag (artist,title,time,etc...) you want to use as a filtering predicate (ie. artist)");
    	Scanner scanner = new Scanner(System.in);
        String TagName = scanner.nextLine();
        System.out.println("Now provide your filtering predicate (ie. Bob Marley)");
    	String Filter = scanner.nextLine();
    	System.out.println("These are the filtered songs:");
    	lib.filterByTag(TagName, Filter);

    }
    private static ArrayList<String> tryImportFiles(Library library, ArrayList<String> songKeys){
        do{
            try{
                songKeys.addAll(library.importSongsToLib(promptUser()));
            } catch(IOException ioException){
                System.out.println("Error: directory does not exist.");
            } catch(UnsupportedAudioFileException unsupportedAudioFileException){
                System.out.println("Error: Sorry, this file format does not support.");
            }
        } while (promptUserAgain(songKeys)||songKeys.size()==0);
        return songKeys;
    }
    private static boolean promptUserAgain(ArrayList<String> songIDs){
        printBorder();
        System.out.println("Want to import again from a different file path?(Y/N)");
        Scanner scanner = new Scanner(System.in);
        char userinput = scanner.next().charAt(0);
        if (userinput=='Y'||userinput=='y'){
            return true;
        }else if (songIDs.size()==0){ /*won't go into menu if library is empty*/
            return true;
        }
        return false;
    }
    private static void actionCaller(Library library, ArrayList<String> songKeys){
        boolean ifTerminate = false;
        actionLoop:
        while(!ifTerminate){
            switch(showMenuAndReturnAction()){
                case 1: /*print lib songs, to do: add sorting option*/
                    library.printLibSongs(true,songKeys);
                    break;
                case 2: /*Go to PlayList*/
                    displayPlaylistMenu(library,songKeys);
                    break;
                case 3:/*Go to Album*/
                    displayAlbumMenu(library,songKeys);
                    break;
                case 4:
                    selectSong(library,songKeys);
                    break;
                case 5:/*update lib*/
                    printBorder();
                    library.updateLib();
                    System.out.println("Library Updated.");
                    break;
                case 6:
                    FilterLibrary(library);
                    break;
                case 7:
                    printBorder();
                    System.out.println("Songs in the Library sorted by song name:");
                    songKeys = library.getSortedSongKeysBySongName();
                    library.printLibSongs(false,songKeys);
                    break;
                case 8:
                    printBorder();
                    System.out.println("Songs in the Library sorted by song length:");
                    songKeys = library.getSortedSongKeysBySongLength();
                    library.printLibSongs(false,songKeys);
                    break;
                case 11:
                    ifTerminate = true;
                    printBorder();
                    System.out.println("GoodBye");
                    break actionLoop;
                case 9:
                    printBorder();
                    songKeys = clientSort(library);
                    System.out.println("Songs in the Library sorted by user-input:");
                    library.printLibSongs(false,songKeys);
                    break;
                case 10:
                    printBorder();
                    System.out.println("Songs in the Library sorted by song artist:");
                    songKeys = library.getSortedSongKeysBySongArtist();
                    library.printLibSongs(false,songKeys);
                    break;
                default: /* unrecognized action*/
                    break;
            }
        }
    }
    private static ArrayList<String> clientSort(Library library){
        ArrayList<String> returnKeys = new ArrayList<>();
        returnKeys = library.sort(userDefinedSort());
        return returnKeys;
    }
    private static String[] userDefinedSort(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("You want to sort songs by 1+ [title;name;time;any custom tag...]: (use , to seperate)");
        System.out.println("<ex: title, time, artist> will sort the songs by title, if two songs has SAME title,\n" +
                "will then sort by time, if they have SAME time, will then sort them by artist.");
        String sortBy = scanner.nextLine();
        return sortBy.toUpperCase().replaceAll(" ","").split(",");
    }
    private static  void displayAlbumMenu(Library library,ArrayList<String> songKeys){
        displayAlbumMenuLoop:
        while(true){
            printBorder();
            System.out.println("Welcome to Album:\n" +
                    "1: Display all albums\n" +
                    "2: Make an album\n" +
                    "3: Add song to an album\n" +
                    "4: Play selected album tracks in order\n" +
                    "5: Delete an album\n" +
                    "6. Delete a song in Album by track number\n" +
                    "7. Filter Album:\n" +
                    "8: Go back to Library Menu");
            printBorder();
            ArrayList<Integer> albumKeys = new ArrayList<>();
            for(Integer integer:library.getAllAlbums().keySet()){
                albumKeys.add(integer);
            }
            Scanner scanner = new Scanner(System.in);
            int albumAction = invalidCheck(scanner,8,1,true);
            switch (albumAction){
                case 2:
                    printBorder();
                    System.out.println("What's the new album's name?");
                    String albumName = scanner.nextLine();
                    System.out.println("What's the new album's artist?");
                    String albumArtist = scanner.nextLine();
                    try{
                        library.makeAlbum(albumName,albumArtist);
                    } catch (IllegalArgumentException illegalArgumentException){
                        System.out.println("This album already exist!");
                    }
                    break;
                case 1:
                    printBorder();
                    library.printAllAlbums(albumKeys);
                    break;
                case 3:
                    printBorder();
                    if(library.getAllAlbums().values().size()==0){
                        System.out.println("There is no album in the library.");
                        break;
                    }
                    System.out.println("Type the album index you want to modify:");
                    int albumIndex = indexCheck(scanner,-1,library.getAllAlbums().values().size(),"Wrong album index, please try again",true);
                    library.printLibSongs(false,songKeys);
                    System.out.println("Type the song index to add:");
                    int songIndex = indexCheck(scanner,-1,songKeys.size(),"Wrong song index, please try again:",true);
                    System.out.println("This song is in which track number (the new track number will over-write the old ones):");
                    int trackIndex = indexCheck(scanner,-1,666666,"Wrong track number, please try again:",true);
                    //library.addSongToAlbum(albumKeys.get(albumIndex),songKeys.get(songIndex));
                    library.addSongToAlbumWithTrackNumber(albumIndex, songKeys.get(songIndex), trackIndex);
                    break;
                case 4:
                    printBorder();
                    if(library.getAllAlbums().values().size()==0){
                        System.out.println("There is no album in the library.");
                        break;
                    }
                    System.out.println("Type the album index you want to play:");
                    int playAlbumIndex = indexCheck(scanner,-1,library.getAllAlbums().values().size(),"Wrong album index, please try again.",true);
                    library.playAlbum(albumKeys.get(playAlbumIndex));
                    break;
                case 5:
                    printBorder();
                    if(library.getAllAlbums().values().size()==0){
                        System.out.println("There is no album in the library.");
                        break;
                    }
                    System.out.println("Type the album index you want to delete:");
                    int removeAlbumIndex = indexCheck(scanner,-1,library.getAllAlbums().values().size(),"Wrong album index, please try again.",true);
                    library.removeAlbum(albumKeys.get(removeAlbumIndex));
                    break;
                case 8:
                    break displayAlbumMenuLoop;
                case 7:
                    printBorder();
                    if(library.getAllAlbums().values().size()==0){
                        System.out.println("There is no album in the library.");
                        break;
                    }
                    System.out.println("Please input the Album index you want to filter:");
                    int filterAlbumIndex = indexCheck(scanner,-1,library.getAllAlbums().values().size(),"Wrong album index, please try again.",true);
                    printBorder();
                    System.out.println("Please provide the Name of the Tag (artist,title,time,etc...) you want to use as a filtering predicate (ie. artist)");
                    String TagName = scanner.nextLine();
                    System.out.println("Now provide your filtering predicate (ie. Bob Marley)");
                    String Filter = scanner.nextLine();
                    System.out.println("These are the filtered songs:");
                    library.filterAlbumBy(albumKeys.get(filterAlbumIndex),TagName,Filter);
                    break;
                case 6:
                    printBorder();
                    if(library.getAllAlbums().values().size()==0){
                        System.out.println("There is no album in the library.");
                        break;
                    }
                    System.out.println("Type the album index you want to select:");
                    int selectAlbumIndex = indexCheck(scanner,-1,library.getAllAlbums().values().size(),"Wrong album index, please try again.",true);
                    System.out.println("Type the track number you want to delete:");
                    int sstrackIndex = indexCheck(scanner,-1,666666,"Wrong track number, please try again",true);
                    library.removeTrackFromAlbum(albumKeys.get(selectAlbumIndex),sstrackIndex);
                    break;
                default:
                    break;
            }
        }
    }
    private static void displayPlaylistMenu(Library library,ArrayList<String> songKeys){
        displayPlaylistMenuLoop:
        while(true){
            printBorder();
            System.out.println("Welcome to Playlist:\n" +
                    "1: Display all playlists\n" +
                    "2: Make a playlist\n" +
                    "3: Add song to a Playlist\n" +
                    "4: Play Playlist\n" +
                    "5: Delete a Playlist\n" +
                    "6: Generate Playlist by Genre\n" +
                    "7: Generate Playlist by Artist\n" +
                    "8: Filter playlist\n" +
                    "9: Go back to Library Menu");
            printBorder();
            ArrayList<Integer> playlistKeys = new ArrayList<>();
            for(Integer integer:library.getAllPlaylists().keySet()){
                playlistKeys.add(integer);
            }
            Scanner scanner = new Scanner(System.in);
            int playlistAction = invalidCheck(scanner,9,1,true);
            switch (playlistAction){
                case 1:
                    library.printAllPlaylists(playlistKeys);
                    break;
                case 2:
                    printBorder();
                    System.out.println("What's the new playlist title?");
                    String pTitle = scanner.nextLine();
                    library.makePlaylist(pTitle);
                    break;
                case 3:
                    printBorder();
                    if(library.getAllPlaylists().values()
                            .size()==0){
                        System.out.println("There is no playlist in the library.");
                        break;
                    }
                    System.out.println("Type the playlist index you want to modify:");
                    int playlistIndex = indexCheck(scanner, -1, library.getAllPlaylists().values().size(), "Wrong playlist index, try again:",true);
                    library.printLibSongs(false,songKeys);
                    System.out.println("Type the song index to add:");
                    int songIndex = indexCheck(scanner, -1, songKeys.size(), "Wrong song index, try again:",true);
                    library.addSongToPlayList(playlistKeys.get(playlistIndex),songKeys.get(songIndex));
                    break;
                case 4:
                    printBorder();
                    if(library.getAllPlaylists().values().size()==0){
                        System.out.println("No playlist in the library.");
                        break;
                    }
                    System.out.println("Type the playlist index you want to play:");
                    int playPlaylistIndex = indexCheck(scanner,-1,library.getAllPlaylists().values().size(),"Wrong playlist index, try again:",true);
                    library.playPlaylist(playlistKeys.get(playPlaylistIndex));
                    break;
                case 5:
                    printBorder();
                    System.out.println("Type the playlist index you want to delete:");
                    int removePlaylistIndex = indexCheck(scanner,-1,library.getAllPlaylists().values().size(),"Wrong playlist index, try again:",true);
                    library.removePlayList(playlistKeys.get(removePlaylistIndex));
                    break;
                case 9:
                    break displayPlaylistMenuLoop;
                case 8:
                    printBorder();
                    if(library.getAllPlaylists().values().size()==0){
                        System.out.println("No playlist in the library.");
                        break;
                    }
                    System.out.println("Please input the Playlist index you want to filter:");
                    int filterPlaylistIndex = indexCheck(scanner,-1,library.getAllPlaylists().values().size(),"Wrong playlist index, try again:",true);
                    printBorder();
                    System.out.println("Please provide the Name of the Tag (artist,title,time,etc...) you want to use as a filtering predicate (ie. artist)");
                    String TagName = scanner.nextLine();
                    System.out.println("Now provide your filtering predicate (ie. Bob Marley)");
                    String Filter = scanner.nextLine();
                    System.out.println("These are the filtered songs:");
                    library.filterPlaylistBy(playlistKeys.get(filterPlaylistIndex),TagName,Filter);
                    break;
                case 6:
                    printBorder();
                    System.out.println("Please Input Name of Genre:");
                    String userInput = scanner.nextLine();
                    library.PlaylistByGenre(userInput);
                    break;
                case 7:
                    printBorder();
                    System.out.println("Please Input Name of Artist:");
                    String userInputs = scanner.nextLine();
                    library.PlaylistAllByArtist(userInputs);
                    break;
                default:
                    break;
            }
        }
    }

    private static int indexCheck(Scanner scanner, int playlistIndex, int playlistSize, String s, boolean ifFirstTime) {
        while (!(playlistIndex < playlistSize) || !(playlistIndex >= 0)) {
            if(!ifFirstTime){
                System.out.println(s);
            }
            playlistIndex = invalidCheck(scanner,0,0,false);
            ifFirstTime = false;
        }
        return playlistIndex;
    }

    private static int invalidCheck(Scanner scanner, int upperBound, int lowerBound, boolean ifBoundry) {
        String userinput;
        int returnValue;
        while(true){
            userinput = scanner.nextLine();
            try{
                returnValue = Integer.parseInt(userinput);
                if(returnValue<=upperBound&&returnValue>=lowerBound&&ifBoundry){
                    return returnValue;
                } else if(!ifBoundry){
                    return returnValue;
                } else{
                    throw new NumberFormatException("Invalid");
                }
            }catch (NumberFormatException numberFormatException){
                System.out.println("Invalid input, please try again");
            }
        }
    }

    private static int showMenuAndReturnAction(){
        System.out.println("**********************************************\n" +
                "Please type in which action you want to do\n" +
                "1: Display Library Songs\n" +
                "2: Go to PlayLists Menu\n" +
                "3: Go to Albums Menu\n" +
                "4: Select a song\n" +
                "5: Update Library\n" +
                "6: Filter all the songs in Library\n" +
                "7: Sort Songs by Song Genre\n" +
                "8: Sort Songs by Song Length\n" +
                "9: Sort Songs by User Input\n" +
                "10: Sort Songs by Song Artist\n" +
                "11: Terminate\n" +
                "**********************************************");
        Scanner scanner = new Scanner(System.in);
        int returnValue = invalidCheck(scanner,13,1,true);
        return returnValue;
    }
    private static void printBorder(){
        System.out.println("**********************************************");
    }
    private static void selectSong(Library library,ArrayList<String> songKeys){
        printBorder();
        library.printLibSongs(false,songKeys);
        printBorder();
        System.out.println("Please input the song index:");
        Scanner scanner = new Scanner(System.in);
        int songIndex = indexCheck(scanner,-1,songKeys.size(),"Wrong song index, please try again:",true);
        while(songIndex>=songKeys.size()){
            System.out.println("Wrong index, please try again:");
            songIndex = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("What do you want to do with this song?\n" +
                "1. Play this song\n" +
                "2: Add/Overwrite a tag\n" +
                "3: Remove it from the library\n" +
                "4: Generate a playlist with songs similiar to the selected song\n"+
                "*: Press any other Number Key to go back to Library.");
        int userResponse = invalidCheck(scanner,0,0,false); /*upper bound and lower bound are zero since they are used to quit this menu*/
        switch (userResponse){
            case 1:
                library.playSong(songKeys.get(songIndex));
                break;
            case 2:
                printBorder();
                System.out.println("Please input the Tag name you want to add/overwrite:");
                String newTagName = scanner.nextLine();
                System.out.println("Please input the its value:");
                String newTagData = scanner.nextLine();
                Song song = library.getAllSongsReferencable().get(songKeys.get(songIndex));
                song.setCustomTag(newTagName.toUpperCase(), newTagData.toUpperCase());
                break;
            case 3:
                library.deleteSong(songKeys.get(songIndex));
                songKeys.remove(songIndex);
                break;
            case 4:
            	printBorder();
                Song pSong = library.getAllSongsReferencable().get(songKeys.get(songIndex));
            	library.PlaylistBySimiliarity(pSong);
            	break;
            default:
                break;
        }
    }
}
