package cn.aurora_x.android.exdictionary.model.bean;

import java.text.DecimalFormat;

/**
 * Created by Rinko on 2016/11/7.
 */
public class WordEntity {
    private String word;
    private String paraphrase;
    private String example;
    private String translation;
    private int correct;
    private int total;

    public WordEntity(String word, String paraphrase, String example, String translation) {
        this.word = word;
        this.paraphrase = paraphrase;
        this.example = example;
        this.translation = translation;
    }

    public WordEntity() {
        this.word = null;
        this.paraphrase = null;
        this.example = null;
        this.translation = null;
        this.correct = 0;
        this.total = 0;
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

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public String getAccuracy() {
        if (total != 0) {
            String res = new DecimalFormat("#.00").format(((double) correct / total) * 100) + "%";
        }
        return "0.00%";
    }
}
