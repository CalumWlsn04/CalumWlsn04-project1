public class EndStation extends Station
{
    public EndStation(String lineColor, String name)
    {
        super(lineColor, name);
    }

    public void makeEnd()
    {
        if (this.next != null)
        {
            this.previousStation = this.next;
        }
        else if (this.previousStation != null)
        {
            this.next = this.previousStation;
        }
        else
        {
            System.out.println("Failed to make a EndStation");
        }
    }

    public String toString()
    {
        return "END" + super.toString();
    }
}
