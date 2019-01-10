import db.*;

public class Main
{
    public static void main(String[] args)
    {
        DataBase test = new DataBase();
        test.executeCommand("Create Table newTable ( col1 string, col2 int )");
        System.out.println(test.toString());
    }
}