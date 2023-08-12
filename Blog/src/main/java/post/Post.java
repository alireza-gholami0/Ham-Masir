package post;

abstract class Post {
    private String title;
    private String content;
    private String publicationDate;

    public Post(String title, String content, String publicationDate) {
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public abstract void display();
}