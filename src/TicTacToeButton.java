import javax.swing.*;

public class TicTacToeButton extends JButton {

    private int row;
    private int col;
    private String state;

    public TicTacToeButton(int row, int col) {
        this.row = row;
        this.col = col;
        this.state = "";
        setText("");
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        setText(state);
    }

    public boolean isEmpty() {
        return state.isEmpty();
    }

    public void reset() {
        state = "";
        setText("");
    }
}