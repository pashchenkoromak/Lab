import db.*;

public class Test
{
    public static void main(String[] args)
    {
        DataBase test = new DataBase();
        test.executeCommand("Create Table newTable ( col1 string, col2 int )");
        test.executeCommand("Insert INto newTable VALUES ( col1_val, col2_val )");
        test.executeCommand("Insert INto newTable VALUES ( col1_val_2, col2_val_2 )");
        System.out.println(test.toString());
    }
}