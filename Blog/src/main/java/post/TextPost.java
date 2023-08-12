package post;

class TextPost extends Post {
    private int wordCount;

    public TextPost(String title, String content, String publicationDate, int wordCount) {
        super(title, content, publicationDate);
        this.wordCount = wordCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    @Override
    public void display() {
        System.out.println("Text Post:");
        System.out.println("Title: " + getTitle());
        System.out.println("Content: " + getContent());
        System.out.println("Word Count: " + wordCount);
        System.out.println("Publication Date: " + getPublicationDate());
    }
}