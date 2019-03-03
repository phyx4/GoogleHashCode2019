import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<Photo> hPhotos = new ArrayList<>();
        ArrayList<Photo> vPhotos = new ArrayList<>();
        ArrayList<Integer[]> slides = new ArrayList<>();

//        String filename = "a_example";
//        String filename = "b_lovely_landscapes";
        String filename = "c_memorable_moments";
//        String filename = "d_pet_pictures";
//        String filename = "e_shiny_selfies";

        ArrayList<ArrayList<Photo>> hv = readPhotos(filename +".txt",hPhotos,vPhotos);
        ArrayList<Photo> horizontal = hOrder(hv.get(0));

        for (Photo p : horizontal) {
            slides.add(new Integer[]{p.getId()});
        }
        slides.addAll(vPair(vPhotos));
        writePhotos(filename + "_output.txt", slides);



    }

    private static ArrayList<ArrayList<Photo>> readPhotos(String filePath, ArrayList<Photo> h,ArrayList<Photo> v) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filePath));
        //total number of photos
        int totalPhotos = Integer.parseInt(in.next());
        ArrayList<ArrayList<Photo>> photos = new ArrayList<>();


        for(int i = 0 ; i < totalPhotos; i++){
            String orient = in.next();
            int tagsNum = Integer.parseInt(in.next());
            ArrayList<String> tags = new ArrayList<>();
            for(int j = 0; j < tagsNum; j++){
                tags.add(in.next());
            }
            Photo p = new Photo(orient, tags, i);
            if(p.getOrientation().equals("H")){
                h.add(p);
            }
            if(p.getOrientation().equals("V")){
                v.add(p);
            }
        }
        photos.add(h);
        photos.add(v);
        return photos;
    }

    private static ArrayList<Photo> hOrder(ArrayList<Photo> h){
        for(int i=1;i<h.size();i++){
            Photo currentPhoto = h.get(i);

            for(int j=i-1;j>=0;j--){
                if(currentPhoto.compare(h.get(j)) ){
                    h.remove(currentPhoto);
                    h.add(j, currentPhoto);
                }
            }
        }

        return h;
    }

    public static ArrayList<Integer[]> vPair(ArrayList<Photo> v){
        ArrayList<Integer[]> vSlides = new ArrayList<>();
        for(int i=0;i<v.size();i=i+2){
//            if(i+1 == v.size()-1){
//                vSlides.add(new Integer[]{v.get(i).getId()});
//                break;
//            }
            vSlides.add(new Integer[]{v.get(i).getId(),v.get(i+1).getId()});
        }

        return vSlides;
    }
    private static  ArrayList<Photo> vOrder(ArrayList<Photo> v){

        return v;
    }

    private static void writePhotos(String filePath, ArrayList<Integer[]> slides) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filePath));
        out.println(slides.size());

        for(Integer[] arr : slides){
            if(arr.length > 1)
                out.println(arr[0] + " " + arr[1]);
            else
                out.println(arr[0]);
        }
        out.flush();
    }
}
