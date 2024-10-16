public class Station
{
    protected String name = "";
    protected String lineColor = "";
    protected boolean service = true;
    protected Station previousStation;
    protected Station nextStation;

    public Station(String lineColor, String name)
    {
        this.name = name;
        this.lineColor = lineColor;
        previousStation.name = "none";
        nextStation.name = "none";
    }

    public void addPrev(Station station)
    {
        this.previousStation = station;
        station.nextStation = this;
    }

    public void addNext(Station station)
    {
        this.nextStation = station;
        station.previousStation = this;
    }

    public void makeEnd()
    {
        if (!this.nextStation.name.equals("none"))
        {
            this.previousStation = this.nextStation;
        }
        else if (!this.previousStation.name.equals("none"))
        {
            this.nextStation = this.previousStation;
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
        return "STATION " + this.name + ": " + this.lineColor + " line, in service: " + this.service + ", previous station: " + this.previousStation.name + ", next station: " + this.nextStation.name;
    }
}