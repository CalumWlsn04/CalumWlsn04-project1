public class Station
{
    protected String name = "";
    protected String lineColor = "";
    protected boolean service = true;
    protected String previouString = "none";
    protected String nexString = "none";

    public Station(String lineColor, String name)
    {
        this.name = name;
        this.lineColor = lineColor;
    }

    public void addPrev(Station station)
    {
        this.previouString = station.name;
        station.nexString = this.name;
    }

    public void nexString(Station station)
    {
        this.nexString = station.name;
        station.previouString = this.name;
    }

    public void makeEnd()
    {
        if (!this.nexString.equals("none"))
        {
            this.previouString = this.nexString;
        }
        else if (!this.previouString.equals("none"))
        {
            this.nexString = this.previouString;
        }
    }

    public boolean equals(Station station)
    {
        if (this.lineColor.equals(station.lineColor) && this.name.equals(station.name))
            return true;
        else
            return false;
    }

    public String toString()
    {
        return "STATION " + this.name + ": " + this.lineColor + " line, in service: " + this.service + ", previous station: " + this.previouString + ", next station: " + this.nexString;
    }
}