package quizApp.API;

/**
 * Helper class to build each category
 *
 * @author Jamie N
 *
 */

public class Categories {


    private String category;
    private int categoryID;

    public Categories(String category, int categoryID) {
        this.category = category;
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return category;
    }
}
