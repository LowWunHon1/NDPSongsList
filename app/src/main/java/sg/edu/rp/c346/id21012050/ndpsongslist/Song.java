package sg.edu.rp.c346.id21012050.ndpsongslist;

import java.io.Serializable;

public class Song implements Serializable {

    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int _id, String title, String singers, int year, int stars) {
        this._id = _id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_id() {  return _id;  }

    public String getTitle() {  return title;  }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingers() {  return singers;  }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public int getYear() {  return year;  }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStar() {  return stars;  }

    public void setStar(int stars) {
        this.stars = stars;
    }

    public String stars(int stars) {
        String star = "";
        for (int i = 1; i <= this.stars; i++) {
            star += "★";
        }
        return star;
    }

    @Override
    public String toString() { return title + "\n" + singers + " - " + year + "\n" + stars;  }


}
