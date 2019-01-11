import db.*;

public class Test
{
    public static void main(String[] args)
    {
        DataBase test = new DataBase();
        test.executeCommand("Create Table newTable ( col1 string, col2 int )");
        test.executeCommand("Insert INto newTable VALUES ( col1_val, col2_val )");
        test.executeCommand("Insert INto newTable VALUES ( col1_val_2, col2_val_2 )");
        test.executeCommand("Insert INto newTable ( col1 ) VALUES ( col1_val_3 )");
        test.executeCommand("Insert INto newTable ( col2 ) VALUES ( col2_val_3 )");
        test.executeCommand("Insert INto newTable ( ) VALUES (  )");
        System.out.println(test.toString());
    }
}