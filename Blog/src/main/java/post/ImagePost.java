package post;

class ImagePost extends Post {
    private String imageUrl;

    public ImagePost(String title, String content, String publicationDate, String imageUrl) {
        super(title, content, publicationDate);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void display() {
        System.out.println("Image Post:");
        System.out.println("Title: " + getTitle());
        System.out.println("Content: " + getContent());
        System.out.println("Image URL: " + imageUrl);
        System.out.println("Publication Date: " + getPublicationDate());
    }
}