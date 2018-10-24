/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

/**
 *
 * @author admin
 */
public class Word {
    private String word_target;
    private String word_explain;
    public Word(){
        this.word_target = "tu moi";
        this.word_explain = "giai nghia";
    }
    public Word(String target, String explain){
        this.word_target = target;
        this.word_explain = explain;
    }
    public void setTarget(String target){
        this.word_target = target;
    }
    public void setExplain(String explain){
        this.word_explain = explain;
    }
    public String getTarget(){
        return this.word_target;
    }
    public String getExplain(){
        return this.word_explain;
    }
}
