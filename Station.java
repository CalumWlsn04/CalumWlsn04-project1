public class Station
{
    protected String name = "";
    protected String lineColor = "";
    protected boolean service = true;
    protected Station previousStation = null;
    protected Station next = null;
    protected boolean flag = false;

    public Station(String lineColor, String name) //The Station Constructor
    {
        this.name = name;
        this.lineColor = lineColor;
    }

    public void addPrev(Station station) //Add the previous station
    {
        this.previousStation = station;
        station.next = this;
    }

    public void addNext(Station station) //Add the next station
    {
        this.next = station;
        station.previousStation = this;
    }

    public boolean isAvailable() //Return availablility
    {
        return service;
    }

    public void switchAvailable() //Switch the availability of the line
    {
        if (service == true)
            service = false;
        else
            service = true;
    }

    public void connect(Station station)
    {
        if (this.next == null) //The priority is to connect the next station
        {
            this.next = station;
            station.previousStation = this;
        }
        else if (this.previousStation == null) //If the next station is already full, then add a previous station
        {
            this.previousStation = station;
            station.next = this;
        }
        else
            System.out.println("Failed to connect"); //This only happens if there is no space for another station
    }

    public boolean equals(Station station)
    {
        if (this.lineColor.equals(station.lineColor) && this.name.equals(station.name)) //Checks for equality based on linecolor and name of stations
            return true;
        else
            return false;
    }

    int tripLengthRecursive(Station current, Station dest)
    {
        int totalMovement = 0;
        TransferStation temp = new TransferStation("blue/pink", "Transfer"); //These are two temporary specific types of stations, which we will be using to compare Class types
        EndStation endy = new EndStation("temp", "temp");

        if (current.equals(dest)) //We have the main base case here
            return totalMovement;
        else if (current.getClass().equals(temp.getClass()) && !current.lineColor.equals(dest.lineColor)) //If its a transferStation and the current line color is not equal to the destinations final color
        {
            temp = (TransferStation) current;

            int checker = totalMovement;
            for (int i = 0; i < temp.otherStations.size(); i++)
            {
                if(temp.otherStations.get(i).equals(dest)) //Another potential base case
                {
                    return totalMovement + 1;
                }
                else if(temp.otherStations.get(i).lineColor.equals(dest.lineColor))
                {
                    if (!temp.otherStations.get(i).getClass().equals(endy.getClass())) //If its not an endstation
                    {
                        if (!temp.otherStations.get(i).next.name.equals(current.name)) //If the next station for the station in the list is not equal to the current station
                        {
                            totalMovement = totalMovement + 1 + tripLengthRecursive(temp.otherStations.get(i), dest); //Add another movement and then call the recursive loop
                            i = temp.otherStations.size() - 1; //We end the loop here
                        }
                    }
                }
            }
            if (checker == totalMovement) //This is basically for if there has been no movement after iterating through the whole for loop, as in no connections that specifically fit into the category we are looking for at the time
            {
                totalMovement = totalMovement + 1 + tripLengthRecursive(current.next, dest); //We just move on normally in the case we can't find anything in the transferStation
            }
        }
        else //This is saying if its not equal to the dest, but also not a transferstation, this is built on the idea of good input, as in no faulty endStations
        {
            totalMovement = totalMovement + 1 + tripLengthRecursive(current.next, dest); //Move through the current line
        }
        return totalMovement; //Return the totalMovement at the end
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