package omnia.adu.ac.ae.midtermpractice;

public class User
{
    private String name;
    private int age;
    private String favoriteColor;

    public User(){}

    public User(String name, int age, String favoriteColor) {
        this.name = name;
        this.age = age;
        this.favoriteColor = favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }
}
