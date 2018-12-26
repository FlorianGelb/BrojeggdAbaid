import javafx.scene.control.TextField;

public class CreateTextField extends TextField
{
    TextField textFeld;

    public CreateTextField(String text)
    {
    textFeld = new TextField(text);
    }

    public TextField createTextFeld()
    {
        return textFeld;
    }
}
