package post;

public class Main {
    public static void main(String[] args) {
        TextPost textPost = new TextPost("title1", "content1", "2023-08-9", 150);
        textPost.display();

        ImagePost imagePost = new ImagePost("title2", "content2", "2023-08-9", "image.jpg");
        imagePost.display();

        CommentablePost commentablePost = new CommentablePost("title3", "content3", "2023-08-5");
        commentablePost.addComment("Great post!");
        commentablePost.addComment("I enjoyed reading this.");
        commentablePost.display();
    }
}