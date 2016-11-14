package cn.aurora_x.android.exdictionary.model.bean;

/**
 * Created by Rinko on 2016/11/7.
 */
public class WordEntity {
    private String word;
    private String paraphrase;
    private String example;
    private String translation;

    public WordEntity(String word, String paraphrase, String example, String translation) {
        this.word = word;
        this.paraphrase = paraphrase;
        this.example = example;
        this.translation = translation;
    }

    public WordEntity() {
        this.word=null;
        this.paraphrase=null;
        this.example=null;
        this.translation=null;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getParaphrase() {
        return paraphrase;
    }

    public void setParaphrase(String paraphrase) {
        this.paraphrase = paraphrase;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return "WordEntity{" +
                "word='" + word + '\'' +
                ", paraphrase='" + paraphrase + '\'' +
                ", example='" + example + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
