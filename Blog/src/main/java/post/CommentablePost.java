package post;

import java.util.ArrayList;
import java.util.List;

class CommentablePost extends Post implements Commentable {
    private List<String> comments;

    public CommentablePost(String title, String content, String publicationDate) {
        super(title, content, publicationDate);
        comments = new ArrayList<>();
    }

    @Override
    public void addComment(String comment) {
        comments.add(comment);
    }

    @Override
    public void displayComments() {
        System.out.println("Comments:");
        for (String comment : comments) {
            System.out.println(comment);
        }
    }

    @Override
    public void display() {
        System.out.println("Text Post:");
        System.out.println("Title: " + getTitle());
        System.out.println("Content: " + getContent());
        System.out.println("Publication Date: " + getPublicationDate());
        displayComments();
    }
}