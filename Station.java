public class Station
{
    protected String name = "";
    protected String lineColor = "";
    protected boolean service = true;
    protected Station previousStation = null;
    protected Station next = null;
    protected boolean flag = false;

    public Station(String lineColor, String name)
    {
        this.name = name;
        this.lineColor = lineColor;
        //previousStation.name = "none";
        //next.name = "none";
    }

    public void addPrev(Station station)
    {
        this.previousStation = station;
        station.next = this;
    }

    public void addNext(Station station)
    {
        this.next = station;
        station.previousStation = this;
    }

    public boolean isAvailable()
    {
        return service;
    }

    public void switchAvailable()
    {
        if (service == true)
            service = false;
        else
            service = true;
    }

    public void connect(Station station)
    {
        if (this.next == null)
        {
            this.next = station;
            station.previousStation = this;
        }
        else if (this.previousStation == null)
        {
            this.previousStation = station;
            station.next = this;
        }
        else
            System.out.println("Failed to connect");
    }

    public boolean equals(Station station)
    {
        if (this.lineColor.equals(station.lineColor) && this.name.equals(station.name))
        {
            System.out.println("Right here!");
            return true;
        }
        else
            return false;
    }

    int tripLengthRecursive(Station current, Station dest)
    {
        int totalMovement = 0;
        TransferStation temp = new TransferStation("blue/pink", "Transfer");
        Station temp2;

        temp2 = (Station) temp;
        System.out.println(temp2.getClass());
        
        System.out.println("This is the current station: " + current.name + ", and this is the current station type: " + current.getClass() + ", and this is the current linecolor: " + current.lineColor);
        System.out.println("These are the two conditions: " + current.getClass().equals(temp.getClass()) + ", " + !current.lineColor.equals(dest.lineColor));

        if (current.equals(dest))
            return totalMovement;
        else if (current.getClass().equals(temp.getClass()) && !current.lineColor.equals(dest.lineColor))
        {
            temp = (TransferStation) current;
            System.out.println("This is the size of the temp otherstations arraylist: " + temp.otherStations.size());
            for (int i = 0; i < temp.otherStations.size(); i++)
            {
                System.out.println("Station Name: " + temp.otherStations.get(i).name + ", True? " + temp.otherStations.get(i).lineColor.equals(dest.lineColor));
                System.out.println("WHY!!!")
                if(temp.otherStations.get(i).equals(dest))
                {
                    return totalMovement + 1;
                }

                if(temp.otherStations.get(i).lineColor.equals(dest.lineColor) && !temp.otherStations.get(i).next.name.equals(current.name))
                {
                    System.out.println("HEY");
                        totalMovement = totalMovement + 1 + tripLengthRecursive(temp.otherStations.get(i), dest);
                        i = temp.otherStations.size() - 1;
                }
            }
        }
        else
        {
            totalMovement = totalMovement + 1 + tripLengthRecursive(current.next, dest);
        }


        /*if (current.equals(dest))
            return totalMovement;
        else if (current.name.equals("Metro Center"))
        {
            temp = (TransferStation) current;

            if (dest.lineColor.equals("orange"))
            {
                totalMovement = totalMovement + 1 + tripLengthRecursive(current.next, dest);
            }
            else
            { 
                for (int i = 0; i < temp.otherStations.size(); i++)
                {
                    if(temp.otherStations.get(i).lineColor == dest.lineColor && !temp.otherStations.get(i).next.name.equals("Metro Center"))
                    {
                        totalMovement = totalMovement + 1 + tripLengthRecursive(temp.otherStations.get(i), dest);
                        i = temp.otherStations.size() - 1;
                    }
                }
            }
        }
        else
        {
            System.out.println("This is the current station: " + current.name);
            totalMovement = totalMovement + 1 + tripLengthRecursive(current.next, dest);
        }
        
        System.out.println("This is the totalMovement: " + totalMovement);*/

        return totalMovement;
    }

    int tripLength(Station dest)
    {
        return tripLengthRecursive(this, dest);
    }

    public String toString()
    {
        String tempNext = "none";
        String tempPrev = "none";

        if (this.next != null)
            tempNext = this.next.name;
        if (this.previousStation != null)
            tempPrev = this.previousStation.name;

        return "STATION " + this.name + ": " + this.lineColor + " line, in service: " + this.service + ", previous station: " + tempPrev + ", next station: " + tempNext;
    }
}