Supported sql commands for now:

    Create table <name> ( <col1_name col1_type, ...> )

    INSERT INTO <name> [(<col1_name, col2_name, ...> )] VALUES ( <value1, value2 ...> )

Example:

    DataBase test = new DataBase();

    test.executeCommand("Create Table newTable ( col1 string, col2 int )");

    test.executeCommand("Insert INto newTable VALUES ( col1_val, col2_val )");

    test.executeCommand("Insert INto newTable VALUES ( col1_val_2, col2_val_2 )");

    test.executeCommand("Insert INto newTable ( col1 ) VALUES ( col1_val_3 )");

    test.executeCommand("Insert INto newTable ( col2 ) VALUES ( col2_val_3 )");

    test.executeCommand("Insert INto newTable ( ) VALUES (  )");