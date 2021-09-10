public class CharacterNode extends Node {
    private char character;

    public CharacterNode(char character, int value){
        super(value);
        this.character = character;
    }

    public char getCharacter(){
        return character;
    }
}
