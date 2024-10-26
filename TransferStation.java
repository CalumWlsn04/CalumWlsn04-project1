import java.util.*;

public class TransferStation extends Station{
    
    ArrayList<Station> otherStations = new ArrayList<Station>();

    public TransferStation(String lineColor, String name)
    {
        super(lineColor, name);
    }

    public void addTransferStationPrev(Station station)
    {
        station.next = this;
        otherStations.add(station);
    }

    public void addTransferStationNext(Station station)
    {
        station.previousStation = this;
        otherStations.add(station);
    }

    public String printHelp(ArrayList<Station> otherStations)
    {
        String total = "";
        for (int i = 0; i < otherStations.size(); i++)
        {
            total = total + "\t" + otherStations.get(i).toString() + "\n";
        }
        total = "\n\tTransfers: \n" + total;
        return total;
    }

    public String toString()
    {
        return "TRANSFER" + super.toString() + printHelp(otherStations);
    }
}