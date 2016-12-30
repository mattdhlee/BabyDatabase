

public class DataSetException extends Exception
{
    private String custom_msg;

    public DataSetException()
    {
        super();
        custom_msg = "";
    }
    
    public DataSetException(String error)
    {
        super(error);
        custom_msg = error;
    }
    
    public String getCustomMsg()
    {
        return custom_msg;
    }
}
